package com.lng.service.biz.order;

import java.util.List;
import org.springframework.stereotype.Service;
import com.lng.model.base.Corporation;
import com.lng.service.main.BaseService;
import com.lng.util.Page;

@Service
public class ArticleService extends BaseService {

	public Page getListForPageDefault() {
		return this.getListForPageDefault("from Article order by id desc");
	}
	
	public int del(String ids) {
		return this.executeUpdate("delete from Article where id in (" + ids + ")");
	}
	
	
	public List getList(int num) {
		return this.getList("from Article order by id desc ",num);
	}
}
