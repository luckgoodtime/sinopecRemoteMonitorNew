package com.lng.action.infrastructure;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.model.biz.Route;
import com.lng.service.base.AddressService;
import com.lng.service.base.RouteService;

@Controller
@RequestMapping("/route")
public class RouteController {

	public static final String DIR = "infrastructure/route/";
	public static final String R = "redirect:/route/list.do";
	
	@Resource
	private RouteService routeService;
	@Resource
	private AddressService addressService;
	
	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(Model model, HttpSession session) {
		
		model.addAttribute("p", routeService.getListForPageDefault());
		return DIR + "list";
	}

	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Model model, Route o, HttpSession session) {
		if (o.getId()!=null) {
			model.addAttribute("o", routeService.get(Route.class, o.getId()));
		}
		//查询地址列表
		model.addAttribute("addressList", addressService.getList());
		return DIR + "addOrUpdate";
	}

	/**
	 * @category 新增或更新
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(Route o,HttpServletRequest request, HttpSession session) {

		// 新增
		routeService.save(o);
		return R;
	}
	
	/**
	 * @category 新增或更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(Route o,HttpServletRequest request, HttpSession session) {

		//修改
		Route o2 =  (Route)routeService.get(Route.class, o.getId());
		if(o2!=null){
			o2.setAddressId1(o.getAddressId1());
			o2.setAddressId2(o.getAddressId2());
			o2.setDistance(o.getDistance());
			o2.setRouteMarking(o.getRouteMarking());
			routeService.update(o2);
		}
	
		return R;
	}

	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del")
	public String del(String ids) {

		if (StringUtils.isNotBlank(ids)) {
			routeService.del(ids);
		}
		return R;
	}


}
