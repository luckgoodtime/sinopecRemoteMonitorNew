package com.lng.action.infrastructure;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lng.action.main.BaseController;
import com.lng.exception.BusinessException;
import com.lng.model.base.Address;
import com.lng.model.base.Corporation;
import com.lng.model.base.Person;
import com.lng.model.base.party.Party;
import com.lng.model.biz.partner.Customer;
import com.lng.model.biz.partner.Supplier;
import com.lng.model.biz.partner.Transporter;
import com.lng.service.base.CorporationService;
import com.lng.util.Const;
import com.lng.util.Util;


/**
 */
@Controller
@RequestMapping("/corporation")
@SuppressWarnings({"unchecked"})
public class CorporationController extends BaseController{

	public static final String DIR = "infrastructure/corporation/";
	public static final String R = "forward:/corporation/list.do";
	
	@Resource
	private CorporationService corporationService;
	
	@InitBinder    
    public void initBinder(WebDataBinder binder) {    
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
        dateFormat.setLenient(true);    
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
    }
	
	/**
	 * @category ajax公司列表
	 */
	@RequestMapping("/listAll.do")
	@ResponseBody
	public List listAll(HttpServletRequest request) {
		
		String q = request.getParameter("q");
		String hql = "from Corporation";
		if(StringUtils.isNotBlank(q)) {
			hql = hql +  " where corpName like '%" + q + "%' or simplePinyin like '%" + q + "%' or fullPinyin like '%" + q + "%'" ;
		}
		
		List corporationList = corporationService.getList(hql);
		List returnList = new ArrayList();
		for(int i=0;i<corporationList.size();i++){
			 Corporation c = (Corporation)corporationList.get(i);
			  Map m = new HashMap();
			  m.put("id", c.getPartyId());
			  m.put("name", c.getCorpName()+"_"+Util.converterToFirstSpell(c.getCorpName()));
			  returnList.add(m);
		 }
		 return returnList;
	}
	
	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(Model model, HttpSession session) {
		
		model.addAttribute("p", corporationService.getListForPageDefault());
		return DIR + "list";
	}
	
	
	/**
	 * @category 客户信息
	 */
	@RequestMapping("/listinfor.do")
	public String list4Ajax(Model model, HttpServletRequest request, HttpSession session) {
		String p = request.getParameter("p");//plaintext,xml,json.
		if(p == null || p.length() == 0)
			p = "plaintext";
		
		String q = request.getParameter("q");
		
		String hql = "from Corporation";
		if(StringUtils.isNotBlank(q)) {
			hql = hql +  " where corpName like '%" + q + "%' or simplePinyin like '%" + q + "%' or fullPinyin like '%" + q + "%'" ;
		}
		
		System.out.println("--------------hql-::" + hql);
		
		model.addAttribute("p", corporationService.getListForPageDefault(hql));
		return DIR + "list_" + p;
	}	
	
	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")	
	public String addOrUpdate(Model model, Corporation o, HttpSession session) {
		if (o.getPartyId() !=null) {
			Corporation corporation = (Corporation)baseService.get(Corporation.class, o.getPartyId());
			model.addAttribute("o", corporation);
			if(corporation.getAddressId() != null) {
				model.addAttribute("address", baseService.get(Address.class, corporation.getAddressId()));
			}

		}
		return DIR + "addOrUpdate";
	}
	/**
	 * @category 对外新增
	 */
	@RequestMapping(value = "/addForOut")	
	public String addForOut() {
		return DIR + "addOrUpdate";
	}
	

	/**
	 * @throws ParseException 
	 * @throws BusinessException 
	 * @category 新增或更新
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(Model model,RedirectAttributes attr, Corporation o, Address address, HttpServletRequest request, HttpSession session) 
			throws ParseException, BusinessException{

		//检查是否有已经存在同名公司
		List list = this.corporationService.getListByName(o.getCorpName());
		if(list != null && list.size() > 0) {
			throw new BusinessException("有已经存在同名公司"); 
		}
		
		// 新增
		Person person = (Person) session.getAttribute("person");
		
		Party p = new Party();
		p.setCreatedByUser(person.getPartyId());
		p.setCreatedDate(Util.dateToStr2(new Date()));
		p.setDescription("Corporation");
		p.setPartyTypeId(Const.PARTY_TYPE_CORP);
		p.setStatusId(Const.PARTY_STATUS_USE);
		baseService.save(p);
		
		//创建地址			
		baseService.save(address);
		o.setAddressId(address.getAddressId());
		o.setAddressDes(address.getAddressDes());
		o.setPartyId(p.getPartyId());
		o.setFullPinyin(Util.converterToSpell(o.getCorpShortName()));
		o.setSimplePinyin(Util.converterToFirstSpell(o.getCorpShortName()));
		baseService.save(o);	
		//更新地址
		address.setCorpNames(o.getCorpName());
		baseService.update(address);
		
		
		//创建伙伴关系
		if("供应商".equals(request.getParameter("supplier"))){
			Supplier s = new Supplier();
			s.setBelong2CorpPartyId(person.getCorpPartyId());
			s.setCorpPartyId(o.getPartyId());
			s.setCreateDate(Util.dateToStr2(new Date()));
			s.setCreateParty(person.getPartyId());
			baseService.save(s);
			
			//创建反关系，我是对方的客户
			Customer c = new Customer();
			c.setCusName(o.getCorpName());
			c.setBelgon2PartyId(o.getPartyId());
			c.setCorpPartyId(person.getCorpPartyId());
			c.setCreateDate(Util.dateToStr2(new Date()));
			c.setCreateParty(person.getPartyId());
			baseService.save(c);
			
		}
		if("客商".equals(request.getParameter("customer"))){
			Customer c = new Customer();
			c.setBelgon2PartyId(person.getCorpPartyId());
			c.setCorpPartyId(o.getPartyId());
			c.setCreateDate(Util.dateToStr2(new Date()));
			c.setCreateParty(person.getPartyId());
			baseService.save(c);
			
			//创建反关系，我是对方的供应商
			Supplier s = new Supplier();
			s.setBelong2CorpPartyId(o.getPartyId());
			s.setCorpPartyId(person.getCorpPartyId());
			s.setCreateDate(Util.dateToStr2(new Date()));
			s.setCreateParty(person.getPartyId());
			baseService.save(s);
		}
		if("运输商".equals(request.getParameter("transport"))){
			Transporter t = new Transporter();
			t.setBelgon2PartyId(person.getCorpPartyId());
			t.setCorpPartyId(o.getPartyId());
			t.setCreateDate(Util.dateToStr2(new Date()));
			t.setCreateParty(person.getPartyId());
			
			baseService.save(t);
		}
		
		suc(null);//提示操作成功
		
		//是否添加该公司人员
		String goOnAddPerson = request.getParameter("goOnAddPerson");
		if(!"true".equals(goOnAddPerson))
			return R;
		else {
			return AllPersonController.DIR + "addOrUpdate";
		}
		

	}
	
	/**
	 * @category 对外新增保存
	 */
	@RequestMapping(value = "/addForOutDo")
	public String addForOutDo(Model model,RedirectAttributes attr, Corporation o, Address address, HttpServletRequest request, HttpSession session) 
			throws ParseException, BusinessException{

		//1、验证
		if(StringUtils.isBlank(o.getCorpName())){
			return err("公司不能空");
		}
		//检查是否有已经存在同名公司
		List list = this.corporationService.getListByName(o.getCorpName());
		if(list.size() > 0) {
			return err("有已经存在同名公司");
		}
		
		//2、创建地址；公司；人员
		baseService.save(address);
		o.setAddressId(address.getAddressId());
		o.setAddressDes(address.getAddressDes());
		o.setFullPinyin(Util.converterToSpell(o.getCorpShortName()));
		o.setSimplePinyin(Util.converterToFirstSpell(o.getCorpShortName()));
		
		baseService.save(o);	
		
		return suc("公司不能空");

	}
	
	/**
	 * @throws ParseException 
	 * @throws BusinessException 
	 * @category 更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(@Valid @ModelAttribute("o")Corporation o, Errors errors, Address address, HttpServletRequest request) 
			throws ParseException, BusinessException{

		if (errors.hasErrors()) return VALIDATE_ERROR;
		
		// 修改
		Corporation o2 =  (Corporation)baseService.get(Corporation.class, o.getPartyId());
		
		if(o2 != null){				
			o2.setCorpName(o.getCorpName());
			o2.setCorpShortName(o.getCorpShortName());
			o2.setSelfAccounting(o.isSelfAccounting());
			o2.setParentCorp(o.getParentCorp());
			o2.setLicense(o.getLicense());
			o2.setCorporationTax(o.getCorporationTax());
			o2.setBuildDate(o.getBuildDate());
			o2.setWebUrl(o.getWebUrl());
			o2.setEmail(o.getEmail());
			o2.setWebChatId(o.getWebChatId());
			o2.setDetail(o.getDetail());
			o2.setFullPinyin(Util.converterToSpell(o.getCorpShortName()));
			o2.setSimplePinyin(Util.converterToFirstSpell(o.getCorpShortName()));

			if(o2.getAddressId() != null) {
				//更新地址
				Address currentAddress =  (Address)baseService.get(Address.class, o2.getAddressId());
				currentAddress.setState(address.getState());
				currentAddress.setCity(address.getCity());
				currentAddress.setCounty(address.getCounty());
				currentAddress.setAddress1(address.getAddress1());			
				currentAddress.setPostalCode(address.getPostalCode());
				//这里要利用消息中间件进行地址变更消息的发布（后续改进）
				o2.setAddressDes(currentAddress.getAddressDes());
				corporationService.update(currentAddress);					
			}  else {
				//创建地址						
				baseService.save(address);
				o2.setAddressId(address.getAddressId());
				o2.setAddressDes(address.getAddressDes());
			}
			corporationService.update(o2);
			
			suc(null);//提示操作成功

		}
		
		//是否添加该公司人员
		String goOnAddPerson = request.getParameter("goOnAddPerson");
		if(!"true".equals(goOnAddPerson))
			return R;
		else {
			
			
			return AllPersonController.DIR + "addOrUpdate";
		}

	}
	
	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del")
	public String del(String ids) {

		if (StringUtils.isNotBlank(ids)) {
			corporationService.del(ids);
			suc(null);//提示操作成功
		}
		return R;
	}
	
}
