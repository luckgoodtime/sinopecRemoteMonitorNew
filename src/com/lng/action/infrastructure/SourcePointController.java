package com.lng.action.infrastructure;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lng.action.main.BaseController;
import com.lng.model.base.Address;
import com.lng.model.base.Person;
import com.lng.model.base.SourcePoint;
import com.lng.model.base.party.Party;
import com.lng.service.base.AddressService;
import com.lng.service.base.SourcePointService;
import com.lng.util.Const;
import com.lng.util.Util;

@Controller
@RequestMapping("/sourcePoint")
public class SourcePointController extends BaseController{

	public static final String DIR = "infrastructure/sourcePoint/";
	public static final String R = "redirect:/sourcePoint/list.do";
	
	@Resource
	private SourcePointService sourcePointService;
	@Resource
	private AddressService addressService;
	
	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(Model model, HttpSession session) {
		
		model.addAttribute("p", sourcePointService.getListForPageDefault());
		
		
		return DIR + "list";
	}
	
	/**
	 * @category ajax公司列表
	 */
	@RequestMapping("/listAll.do")
	@ResponseBody
	public List listAll(HttpServletRequest request) {
		
		String q = request.getParameter("q");
		String hql = "from SourcePoint";
		
		if(StringUtils.isNotBlank(q)) {
			hql = hql +  " where sourceName like '%" + q + "%' or fullPinyin like '%" + q + "%' or simplePinyin like '%" + q + "%'";
		}
		
		List sourcePointList = sourcePointService.getList(hql);
		List returnList = new ArrayList();
		
		for(int i=0;i<sourcePointList.size();i++){
			SourcePoint e = (SourcePoint)sourcePointList.get(i);
			 
			 //DAC(Data Access Control) 
			 
			 Map m = new HashMap();
			 m.put("id", e.getPartyId());
			 m.put("name", e.getSourceName());
			 m.put("py", (e.getSimplePinyin() == null?Util.converterToFirstSpell(e.getSourceName()):e.getSimplePinyin() ));
			 returnList.add(m);
			  
		 }
		 return returnList;
	}
	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Model model, SourcePoint o, HttpSession session) {
		if (o.getPartyId() !=null) {
			SourcePoint sp = (SourcePoint)sourcePointService.get(SourcePoint.class, o.getPartyId());
			if(sp.getAddressId()!=null){
				model.addAttribute("address",this.baseService.get(Address.class, sp.getAddressId()));
			}
			model.addAttribute("o", sp);
			
		}
		//地址列表
//		model.addAttribute("addressList", addressService.getList());
		return DIR + "addOrUpdate";
	}

	/**
	 * @throws ParseException 
	 * @category 新增
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(@Valid @ModelAttribute("o")SourcePoint o, Address address, Errors errors,HttpServletRequest request, HttpSession session) {

		if (errors.hasErrors()) return VALIDATE_ERROR;
		// 新增
		Person person = (Person) session.getAttribute("person");
		Party p = new Party();
		p.setCreatedByUser(person.getPartyId());
		p.setCreatedDate(Util.dateToStr2(new Date()));
		p.setDescription("SourcePoint");
		p.setPartyTypeId(Const.PARTY_TYPE_SOURCEPOINT);
		p.setStatusId(Const.PARTY_STATUS_USE);
		sourcePointService.save(p);
		
		o.setPartyId(p.getPartyId());
		
		//创建地址			
		baseService.save(address);
		o.setAddressId(address.getAddressId());		
		sourcePointService.save(o);
	
		return R;
	}
	
	/**
	 * @throws ParseException 
	 * @category 更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(@Valid @ModelAttribute("o")SourcePoint o, Address address, Errors errors,HttpServletRequest request, HttpSession session) {

		if (errors.hasErrors()) return VALIDATE_ERROR;
		//修改
		SourcePoint o2 =  (SourcePoint)sourcePointService.get(SourcePoint.class, o.getPartyId());
		if(o2!=null){
			o2.setSourceName(o.getSourceName());
			o2.setSourceShortName(o.getSourceShortName());
			o2.setSourceType(o.getSourceType());
			o2.setDailyAbility(o.getDailyAbility());
			o2.setAnnualAbility(o.getAnnualAbility());
			o2.setCalorificValue(o.getCalorificValue());
			o2.setGasificationRate(o.getGasificationRate());
			
			//更新地址
			if(o2.getAddressId()!=null){
				Address currentAddress =  (Address)baseService.get(Address.class, o2.getAddressId());
				if(currentAddress!=null){
					currentAddress.setState(address.getState());
					currentAddress.setCity(address.getCity());
					currentAddress.setCounty(address.getCounty());
					currentAddress.setAddress1(address.getAddress1());
					this.baseService.update(currentAddress);
				}
			} else {
				baseService.save(address);
				o2.setAddressId(address.getAddressId());
			}
						
			
			sourcePointService.update(o2);
		}
	
		return R;
	}

	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del")
	public String del(String ids) {

		if (StringUtils.isNotBlank(ids)) {
			sourcePointService.del(ids);
		}
		return R;
	}


}
