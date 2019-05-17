package com.lng.action.businessexec;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lng.action.main.BaseController;
import com.lng.model.base.Dic;
import com.lng.model.base.Person;
import com.lng.model.biz.BizOrder;
import com.lng.service.base.DicService;
import com.lng.service.biz.order.OrderService;
import com.lng.util.Const;
import com.lng.util.Message;
import com.lng.util.Page;
import com.lng.util.Util;

/**
 */
@Controller
@RequestMapping("/biz")
public class OrderController extends BaseController {
	public static final String DIR = "businessexec/order/";
	public static final String R_S = "redirect:/biz/saleorderlist.do";
	public static final String R_P = "redirect:/biz/purchaseorderlist.do";
	
	public static final String REPORT_DIR = "smartlpg/report/";

	@Resource
	private OrderService orderService;
	
	@Resource
	private DicService dicService;// 数据字典
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping("/saleorderlist.do")
	public String list(Model model, BizOrder bizOrder, HttpSession session) {

		Person person = (Person) session.getAttribute("person");

		// Integer supCorpPartyId = user.getPartyId();
		model.addAttribute("p", orderService.getListForPageDefault(bizOrder, person.getCorpPartyId(),
				"SALE"));
		return DIR + "list";
	}

	@RequestMapping("/purchaseorderlist.do")
	public String plist(Model model, BizOrder o, HttpSession session,HttpServletRequest request) {

		Person person = (Person) session.getAttribute("person");

		// Integer supCorpPartyId = user.getPartyId();
		//model.addAttribute("p", orderService.getListForPageDefault(o, person.getCorpPartyId(),	"PURCHASE"));
//		request.setAttribute("from", request.getParameter("from"));
		
		String from  = (String)request.getParameter("from");
		
		String viewPage = "plist";
		if("purchaseCostRegister".equals(from))
			viewPage = "plistwithcost";
		
		return DIR + viewPage;
	}
	
	@RequestMapping("/ajaxPlist.do")
	@ResponseBody
	public Object getPlist(Model model, BizOrder o, HttpSession session,HttpServletRequest request) {

		Person person = (Person) session.getAttribute("person");
		Page p = orderService.getListForPageDefault(o, person.getCorpPartyId(),	"PURCHASE");
		
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("total", p.getTotalRows());
		returnMap.put("rows", p.getDataList());
		return returnMap;
	}

	
	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Model model, BizOrder o,HttpServletRequest request, HttpSession session) {
		if (o.getId() != null) {
			model.addAttribute("o", orderService.get(BizOrder.class, o.getId()));
		}
		
		String bizType = (String)request.getParameter("bizType");
		
		//获取气品列表
        Dic dic = new Dic();
        if("SALE".equalsIgnoreCase(bizType)) {
        	dic.setType(Const.GAS_TYPE_NONSTANDARD);
        }else {
        	dic.setType(Const.GAS_TYPE);
        }
        request.setAttribute("gasTypeList",dicService.getDicList(dic));
		
		
		if("SALE".equalsIgnoreCase(bizType))
			return DIR + "addOrUpdateSale";
		else
			return DIR + "addOrUpdatePurchase";
	}
	
	
	/**
	 * @category 更新库存成本
	 */
	@RequestMapping(value = "/addOrUpdateCost.do")
	public String addOrUpdateCost(Model model, BizOrder o,HttpServletRequest request, HttpSession session) {
		if (o.getId() != null) {
			model.addAttribute("o", orderService.get(BizOrder.class, o.getId()));
		}
		
		String bizType = (String)request.getParameter("bizType");
		//获取气品列表
        Dic dic = new Dic();
        if("SALE".equalsIgnoreCase(bizType)) {
        	dic.setType(Const.GAS_TYPE_NONSTANDARD);
        }else {
        	dic.setType(Const.GAS_TYPE);
        }
        request.setAttribute("gasTypeList",dicService.getDicList(dic));
        
		if("SALE".equalsIgnoreCase(bizType))
			return DIR + "addOrUpdateSale";
		else
			return DIR + "UpdatePurchaseCost";
	}

	/**
	 * @category 新增
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(BizOrder o, HttpServletRequest request,
			HttpSession session) {
		// 新增
		Person person = (Person) session.getAttribute("person");
		
		String bizType = request.getParameter("bizType");
		
		if("SALE".equalsIgnoreCase(bizType)) {
			o.setSupCorpPartyId(person.getCorp().getPartyId());
			o.setSupplierName(person.getCorp().getCorpName());
		}
		if("PURCHASE".equalsIgnoreCase(bizType)) {
			o.setCusCorpPartyId(person.getCorp().getPartyId());
			o.setCustomerName(person.getCorp().getCorpName());
		}
		
		o.setCreatePartyId(person.getPartyId());
		orderService.save(o);

		if("SALE".equalsIgnoreCase(bizType))		
			return R_S;
		else
			return R_P +"?from="+request.getParameter("from");
	}

	/**
	 * @category 更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(BizOrder o, HttpServletRequest request,
			HttpSession session) {

		// 修改
		BizOrder o2 = (BizOrder) orderService.get(BizOrder.class, o.getId());
		if (o2 != null) {
			
			this.overFillOrderBiz(o2, request);
			
			orderService.update(o2);

			// TODO 测试jms
			// String msg = "id:"+o.getPartyId()+" 名称:"+o.getPointName()+"
			// 类型:"+o.getPointType();
			// messageSender.sendMessage(msg);
		}
		
		
		String bizType = (String)request.getParameter("bizType");
		
		if("SALE".equalsIgnoreCase(bizType))		
			return R_S;
		else
			return R_P +"?from="+request.getParameter("from");
	}
	
	
	/**
	 * 
	 * 入库确认 ,产生入库流水
	 * 
	 * @category 更新
	 */
	@RequestMapping(value = "/instock")
	public String inStock(BizOrder o, String note,  HttpSession session) {		
		Person person = (Person) session.getAttribute("person");
		
		BizOrder o2 = (BizOrder) orderService.get(BizOrder.class, o.getId());

		String msg = null;

//		List list = stockEntryService.queryStockEntry(o2.getGasType(), "采购入库", "1", o2.getId(), person);
//		
//		if(list == null || list.size() == 0) {
//			Message message = stockService. updateInStock(o2.getGasType(),null, o2.getLoadingNW(), new BigDecimal(0), o2.getPurchaseCost(), Util.dateToStr2(new Date()), person, "采购入库", o2.getId(), null);
//			msg = message.getMesasge();
//			if(message.isSuc()) {
//				o2.setInStockFlag("已入库");
//				this.orderService.update(o2);
//			}
//				
//		} else
//			msg = "已经生成过入库流水";
		
		suc(msg);//提示操作成功
		return R_P;
	}
	

	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del.do")
	public String del(String ids, HttpServletRequest request) {
		
		if (StringUtils.isNotBlank(ids)) {
			orderService.del(ids);
		}
		
		String bizType = (String)request.getParameter("bizType");
		
		if("SALE".equalsIgnoreCase(bizType))		
			return R_S;
		else
			return R_P;

	}
	
	private void overFillOrderBiz(BizOrder order, HttpServletRequest request) {
		
		if(order == null) return;
		
		Map params = request.getParameterMap();
		
		/**运输单Id*/
		//private Integer shippingOrderId;
		if(params.containsKey("shippingOrderId"))
			order.setShippingOrderId(Util.isEmptyString((String)request.getParameter("shippingOrderId"))?null:
				Integer.valueOf((String)request.getParameter("shippingOrderId")));
		/**车牌号*/
		//private String plateNo;
		if(params.containsKey("plateNo"))
			order.setPlateNo((String)request.getParameter("plateNo"));
		
		/**挂车号*/
		//private String tankNo;
		if(params.containsKey("tankNo"))
			order.setTankNo((String)request.getParameter("tankNo"));
		
		/**司机partyId*/
		//private Integer driverPartyId;
		if(params.containsKey("driverPartyId"))
			order.setDriverPartyId(Util.isEmptyString((String)request.getParameter("driverPartyId"))?null:
				Integer.valueOf((String)request.getParameter("driverPartyId")));		
		
		/**司机姓名*/
		//private String driverName;
		if(params.containsKey("driverName"))
			order.setDriverName((String)request.getParameter("driverName"));
		
		/**司机电话*/
		//private String driverTel;
		if(params.containsKey("driverTel"))
			order.setDriverTel((String)request.getParameter("driverTel"));
		
		/**运输商Id*/
		//private Integer logisticsPartyId;
		if(params.containsKey("logisticsPartyId"))
			order.setLogisticsPartyId(Util.isEmptyString((String)request.getParameter("logisticsPartyId"))?null:
				Integer.valueOf((String)request.getParameter("logisticsPartyId")));		
		
		/**运输商名称*/
		//private String logisticsName;
		if(params.containsKey("logisticsName"))
			order.setLogisticsName((String)request.getParameter("logisticsName"));		
		
		/**供应公司Id*/
		//private Integer supCorpPartyId;
		if(params.containsKey("supCorpPartyId"))
			order.setSupCorpPartyId(Util.isEmptyString((String)request.getParameter("supCorpPartyId"))?null:
				Integer.valueOf((String)request.getParameter("supCorpPartyId")));	
		
		/**供应商名称*/
		//private String supplierName;
		if(params.containsKey("supplierName"))
			order.setSupplierName((String)request.getParameter("supplierName"));	
		
		/**气源点*/
		//private Integer sourcePartyId;
		if(params.containsKey("sourcePartyId"))
			order.setSourcePartyId(Util.isEmptyString((String)request.getParameter("sourcePartyId"))?null:
				Integer.valueOf((String)request.getParameter("sourcePartyId")));	
		
		/**气源点名称*/
		//private String sourceName;
		if(params.containsKey("sourceName"))
			order.setSourceName((String)request.getParameter("sourceName"));			
		
		/**客户公司Id*/
		//private Integer cusCorpPartyId;
		if(params.containsKey("cusCorpPartyId"))
			order.setCusCorpPartyId(Util.isEmptyString((String)request.getParameter("cusCorpPartyId"))?null:
				Integer.valueOf((String)request.getParameter("cusCorpPartyId")));		
		
		/**客户名称*/
		//private String customerName;
		if(params.containsKey("customerName"))
			order.setCustomerName((String)request.getParameter("customerName"));			
		
		/**终端卸气点1*/
		//private Integer endPointPartyId1;
		if(params.containsKey("endPointPartyId1"))
			order.setEndPointPartyId1(Util.isEmptyString((String)request.getParameter("endPointPartyId1"))?null:
				Integer.valueOf((String)request.getParameter("endPointPartyId1")));	
		
		/**终端点名称1*/
		//private String endPointName1;
		if(params.containsKey("endPointName1"))
			order.setEndPointName1((String)request.getParameter("endPointName1"));		
		
		/**终端卸气点2*/
		//private Integer endPointPartyId2;
		if(params.containsKey("endPointPartyId2"))
			order.setEndPointPartyId2(Util.isEmptyString((String)request.getParameter("endPointPartyId2"))?null:
				Integer.valueOf((String)request.getParameter("endPointPartyId2")));			
		
		/**终端点名称2*/
		//private String endPointName2;
		if(params.containsKey("endPointName2"))
			order.setEndPointName2((String)request.getParameter("endPointName2"));				
		
		/**终端卸气点3*/
		//private Integer endPointPartyId3;
		if(params.containsKey("endPointPartyId3"))
			order.setEndPointPartyId3(Util.isEmptyString((String)request.getParameter("endPointPartyId3"))?null:
				Integer.valueOf((String)request.getParameter("endPointPartyId3")));		
		
		/**终端点名称3*/
		//private String endPointName3;	
		if(params.containsKey("endPointName3"))
			order.setEndPointName3((String)request.getParameter("endPointName3"));			
		
		/**要求日期*/
		//private String requiredString;
		if(params.containsKey("requiredString"))
			order.setRequiredString((String)request.getParameter("requiredString"));			
		
		/**计划提货量*/
		//private BigDecimal quantity;
		if(params.containsKey("quantity"))
			order.setQuantity(Util.isEmptyString((String)request.getParameter("quantity"))?null:
				new BigDecimal((String)request.getParameter("quantity")));			
		
		/**是否分卸,是，否*/
		//private String unLoadOnRoad;
		if(params.containsKey("unLoadOnRoad"))
			order.setUnLoadOnRoad((String)request.getParameter("unLoadOnRoad"));
		
		/**计划装车日期*/
		//private String planLoadingString;
		if(params.containsKey("planLoadingString"))
			order.setPlanLoadingString((String)request.getParameter("planLoadingString"));
		
		/**装车时间*/
		//private String loadingTime;
		if(params.containsKey("loadingTime"))
			order.setLoadingTime((String)request.getParameter("loadingTime"));		
		
		/**装车皮重(Tare Weight)*/
		//private BigDecimal loadingTW;
		if(params.containsKey("loadingTW"))
			order.setLoadingTW(Util.isEmptyString((String)request.getParameter("loadingTW"))?null:
				new BigDecimal((String)request.getParameter("loadingTW")));
		
		/**装车毛重(Gross Weight)*/
		//private BigDecimal loadingGW;
		if(params.containsKey("loadingGW"))
			order.setLoadingGW(Util.isEmptyString((String)request.getParameter("loadingGW"))?null:
				new BigDecimal((String)request.getParameter("loadingGW")));		
		
		/**装车净重(Net Weight)*/
		//private BigDecimal loadingNW;
		if(params.containsKey("loadingNW"))
			order.setLoadingNW(Util.isEmptyString((String)request.getParameter("loadingNW"))?null:
				new BigDecimal((String)request.getParameter("loadingNW")));			
		
		/**卸车时间*/
		//private String unloadingTime;
		if(params.containsKey("unloadingTime"))
			order.setUnloadingTime((String)request.getParameter("unloadingTime"));		
		
		/**卸车毛重*/
		//private BigDecimal unLoadingGW;	
		if(params.containsKey("unLoadingGW"))
			order.setUnLoadingGW(Util.isEmptyString((String)request.getParameter("unLoadingGW"))?null:
				new BigDecimal((String)request.getParameter("unLoadingGW")));			

		/**卸车皮重*/
		//private BigDecimal unLoadingTW;	
		if(params.containsKey("unLoadingTW"))
			order.setUnLoadingTW(Util.isEmptyString((String)request.getParameter("unLoadingTW"))?null:
				new BigDecimal((String)request.getParameter("unLoadingTW")));		
		
		/**卸车净重*/
		//private BigDecimal unLoadingNW;	
		if(params.containsKey("unLoadingNW"))
			order.setUnLoadingNW(Util.isEmptyString((String)request.getParameter("unLoadingNW"))?null:
				new BigDecimal((String)request.getParameter("unLoadingNW")));		
		
		/**结算量*/
		//private BigDecimal settleWeight;
		if(params.containsKey("settleWeight"))
			order.setSettleWeight(Util.isEmptyString((String)request.getParameter("settleWeight"))?null:
				new BigDecimal((String)request.getParameter("settleWeight")));	
		
		/**磅差费*/
		//private BigDecimal weightGagFee;
		if(params.containsKey("weightGagFee"))
			order.setWeightGagFee(Util.isEmptyString((String)request.getParameter("weightGagFee"))?null:
				new BigDecimal((String)request.getParameter("weightGagFee")));	
		
		/**增加运费*/
		//private BigDecimal addingFreight;
		if(params.containsKey("addingFreight"))
			order.setAddingFreight(Util.isEmptyString((String)request.getParameter("addingFreight"))?null:
				new BigDecimal((String)request.getParameter("addingFreight")));	
		
		/**总结算金额*/
		//private BigDecimal totalSettleAmount;	
		if(params.containsKey("totalSettleAmount"))
			order.setTotalSettleAmount(Util.isEmptyString((String)request.getParameter("totalSettleAmount"))?null:
				new BigDecimal((String)request.getParameter("totalSettleAmount")));	
		
		/**创建者*/
		//private Integer createPartyId;
		if(params.containsKey("createPartyId"))
			order.setCreatePartyId(Util.isEmptyString((String)request.getParameter("createPartyId"))?null:
				Integer.valueOf((String)request.getParameter("createPartyId")));	

		
		/**单价*/
		if(params.containsKey("unitPrice"))
			order.setUnitPrice(Util.isEmptyString((String)request.getParameter("unitPrice"))?null:
				new BigDecimal((String)request.getParameter("unitPrice")));	
		
		/**购气金额*/
		if(params.containsKey("money"))
			order.setMoney(Util.isEmptyString((String)request.getParameter("money"))?null:
				new BigDecimal((String)request.getParameter("money")));	
		
		/**运费单价*/
		if(params.containsKey("freightPrice"))
			order.setFreightPrice(Util.isEmptyString((String)request.getParameter("freightPrice"))?null:
				new BigDecimal((String)request.getParameter("freightPrice")));	
		
		/**运费*/
		if(params.containsKey("transportFee"))
			order.setTransportFee(Util.isEmptyString((String)request.getParameter("transportFee"))?null:
				new BigDecimal((String)request.getParameter("transportFee")));	
		
		/**采购成本*/
		if(params.containsKey("purchaseCost"))
			order.setPurchaseCost(Util.isEmptyString((String)request.getParameter("purchaseCost"))?null:
				new BigDecimal((String)request.getParameter("purchaseCost")));	
		/**气品*/
		if(params.containsKey("gasType"))
			order.setGasType((String)request.getParameter("gasType"));
		
		/**单据号*/
		if(params.containsKey("trackNumber"))
			order.setTrackNumber((String)request.getParameter("trackNumber"));
			
	}
	
	
	/**
	 * 
	 *采购报表
	 * 
	 */
	@RequestMapping(value = "/purchasereport.do")
	public String purchaseReport(Model model, HttpSession session, String startTimeStr, String endTimeStr, String supplierName, String sourceName,
			String gasType, String from) {
		 
		Person person = (Person) session.getAttribute("person");
		
		if(Util.isEmptyString(startTimeStr)) startTimeStr = Util.datetoStr(new Date()) + " 00:00:00";
		if(Util.isEmptyString(endTimeStr)) endTimeStr = Util.datetoStr(new Date()) + " 23:59:59";
		
		model.addAttribute("startTimeStr", startTimeStr);
		model.addAttribute("endTimeStr", endTimeStr);
		model.addAttribute("supplierName", supplierName);
		model.addAttribute("sourceName", sourceName);
		model.addAttribute("gasType", gasType);
		
		model.addAttribute("p", this.orderService.purchaseReport(person.getCorpPartyId(), startTimeStr, endTimeStr, supplierName, sourceName, gasType));
		
		//获取气品列表
		Dic dic = new Dic();
		dic.setType(Const.GAS_TYPE_NONSTANDARD);
		model.addAttribute("gasTypeList",dicService.getDicList(dic));

		
		if("purchaseReportWithC".equals(from))
			return REPORT_DIR + "purchaseReportWithC";
		else
			return REPORT_DIR + "purchaseReport";
	}
	
}
