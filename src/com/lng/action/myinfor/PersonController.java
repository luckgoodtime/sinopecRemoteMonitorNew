package com.lng.action.myinfor;


import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lng.action.main.BaseController;
import com.lng.model.base.Address;
import com.lng.model.base.Corporation;
import com.lng.model.base.Person;
import com.lng.model.base.party.Party;
import com.lng.service.base.PersonService;
import com.lng.util.Const;
import com.lng.util.Util;

/**
 */
@Controller
@RequestMapping("/person")
public class PersonController extends BaseController {

	public static final String DIR = "myinfor/person/";
	public static final String R = "redirect:/person/list.do";
	
	@Resource
	private PersonService personService;

	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(Model model, HttpSession session) {
		
//		if (Util.isMobile()) {
//			//移动端
//			System.out.println("我是移动");
//		} else {
//			//pc
//			System.out.println("我是pc");
//		}
		
		Person person = (Person) session.getAttribute("person");
		model.addAttribute("p", personService.getListForPageDefaultPerson(person.getCorpPartyId()));
		return DIR + "list";
	}

	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Model model, Person o, HttpSession session) {
		if (o.getPartyId() !=null) {
			model.addAttribute("o", baseService.get(Person.class, o.getPartyId()));
		}
		return DIR + "addOrUpdate";
	}

	/**
	 * @category 新增
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(Person o,HttpServletRequest request, HttpSession session)  {

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
		o.setCorpPartyId(person.getCorpPartyId());
		personService.save(o);
	
		return R;
	}
	
	/**
	 * @throws ParseException 
	 * @category 新增或更新
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
			o2.setQQ(o.getQQ());
			o2.setMobile(o.getMobile());
			o2.setEmail(o.getEmail());
			o2.setIsOpen(o.getIsOpen());
			
			
			personService.update(o2);
		}
	
		return R;
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
	
	
	
	/**
	 * @category 我的信息--》基本信息
	 */
	@RequestMapping(value = "/baseInfo.do")
	public String baseInfo(Model model, HttpSession session) {
//		String title = SpringUtil.getProperty("title");
		//公司所在地址
		
		Person person = (Person)session.getAttribute("person");		
		Corporation corporation = (Corporation)person.getCorp();		
		if(corporation != null  &&  corporation.getAddressId() != null) {
			model.addAttribute("corporation", corporation);
			model.addAttribute("address", baseService.get(Address.class, corporation.getAddressId()));
		}
		
		return DIR + "baseInfo";
	}
}