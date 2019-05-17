package com.lng.action.infrastructure;


import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lng.action.main.BaseController;
import com.lng.model.base.Person;
import com.lng.model.base.Role;
import com.lng.model.base.party.PartyAttribute;
import com.lng.service.base.PartyAttributeService;

/**
 * PartyAttribute属性
 */
@Controller
@RequestMapping("/partyAttribute")
public class PartyAttributeController extends BaseController {
	
	
	public static final String DIR = "infrastructure/partyAttribute/";
	public static final String R = "forward:/partyAttribute/list.do";
	
	@Resource
	private PartyAttributeService partyAttributeService;	
	
	//列表
	@RequestMapping("/list.do")
	public String list() {
		return DIR + "list";
	}

	//ajax列表
	@RequestMapping("/ajaxList.do")
	@ResponseBody
	public Object ajaxList(PartyAttribute partyAttribute,HttpServletRequest request) {
		
		Person person = (Person) request.getSession().getAttribute("person");
		return partyAttributeService.getListForPageDefaultAjax(partyAttribute,person);
	}
	
	/**
	 *  进入新增页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(PartyAttribute o) {
		if (o.getPartyAttributeId() != null) {
			request.setAttribute(
					"o",
					partyAttributeService.get(PartyAttribute.class,
							o.getPartyAttributeId()));
		}
		return DIR + "addOrUpdate";
	}

	/**
	 * 新增页面保存
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(PartyAttribute o, HttpServletRequest request) {
		
		partyAttributeService.save(o);
		return R;
	}
	
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(PartyAttribute o) {

		if(o.getPartyAttributeId() != null) {
			PartyAttribute partyAttribute =  (PartyAttribute)partyAttributeService.get(PartyAttribute.class, o.getPartyAttributeId());
			if(partyAttribute != null) {
				
				partyAttribute.setAttrName(o.getAttrName());
				partyAttribute.setAttrValue(o.getAttrValue());
				partyAttribute.setPartyId(o.getPartyId());
				
				partyAttributeService.update(o);
			}
		}
		return R;
	}
	
	

	
	
}
