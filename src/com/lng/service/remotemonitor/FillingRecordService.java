package com.lng.service.remotemonitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.lng.model.base.Person;
import com.lng.model.remotemonitor.FillingRecord;
import com.lng.service.main.BaseService;


@Service
public class FillingRecordService extends BaseService {
	
	public Map<String,Object> getListForPageDefaultAjax(FillingRecord fillingRecord,Person person) {
		
		StringBuffer hql = new StringBuffer("from FillingRecord t where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		
		if(StringUtils.isNotBlank(fillingRecord.getHolderName())) {
			hql.append(" and holderName like ?");
			paramList.add(fillingRecord.getHolderName() + "%");
		}

		
		hql.append(" order by id desc");
		
		return  this.getListForPageDefaultAjax(hql.toString(),paramList.toArray());
	}

	

}
