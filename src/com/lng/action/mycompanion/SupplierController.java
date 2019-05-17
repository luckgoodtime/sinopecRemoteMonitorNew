package com.lng.action.mycompanion;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.action.main.BaseController;
import com.lng.model.base.Person;
import com.lng.model.biz.partner.Supplier;
import com.lng.service.base.CorporationService;
import com.lng.service.base.PersonService;
import com.lng.service.biz.order.partner.SupplierService;
import com.lng.util.Util;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController{

	public static final String DIR = "mycompanion/supplier/";
	public static final String R = "redirect:/supplier/list.do";

	@Resource
	private SupplierService supplierService;
	@Resource
	private CorporationService corporationService;
	@Resource
	private PersonService personService;

	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(Model model, HttpSession session,HttpServletRequest request) {
		
//		if (Util.isMobile()) {
//			//移动端
//			System.out.println("我是移动");
//		} else {
//			//pc
//			System.out.println("我是pc");
//		}

		model.addAttribute("p", supplierService.getListForPageDefault());
		return DIR  + "list";
	}
	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Model model, Supplier o) {
		if (o.getId() != null) {
			Supplier s = (Supplier)supplierService.get(Supplier.class, o.getId());
			// 公司列表
			model.addAttribute("corporationList", corporationService.getSupplierList(s));
			model.addAttribute("o", s);
		}else{
			// 公司列表
			model.addAttribute("corporationList", corporationService.getSupplierList(null));
		}
		
		// 人员列表
		model.addAttribute("personServiceList", personService.getList());

		return DIR + "addOrUpdate";
	}
	/**
	 * @category 新增或更新
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(Supplier o, HttpSession session) {

		//排重
		List supplierList = supplierService.getSupplierList(o.getCorpPartyId());
		if(supplierList.size()>0){
			return err("不能重复创建");
		}
		// 新增
		Person person = (Person) session.getAttribute("person");
		o.setCreateDate(Util.dateToStr2(new Date()));
		o.setCreateParty(person.getPartyId());
		o.setBelong2CorpPartyId(person.getCorpPartyId());
		supplierService.save(o);
	
		return R;
	}
	
	/**
	 * @category 新增或更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(Supplier o, HttpSession session) {

		// 修改
		Supplier o2 = (Supplier) supplierService.get(Supplier.class, o.getId());
		if (o2 != null) {
			
			//排重
			if(!o2.getCorpPartyId().equals(o.getCorpPartyId())){
				List supplierList = supplierService.getSupplierList(o.getCorpPartyId());
				if(supplierList.size()>0){
					return err("不能重复创建");
				}
			}
			
			o2.setBizManager(o.getBizManager());
			o2.setBizStartDate(o.getBizStartDate());
			o2.setCorpPartyId(o.getCorpPartyId());
			o2.setSerialNo(o.getSerialNo());
			o2.setRegion(o.getRegion());

			supplierService.update(o2);
		}
	
		return R;
	}

	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del")
	public String del(String ids) {

		if (StringUtils.isNotBlank(ids)) {
			supplierService.del(ids);
		}
		return R;
	}

}
