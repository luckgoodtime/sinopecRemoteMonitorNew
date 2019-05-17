package com.lng.service.base;

import org.springframework.stereotype.Service;

import com.lng.service.main.BaseService;
import com.lng.util.Page;

/**
 * 用户管理
 */
@Service
@SuppressWarnings("unchecked")
public class UserService extends BaseService {

	//分页查询
	public Page getListForPageDefault() {
		return this.getListForPageDefault("from User");
	}
	
	public int del(String ids) {
		return this.executeUpdate("delete from User where id in(" + ids + ")");
	}
}