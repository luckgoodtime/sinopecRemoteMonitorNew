package com.lng.action.infrastructure;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lng.action.main.BaseController;
import com.lng.model.base.Address;
import com.lng.service.base.AddressService;
import com.lng.util.Util;

@Controller
@RequestMapping("/address")
@SuppressWarnings({"unchecked" })
public class AddressController extends BaseController{
	
	public static final String DIR = "infrastructure/address/";
	public static final String R = "redirect:/address/list.do";
	
	@Resource
	private AddressService addressService;
	
	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(Model model, HttpSession session) {
		
		model.addAttribute("p", addressService.getListForPageDefault());
		return DIR + "list";
	}
	
	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Model model, Address o, HttpSession session) {
		if (o.getAddressId() != null) {
			Address address = (Address)baseService.get(Address.class, o.getAddressId());
			model.addAttribute("o", address);
		}
		return DIR + "addOrUpdate";
	}

	/**
	 * @throws ParseException 
	 * @category 新增
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(Model model, Address address, HttpServletRequest request, HttpSession session) throws ParseException {

		//创建地址			
		baseService.save(address);
		return R;
	}
	
	/**
	 * @throws ParseException 
	 * @category 新增或更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(Model model, Address address, HttpServletRequest request, HttpSession session) throws ParseException {


		//更新地址
		Address currentAddress =  (Address)baseService.get(Address.class, address.getAddressId());
		currentAddress.setState(address.getState());
		currentAddress.setCity(address.getCity());
		currentAddress.setCounty(address.getCounty());
		currentAddress.setAddress1(address.getAddress1());			
		currentAddress.setPostalCode(address.getPostalCode());
		
		currentAddress.setLatitude(address.getLatitude());
		currentAddress.setLongitude(address.getLongitude());
		currentAddress.setAltitude(address.getAltitude());
		
		this.addressService.update(currentAddress);	
	
		return R;
	}
	
	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del")
	public String del(String ids) {

		if (StringUtils.isNotBlank(ids)) {
			this.addressService.del(ids);
		}
		return R;
	}

	/*************************************ajax**************************************************************************/
	
	/**
	 * @category ajax地址列表
	 */
	@RequestMapping("/ajax_list.do")
	@ResponseBody
	public List ajax_list(HttpServletRequest request) {
		
		String q = request.getParameter("q");
		String hql = "from Address";
		if(StringUtils.isNotBlank(q)) {
//			hql = hql +  " where corpName like '%" + q + "%'";
		}
		
		List list = baseService.getList(hql);
		List returnList = new ArrayList();
		for(int i=0;i<list.size();i++){
			  Address a = (Address)list.get(i);
			  Map m = new HashMap();
			  m.put("id", a.getAddressId());
			  String name = a.getState()+a.getCity()+a.getCounty();
			  m.put("name",name+a.getAddress1()+"_"+Util.converterToFirstSpell(name));
			  returnList.add(m);
		 }
		 return returnList;
	}
}
