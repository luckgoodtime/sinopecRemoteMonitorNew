package com.lng.action.businessexec;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.action.main.BaseController;
import com.lng.model.base.Person;
import com.lng.model.biz.ShippingOrder;
import com.lng.model.biz.ShippingOrderUnload;
import com.lng.service.base.EndPointService;
import com.lng.service.biz.order.ShippingOrderService;
import com.lng.service.biz.order.ShippingOrderUnloadService;

/**
 * 运输单卸气
 *
 */
@Controller
@RequestMapping("/shippingOrderUnload")
public class ShippingOrderUnloadController extends BaseController {

	public static final String DIR = "businessexec/shippingOrderUnload/";
	public static final String R = "redirect:/shippingOrderUnload/list.do";
	
	@Resource
	private ShippingOrderService shippingOrderService;
	@Resource
	private ShippingOrderUnloadService shippingOrderUnloadService;
	@Resource
	private EndPointService endPointService;
	
	
	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request) {
		request.setAttribute("p", shippingOrderUnloadService.getListForPageDefault());
		return DIR + "list";
	}

	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(ShippingOrder o, HttpServletRequest request) {
		if (o.getId() !=null) {
			request.setAttribute("o",baseService.get(ShippingOrderUnload.class, o.getId()));
		}
		// 运输单列表
		request.setAttribute("shippingOrderList", shippingOrderService.getList());
		//卸气点
		request.setAttribute("endPointList", endPointService.getList());
				
		return DIR + "addOrUpdate";
	}

	/**
	 * @category 新增
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(ShippingOrderUnload o,HttpServletRequest request) {
		// 新增
		Person person = (Person) request.getSession().getAttribute("person");


		baseService.save(o);
		return R;
	}
	
	
	/**
	 * @category 更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(ShippingOrderUnload o,HttpServletRequest request) {

		//修改
		ShippingOrderUnload o2 =  (ShippingOrderUnload)baseService.get(ShippingOrderUnload.class, o.getId());
		if(o2!=null){
			o2.setArrivedTime(o.getArrivedTime());
			o2.setEndUnload(o.getEndUnload());
			o2.setLeavingTime(o.getLeavingTime());
			o2.setMemo(o.getMemo());
			o2.setShippingOrderId(o.getShippingOrderId());
			o2.setStartUnload(o.getStartUnload());
			o2.setUnLoadingGW(o.getUnLoadingGW());
			o2.setUnLoadingNW(o.getUnLoadingNW());
			o2.setUnLoadingTW(o.getUnLoadingTW());
			
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
			shippingOrderUnloadService.del(ids);
		}
		return R;
	}

	
}