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
public class ShippingOrderUnloadService extends BaseService {

	//分页查询
	public Page getListForPageDefault() {
		Page p = this.getListForPageDefault("from ShippingOrderUnload ");
		
		return p;
	}
	
	public int del(String ids) {
		return this.executeUpdate("delete from ShippingOrderUnload where id in (" + ids + ")");
		//TODO 更新运输单
	}
	
	
	
	
}