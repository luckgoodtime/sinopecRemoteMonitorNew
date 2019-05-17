package com.lng.action.infrastructure;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lng.action.main.BaseController;
import com.lng.model.base.Corporation;
import com.lng.model.base.Person;
import com.lng.model.base.PersonRole;
import com.lng.model.base.party.Party;
import com.lng.service.base.PersonService;
import com.lng.service.base.RoleService;
import com.lng.util.Const;
import com.lng.util.Util;


@Controller
@RequestMapping("/allPerson")
public class AllPersonController extends BaseController {

	public static final String DIR = "infrastructure/allPerson/";
	public static final String R = "redirect:/allPerson/list.do";
	
	@Resource
	private PersonService personService;
	@Resource
	private RoleService roleService;
	
	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(Model model, HttpSession session) {
		
		model.addAttribute("p", personService.getListForPageDefaultPerson(null));
		return DIR + "list";
	}

	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Model model, Person o, HttpSession session) {
		if (o.getPartyId() !=null) {
			Person p = (Person)baseService.get(Person.class, o.getPartyId());
			if(p.getCorpPartyId()!=null){
				model.addAttribute("c",baseService.get(Corporation.class, p.getCorpPartyId()));
			}
			model.addAttribute("o",p);
		}
		
		//获取角色列表
		model.addAttribute("roleList",roleService.getRoleList(o.getPartyId()));
		
		return DIR + "addOrUpdate";
	}

	/**
	 * @throws ParseException 
	 * @category 新增
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(Model model,RedirectAttributes attr, Person o,HttpServletRequest request, HttpSession session) throws ParseException {

		// 新增
		Person person = (Person) session.getAttribute("person");

		Party p = new Party();
		p.setCreatedByUser(person.getPartyId());
		p.setCreatedDate(Util.dateToStr2(new Date()));
		p.setDescription("Person");
		p.setPartyTypeId(Const.PARTY_TYPE_PERSON);
		p.setStatusId(Const.PARTY_STATUS_USE);
		personService.save(p);
		
		o.setPartyId(p.getPartyId());
		if("是".equals(o.getDefaultPwd())){
			o.setPassword(Util.md5(Const.DEFAULT_PWD));
		}
		personService.save(o);
		
		saveRoles(request,o);
		
		return R;
	}
	
	
	/**
	 * @throws ParseException 
	 * @category 更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(Model model,RedirectAttributes attr, Person o,HttpServletRequest request, HttpSession session) throws ParseException {

		//修改
		Person o2 =  (Person)baseService.get(Person.class, o.getPartyId());
		if(o2!=null){
			o2.setBirthDate(o.getBirthDate());
			o2.setSalutation(o.getSalutation());
			o2.setFirstName(o.getFirstName());
			o2.setNickname(o.getNickname());
			o2.setGender(o.getGender());
			o2.setYearsWithEmployer(o.getYearsWithEmployer());
			o2.setBirthDate(o.getBirthDate());
			o2.setCorpPartyId(o.getCorpPartyId());
			o2.setMobile(o.getMobile());
			o2.setQQ(o.getQQ());
			o2.setEmail(o.getEmail());
			if("是".equals(o.getDefaultPwd())){
				o.setPassword(Util.md5(Const.DEFAULT_PWD));
			}
			personService.update(o2);
			
			//删除以前的角色
			baseService.executeUpdate("delete from PersonRole where personId=?", new Object[]{o2.getPartyId()});
			
			saveRoles(request,o);
		}
		
		
		
		return R;
	}
	//保存角色
	private void saveRoles(HttpServletRequest request,Person o){
		String[] roleIds = request.getParameterValues("roleIds");
		if(roleIds!=null&&roleIds.length>0){
			List<PersonRole> objList = new ArrayList<PersonRole>();
			for(int i=0;i<roleIds.length;i++){
				PersonRole pr = new PersonRole();
				pr.setRoleId(Integer.valueOf(roleIds[i]));
				pr.setPersonId(o.getPartyId());
				objList.add(pr);
			}
			
			baseService.batchSaveOrUpdate(objList);
		}
	}

	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del")
	public String del(String ids) {

		if (StringUtils.isNotBlank(ids)) {
			personService.del(ids);
		}
		return R;
	}
	
	
	
}