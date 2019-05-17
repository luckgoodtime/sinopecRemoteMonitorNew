package com.lng.service.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.lng.service.main.BaseService;
import com.lng.model.base.Person;
import com.lng.model.base.party.PartyAttribute;


@Service
public class PartyAttributeService extends BaseService {
	
	public Map<String,Object> getListForPageDefaultAjax(PartyAttribute partyAttribute,Person person) {
		
		StringBuffer hql = new StringBuffer("from PartyAttribute t where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(partyAttribute.getAttrName())) {
			hql.append(" and attrName like ?");
			paramList.add( partyAttribute.getAttrName() + "%");
		}

		
		hql.append(" order by attrName ,attrValue,partyId");
		
		return  this.getListForPageDefaultAjax(hql.toString(),paramList.toArray());
	}

	public String getPartyAttribute(Integer partyId, String attributeName) {
		
		List list = this.getList("from PartyAttribute t where t.partyId=" + partyId + " and t.attrName='" + attributeName + "'");
		
		String attribute = null;
		
		if(list != null && list.size() > 0) {
			attribute = ((PartyAttribute)list.get(0)).getAttrValue();
		}
		
		
		return attribute;
	} 
	
	public List getPartyAttributes(Integer partyId, String attributeName) {	
		
		return this.getList("from PartyAttribute t where t.partyId=" + partyId + " and t.attrName='" + attributeName + "'");
	}
	
	

}
