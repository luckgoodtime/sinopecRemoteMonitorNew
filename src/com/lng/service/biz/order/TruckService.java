package com.lng.service.biz.order;

import java.util.List;
import org.springframework.stereotype.Service;
import com.lng.model.base.Corporation;
import com.lng.service.main.BaseService;
import com.lng.util.Page;

@Service
public class TruckService extends BaseService {

	public Page getListForPageDefault() {
		return this.getListForPageDefault("from Truck order by id desc");
	}
	
	public int del(String ids) {
		 this.executeUpdate("delete from Truck where partyId in (" + ids + ")");
		 return this.executeUpdate("delete from Party where partyId in (" + ids + ")");
	}
	
	
	public List getList() {
		return this.getList("from Truck ");
	}
}
