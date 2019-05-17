package com.lng.service.biz.order.partner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lng.model.base.Person;
import com.lng.model.biz.partner.Customer;
import com.lng.service.main.BaseService;
import com.lng.util.Const;
import com.lng.util.Page;
import com.lng.util.Util;

@Service
@SuppressWarnings("rawtypes")
public class CustomerService extends BaseService {

	public Page getListForPageDefault(Customer customer, boolean bothSideMatch) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Person person = (Person)request.getSession().getAttribute("person");
		
		List<Object> paramList = new ArrayList<Object>();
		
		StringBuffer hql = new StringBuffer();
		
		hql.append("from Customer c where c.belgon2PartyId=?");
		paramList.add(person.getCorpPartyId());
		
		if(StringUtils.isNotBlank(customer.getGasCardNo())) {
			hql.append(" and (gasCardNo like ? or parentSerialNo like ?)");
			paramList.add( "%" + customer.getGasCardNo() + (bothSideMatch?"%":""));
			paramList.add( "%" + customer.getGasCardNo() + (bothSideMatch?"%":""));
		}
		
		if(StringUtils.isNotBlank(customer.getCusName())) {
			hql.append(" and (cusName like ? or parentCusName like ?)");
			paramList.add( "%" + customer.getCusName() + "%");
			paramList.add( "%" + customer.getCusName() + "%");
		}
		
		if(StringUtils.isNotBlank(customer.getCusContactTel())) {
			hql.append(" and cusContactTel like ?");
			paramList.add( "%" + customer.getCusContactTel() + "%");
		}
		
		if(StringUtils.isNotBlank(customer.getReceiptPlace())) {
			hql.append(" and receiptPlace like ?");
			paramList.add("%" + customer.getReceiptPlace() + "%");
		}
		
		hql.append(" order by c.id asc");
		
		return this.getListForPageDefault(hql.toString(),paramList.toArray());
	}
	
	public int del(String ids) {
		//return this.executeUpdate("delete from Customer where id in (" + ids + ")");
		return this.executeUpdate("update Customer set state='" + Const.ORDER_STATE_DEL + "' where id in (" + ids + ")");
	}
	
	public List getCustomerListByGasCardNo(String gasCardNo, Integer belgon2PartyId) {		
		return this.getList("from Customer where gasCardNo=? and belgon2PartyId=?",new Object[]{gasCardNo, belgon2PartyId});
	}
	
	
	public List getCustomerListByContactIdNo(Integer corpPartyId, String cusContactIdNo) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Person person = (Person)request.getSession().getAttribute("person");
		return this.getList("from Customer  where (corpPartyId=? or cusContactIdNo=?)  and belgon2PartyId=?",new Object[]{corpPartyId, cusContactIdNo, person.getCorpPartyId()});
	}
	
	public List getCustomerListByOpenId(String openId) {
		Criteria criteria = super.getCriteria(Customer.class);
		criteria.add(Restrictions.ilike("bindOpenIds", openId, MatchMode.ANYWHERE));
		return criteria.list();
	}
	
	
	
	public List<Customer> getCustomerListByPhoneNo(String phoneNo) {
		return this.getList("from Customer  where cusContactTel like ? order by buytimes desc",new Object[]{"%" + phoneNo + "%"});
	}
	
	public List<Customer> getCustomerListByGasCardNo(String gasCardNo) {
		return this.getList("from Customer  where gasCardNo=? order by buytimes desc",new Object[]{gasCardNo});
	}

	/**
	 * 解析省市县，如果没有省市，则设置默认省市
	 * @param customerList
	 */
	public void setProvinCityCounty(List<Customer> customerList) {
		
		if(customerList != null && customerList.size() >0) {
			for (Customer customer : customerList) {
				String receiptPlace = customer.getReceiptPlace();//送气地址
				if(StringUtils.isNotBlank(receiptPlace)) {
					List<Map<String,String>> addressList= Util.addressResolution(receiptPlace);
					if(addressList.size() >0) {
						Map<String,String> addressMap = addressList.get(0);
						String province = addressMap.get("province");//省
						String city = addressMap.get("city");//市
						String county = addressMap.get("county");//县
						String town = addressMap.get("town");//镇
						String village = addressMap.get("village");//村庄（地址其他部分）
						
						if(StringUtils.isBlank(province)) {
							customer.setProvince("浙江省");//设置默认省 
						}else {
							customer.setProvince(province);
						}
						
						if(StringUtils.isBlank(city)) {
							customer.setCity("台州市");//设置默认市
						}else {
							customer.setCity(city);
						}
						
						if(StringUtils.isBlank(county)) {
							//没有解析到县，说明数据没有市，设置默认市和县
							customer.setCity("台州市");
							customer.setCounty("临海市");
						}else {
							customer.setCounty(county);
						}
						
						if(StringUtils.isNotBlank(town)) {//设置镇
							customer.setTown(town);
						}else {
							customer.setTown("");
						}
						
						if(StringUtils.isNotBlank(village)) {//设置村（其他地址）
							customer.setVillage(village);
						}
					}else {//没有解析出地址
						customer.setTown("");
						customer.setVillage(receiptPlace);
					}
				}
			}
		}
			
	}
	
}