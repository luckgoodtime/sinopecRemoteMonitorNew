package com.lng.action.infrastructure;

import java.util.ArrayList;
import java.util.Date;
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
import com.lng.model.base.EndPoint;
import com.lng.model.base.Person;
import com.lng.model.base.party.Party;
import com.lng.service.base.AddressService;
import com.lng.service.base.EndPointService;
import com.lng.util.Const;
import com.lng.util.Util;

@Controller
@RequestMapping("/endPoint")
public class EndPointController extends BaseController{

	public static final String DIR = "infrastructure/endPoint/";
	public static final String R = "redirect:/endPoint/list.do";
	
	@Resource
	private EndPointService endPointService;
//	@Resource
//	private MessageSender messageSender;
	@Resource
	private AddressService addressService;
	
	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(Model model, HttpSession session) {
		
		model.addAttribute("p", endPointService.getListForPageDefault());
		return DIR + "list";
	}
	
	/**
	 * @category ajax公司列表
	 */
	@RequestMapping("/listAll.do")
	@ResponseBody
	public List listAll(HttpServletRequest request) {
		
		String q = request.getParameter("q");
		String hql = "from EndPoint";
		
		if(StringUtils.isNotBlank(q)) {
			hql = hql +  " where pointName like '%" + q + "%' or fullPinyin like '%" + q + "%' or simplePinyin like '%" + q + "%'";
		}
		
		List endPointList = endPointService.getList(hql);
		List returnList = new ArrayList();
		
		for(int i=0;i<endPointList.size();i++){
			EndPoint e = (EndPoint)endPointList.get(i);
			 
			 //DAC(Data Access Control) 
			 
			 Map m = new HashMap();
			 m.put("id", e.getPartyId());
			 m.put("name", e.getPointName());
			 m.put("py", (e.getSimplePinyin() == null?Util.converterToFirstSpell(e.getPointName()):e.getSimplePinyin() ));
			 returnList.add(m);
			  
		 }
		 return returnList;
	}

	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Model model, EndPoint o, HttpSession session) {
		if (o.getPartyId() !=null) {
			EndPoint ep = (EndPoint)endPointService.get(EndPoint.class, o.getPartyId());
			if(ep.getAddressId()!=null){
				model.addAttribute("address",this.baseService.get(Address.class, ep.getAddressId()));
			}
			model.addAttribute("o", ep);
		}
		//地址列表
//		model.addAttribute("addressList", addressService.getList());
		return DIR + "addOrUpdate";
	}

	/**
	 * @category 新增或更新
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(EndPoint o, Address address,HttpServletRequest request, HttpSession session) {

		// 新增
		Person user = (Person) session.getAttribute("person");
		Party p = new Party();
		p.setCreatedByUser(user.getPartyId());
		p.setCreatedDate(Util.dateToStr2(new Date()));
		p.setDescription("EndPoint");
		p.setPartyTypeId(Const.PARTY_TYPE_ENDPOINT);
		p.setStatusId(Const.PARTY_STATUS_USE);
		endPointService.save(p);
		
		o.setPartyId(p.getPartyId());
		
		//创建地址
		baseService.save(address);
		o.setAddressId(address.getAddressId());	
				
		endPointService.save(o);
		
	
		return R;
	}
	
	/**
	 * @category 更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(EndPoint o, Address address,HttpServletRequest request, HttpSession session) {

		//修改
		EndPoint o2 =  (EndPoint)endPointService.get(EndPoint.class, o.getPartyId());
		if(o2!=null){
			o2.setPointName(o.getPointName());
			o2.setPointShortName(o.getPointShortName());
			o2.setPointType(o.getPointType());
			
			//更新地址
			if(o2.getAddressId()!=null){
				Address currentAddress =  (Address)baseService.get(Address.class, o2.getAddressId());
				if(currentAddress!=null){
					currentAddress.setTel(address.getTel());
					currentAddress.setMobile(address.getMobile());
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
			
			endPointService.update(o2);
			
			//TODO 测试jms
//			String msg = "id:"+o.getPartyId()+" 名称:"+o.getPointName()+" 类型:"+o.getPointType();
//			messageSender.sendMessage(msg);
		}
	
		return R;
	}

	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del")
	public String del(String ids) {

		if (StringUtils.isNotBlank(ids)) {
			endPointService.del(ids);
		}
		return R;
	}


}
