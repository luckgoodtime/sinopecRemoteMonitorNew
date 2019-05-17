package com.lng.service.base;

import org.springframework.stereotype.Service;

import com.lng.service.main.BaseService;
import com.lng.util.Page;

@Service
public class RouteService extends BaseService {

	public Page getListForPageDefault() {
		return this.getListForPageDefault("from Route r , Address a,Address a2 where r.addressId1=a.addressId and r.addressId2=a2.addressId order by r.id desc");
	}
	
	public int del(String ids) {
		return this.executeUpdate("delete from Route where id in (" + ids + ")");
	}
}