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
public class TransporterService extends BaseService {

	public Page getListForPageDefault() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Person person = (Person)request.getSession().getAttribute("person");
		
		return this.getListForPageDefault("from Transporter t,Corporation c1,Person p " +
				"where t.corpPartyId=c1.partyId and t.createParty=p.partyId and t.belgon2PartyId=? order by t.id desc",new Object[]{person.getCorpPartyId()});
	}
	
	public int del(String ids) {
		return this.executeUpdate("delete from Transporter where id in (" + ids + ")");
	}
	
	public List getTransporterList(Integer corpPartyId) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Person person = (Person)request.getSession().getAttribute("person");
		return this.getList("from Transporter  where corpPartyId=? and belgon2PartyId=?",new Object[]{corpPartyId,person.getCorpPartyId()});
	}
}