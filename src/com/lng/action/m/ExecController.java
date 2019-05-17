package com.lng.action.m;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lng.action.main.BaseMController;
import com.lng.model.biz.ShippingOrder;
import com.lng.service.biz.order.ShippingOrderService;

@Controller
@RequestMapping("/exec")
public class ExecController extends BaseMController{

	public static final String DIR = "exec/";
	@Resource
	private ShippingOrderService shippingOrderService;
	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request) {
		
//		request.setAttribute("p", shippingOrderService.getListForPageDefault());
		return DIR+"list";
	}
	
	/**
	 * @category 列表
	 */
	@RequestMapping("/listJson.do")
	@ResponseBody
	public Object listJson(HttpServletRequest request) {
		
		return shippingOrderService.getListForPageDefault();
	}
	
	/**
	 * @category 详细页
	 */
	@RequestMapping("/view.do")
	public String view(HttpServletRequest request) {

		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			ShippingOrder shippingOrder = (ShippingOrder)baseService.get(ShippingOrder.class, Integer.parseInt(id));
			request.setAttribute("shippingOrder",shippingOrder);
		}
	
		
		return DIR+"view";
	}
	

}
