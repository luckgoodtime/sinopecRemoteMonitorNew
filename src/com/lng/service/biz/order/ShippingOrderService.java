package com.lng.service.biz.order;

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
 * 运输单业务
 */
@Service
public class ShippingOrderService extends BaseService {

	//分页查询
	public Page getListForPageDefault() {
		String sql = "select s.id,c1.corpName,c2.corpName corpName2,s.truckNo,s.tankNo,s.driverName,s.driverTel,sp.sourceName,CONCAT(a.state,a.city,a.county,a.address1)" +
				",ep.pointName,CONCAT(a1.state,a1.city,a1.county,a1.address1)," +
				"  s.requiredString,s.unLoadOnRoad,s.planLoadingString,s.loadingTime ,s.loadingTW,s.loadingGW,s.loadingNW,s.settleWeight,s.state" +
				" from ShippingOrder s left join Corporation c1 on s.transportCorpPartyId=c1.partyId " +
				" left join Corporation c2 on s.transportOwner=c2.partyId " +
				" left join SourcePoint sp on s.sourcePartyId=sp.partyId left join Address a on sp.addressId=a.addressId " +
				" left join EndPoint ep on s.endPointPartyId=ep.partyId left join Address a1 on ep.addressId=a1.addressId ";
	
		Page p =  this.getListForPageDefaultSQL(sql);
		/*List personList =  p.getDataList();
		for(int i=0;i<personList.size();i++){
			Person person = (Person)personList.get(i);
			if(person.getCorpPartyId()!=null){
				person.setCorp((Corporation)this.get(Corporation.class, person.getCorpPartyId()));
			}
		}*/
		return p;
	}
	
	public int del(String ids) {
		return this.executeUpdate("delete from ShippingOrder where id in (" + ids + ")");
	}
	
	public List getList() {
		return this.getList("from ShippingOrder ");
	}
	
}