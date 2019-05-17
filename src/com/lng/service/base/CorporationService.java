package com.lng.service.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lng.model.base.Corporation;
import com.lng.model.base.Person;
import com.lng.model.biz.partner.Supplier;
import com.lng.service.main.BaseService;
import com.lng.util.Page;

@Service
public class CorporationService extends BaseService{
	
	//分页查询
	public Page getListForPageDefault() {
		return this.getListForPageDefault("from Corporation order by corpName");
	}
	
	public int del(String ids) {
		 this.executeUpdate("delete from Corporation where partyId in (" + ids + ")");
		 return this.executeUpdate("delete from Party where partyId in (" + ids + ")");
	}

	public List<Corporation> getList() {
		return this.getList("from Corporation order by partyId desc");
	}
	
	public List<Corporation> getSupplierList(Supplier s) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Person person = (Person)request.getSession().getAttribute("person");
		
		Object[] params;
		String hql = "from Corporation where partyId not in(select corpPartyId from Supplier where belong2CorpPartyId=? ";
		if(s!=null){//修改操作
			hql += " and id !=?";
			params = new Object[]{person.getCorpPartyId(),s.getId()};
		}else{
			params = new Object[]{person.getCorpPartyId()};
		}
		
		hql += " ) order by partyId desc";
		
		return this.getList(hql,params);
	}
	public List<Corporation> getListByName(String corporationName) {
		return this.getList("from Corporation where corpName=?",new Object[]{corporationName});
	}
}
