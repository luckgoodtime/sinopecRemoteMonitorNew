package com.lng.service.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lng.model.base.Corporation;
import com.lng.model.base.Person;
import com.lng.service.main.BaseService;
import com.lng.util.Page;

/**
 */
@Service
@SuppressWarnings("unchecked")
public class PersonService extends BaseService {

	//分页查询
	public Page getListForPageDefaultPerson(Integer  corpPartyId) {
		String hql = "from Person p ";
		if(corpPartyId!=null){
			hql = hql + " where corpPartyId="+corpPartyId;
		}
		hql = hql + " order by p.partyId desc";
		Page p =  this.getListForPageDefault(hql);
		List personList =  p.getDataList();
		for(int i=0;i<personList.size();i++){
			Person person = (Person)personList.get(i);
			if(person.getCorpPartyId()!=null){
				person.setCorp((Corporation)this.get(Corporation.class, person.getCorpPartyId()));
			}
		}
		return p;
	}
	
	public int del(String ids) {
		 this.executeUpdate("delete from Person where partyId in (" + ids + ")");
		 return this.executeUpdate("delete from Party where partyId in (" + ids + ")");
	}
	
	public List<Person> getList() {
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Person person = (Person)request.getSession().getAttribute("person");
		
		return this.getList("from Person where corpPartyId=? order by partyId desc",new Object[]{person.getCorpPartyId()});
	}
	
	
	public List<Person> getListByOpenid(String openid) {
		return this.getList("from Person where openid=? order by partyId desc",new Object[]{openid});
	}
}