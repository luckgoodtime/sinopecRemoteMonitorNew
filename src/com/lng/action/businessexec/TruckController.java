package com.lng.action.businessexec;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.action.main.BaseController;
import com.lng.model.base.Person;
import com.lng.model.base.Truck;
import com.lng.service.base.CorporationService;
import com.lng.service.biz.order.TruckService;
import com.lng.util.set.AutoSet;

@Controller
@RequestMapping("/truck")
public class TruckController extends BaseController{

	public static final String DIR = "businessexec/truck/";
	public static final String R = "redirect:/truck/list.do";
	
	@Resource
	private TruckService truckService;
	@Resource
	private CorporationService corporationService;
	
	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request) {
		
		request.setAttribute("p", truckService.getListForPageDefault());
		return DIR + "list";
	}
	
	
	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Truck o, HttpServletRequest request) {
		if (o.getId() !=null) {
			Truck o2 = (Truck)baseService.get(Truck.class, o.getId());
			request.setAttribute("o", o2);
		}
		// 公司列表
		request.setAttribute("corporationList", corporationService.getList());
		return DIR + "addOrUpdate";
	}

	/**
	 * @throws ParseException 
	 * @category 新增
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(@Valid @ModelAttribute("o")Truck o,Errors errors,HttpServletRequest request, HttpSession session) {

		if (errors.hasErrors()) return VALIDATE_ERROR;
		// 新增
		Person person = (Person) session.getAttribute("person");
			
		baseService.save(o);
	
		return R;
	}
	
	/**
	 * @throws ParseException 
	 * @category 更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(@Valid @ModelAttribute("o")Truck o, Errors errors,HttpServletRequest request, HttpSession session) {

		if (errors.hasErrors()) return VALIDATE_ERROR;
		//修改
		Truck o2 =  (Truck)baseService.get(Truck.class, o.getId());
		if(o2!=null){
			AutoSet.setObject(o, o2);
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
			baseService.del(ids);
		}
		return R;
	}


}
