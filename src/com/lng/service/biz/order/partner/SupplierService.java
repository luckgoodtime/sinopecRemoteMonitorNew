package com.lng.service.biz.order.partner;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lng.model.base.Person;
import com.lng.service.main.BaseService;
import com.lng.util.Page;

@Service
public class SupplierService extends BaseService {

	public Page getListForPageDefault() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Person person = (Person)request.getSession().getAttribute("person");
		
		return this.getListForPageDefault("from Supplier s,Corporation c1,Person p " +
				"where s.corpPartyId=c1.partyId and s.createParty=p.partyId and s.belong2CorpPartyId=? order by s.id desc",new Object[]{person.getCorpPartyId()});
	}
	
	public int del(String ids) {
		return this.executeUpdate("delete from Supplier where id in (" + ids + ")");
	}
	public List getSupplierList(Integer corpPartyId) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Person person = (Person)request.getSession().getAttribute("person");
		return this.getList("from Supplier  where corpPartyId=? and belong2CorpPartyId=?",new Object[]{corpPartyId,person.getCorpPartyId()});
	}
	
}