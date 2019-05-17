package com.lng.action.businessexec;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.action.main.BaseController;
import com.lng.model.base.Person;
import com.lng.model.biz.ShippingOrder;
import com.lng.service.base.CorporationService;
import com.lng.service.base.EndPointService;
import com.lng.service.base.SourcePointService;
import com.lng.service.biz.order.ShippingOrderService;

/**
 * 运输单
 *
 */
@Controller
@RequestMapping("/shippingOrder")
public class ShippingOrderController extends BaseController {

	public static final String DIR = "businessexec/shippingOrder/";
	public static final String R = "redirect:/shippingOrder/list.do";
	
	@Resource
	private ShippingOrderService shippingOrderService;
	@Resource
	private CorporationService corporationService;
	@Resource
	private SourcePointService sourcePointService;
	@Resource
	private EndPointService endPointService;
	
	
	
	
	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request) {
		request.setAttribute("p", shippingOrderService.getListForPageDefault());
		return DIR + "list";
	}

	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(ShippingOrder o, HttpServletRequest request) {
		if (o.getId() !=null) {
			ShippingOrder so = (ShippingOrder)baseService.get(ShippingOrder.class, o.getId());
			request.setAttribute("o",so);
		}
		// 公司列表
		request.setAttribute("corporationList", corporationService.getList());
		//气源点
		request.setAttribute("sourcePointList", sourcePointService.getList());
		//卸气点
		request.setAttribute("endPointList", endPointService.getList());
		if("true".equals(request.getParameter("tooltip")))
			return DIR + "tooltip";
		else 
			return DIR + "addOrUpdate";
	}

	/**
	 * @category 新增
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo( ShippingOrder o,HttpServletRequest request) {
		// 新增
		Person person = (Person) request.getSession().getAttribute("person");


		baseService.save(o);
		return R;
	}
	
	
	/**
	 * @category 更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(ShippingOrder o,HttpServletRequest request) {

		//修改
		ShippingOrder o2 =  (ShippingOrder)baseService.get(ShippingOrder.class, o.getId());
		if(o2!=null){
			o2.setTransportCorpPartyId(o.getTransportCorpPartyId());
			o2.setTransportOwner(o.getTransportOwner());
			o2.setTruckNo(o.getTruckNo());
			o2.setTankNo(o.getTankNo());
			o2.setDriverName(o.getDriverName());
			o2.setDriverTel(o.getDriverTel());
			o2.setSourcePartyId(o.getSourcePartyId());
			o2.setEndPointPartyId(o.getEndPointPartyId());
			o2.setTransportCorpPartyId(o.getTransportCorpPartyId());
			o2.setRequiredString(o.getRequiredString());
			o2.setUnLoadOnRoad(o.getUnLoadOnRoad());
			o2.setPlanLoadingString(o.getPlanLoadingString());
			o2.setArriveLoadTime(o.getArriveLoadTime());
			o2.setLoadingTime(o.getLoadingTime());
			o2.setLoadingTW(o.getLoadingTW());
			o2.setLoadingGW(o.getLoadingGW());
			o2.setLoadingNW(o.getLoadingNW());
			o2.setSettleWeight(o.getSettleWeight());
			
			baseService.update(o2);
			
		}
		
		return R;
	}
	

	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del")
	public String del(String ids) {

		if (StringUtils.isNotBlank(ids)) {
			shippingOrderService.del(ids);
		}
		return R;
	}
	
	
	
}