package com.lng.service.base;

import java.util.List;

import org.springframework.stereotype.Service;





import com.lng.model.base.Address;
import com.lng.service.main.BaseService;
import com.lng.util.Page;

@Service
public class AddressService extends BaseService{

	//分页查询
	public Page getListForPageDefault() {
		return this.getListForPageDefault("from Address order by state");
	}
	
	public int del(String ids) {
		//TODO 如果气源或终端使用，则不能删除
		 return this.executeUpdate("delete from Address where addressId in (" + ids + ")");
	}
	
	public List<Address> getList() {
		return this.getList("from Address order by addressId desc");
	}
	
}
