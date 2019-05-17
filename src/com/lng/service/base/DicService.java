package com.lng.service.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.lng.model.base.Dic;
import com.lng.service.main.BaseService;


@Service
public class DicService extends BaseService {
	
	public Map<String,Object> getListForPageDefaultAjax(Dic dic) {
		
		StringBuffer hql = new StringBuffer("from Dic t where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(dic.getType())) {
			hql.append(" and type like ?");
			paramList.add( dic.getType() + "%");
		}

		
		hql.append(" order by id desc");
		
		return  this.getListForPageDefaultAjax(hql.toString(),paramList.toArray());
	}

	/**
	 * 根据条件查询数据字典
	 * @param dic
	 * @return
	 */
	public List<Dic> getDicList(Dic dic) {
		
		StringBuffer hql = new StringBuffer("from Dic t where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(dic.getType())) {
			hql.append(" and type = ?");
			paramList.add(dic.getType());
		}

		//TODO 加缓存
		return  this.getList(hql.toString(),paramList.toArray());
	}
	
	
	

}
