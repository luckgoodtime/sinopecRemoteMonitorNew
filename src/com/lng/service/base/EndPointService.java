package com.lng.service.base;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lng.service.main.BaseService;
import com.lng.util.Page;

@Service
public class EndPointService extends BaseService {

	public Page getListForPageDefault() {
		return this.getListForPageDefault("from EndPoint order by partyId desc");
	}
	
	public int del(String ids) {
		 this.executeUpdate("delete from EndPoint where partyId in (" + ids + ")");
		 return this.executeUpdate("delete from Party where partyId in (" + ids + ")");
	}
	public List getList() {
		return this.getList("from EndPoint ep ,Address a where ep.addressId=a.addressId order by ep.partyId desc");
	}
	
	
}