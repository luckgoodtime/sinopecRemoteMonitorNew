package com.lng.service.gasStation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.lng.model.base.Person;
import com.lng.model.gasStation.FillingRecord;
import com.lng.model.gasStation.PriceList;
import com.lng.model.gasStation.RechargeRecord;
import com.lng.model.gasStation.ShiftRecord;
import com.lng.service.main.BaseService;

@Service
public class GasStationService extends BaseService {

	public Map<String, Object> getListForFillingRecord(FillingRecord fillingRecord, Person person) {

		StringBuffer hql = new StringBuffer("from FillingRecord t where 1=1");
		List<Object> paramList = new ArrayList<Object>();

		if (StringUtils.isNotBlank(fillingRecord.getHolderName())) {
			hql.append(" and holderName like ?");
			paramList.add(fillingRecord.getHolderName() + "%");
		}

		hql.append(" order by id desc");

		return this.getListForPageDefaultAjax(hql.toString(), paramList.toArray());
	}

	public Map<String, Object> getListForPriceList(PriceList priceList, Person person) {

		StringBuffer hql = new StringBuffer("from PriceList t where 1=1");
		List<Object> paramList = new ArrayList<Object>();

		if (StringUtils.isNotBlank(priceList.getGunNo())) {
			hql.append(" and gunNo like ?");
			paramList.add(priceList.getGunNo() + "%");
		}

		hql.append(" order by id desc");

		return this.getListForPageDefaultAjax(hql.toString(), paramList.toArray());
	}

	public Map<String, Object> getListForRechargeRecord(RechargeRecord rechargeRecord, Person person) {

		StringBuffer hql = new StringBuffer("from RechargeRecord t where 1=1");
		List<Object> paramList = new ArrayList<Object>();

		if (StringUtils.isNotBlank(rechargeRecord.getCarNo())) {
			hql.append(" and carNo like ?");
			paramList.add(rechargeRecord.getCarNo() + "%");
		}

		hql.append(" order by id desc");

		return this.getListForPageDefaultAjax(hql.toString(), paramList.toArray());
	}

	public Map<String, Object> getListForShiftRecord(ShiftRecord shiftRecord, Person person) {

		StringBuffer hql = new StringBuffer("from ShiftRecord t where 1=1");
		List<Object> paramList = new ArrayList<Object>();

		if (StringUtils.isNotBlank(shiftRecord.getShiftNo())) {
			hql.append(" and shiftNo like ?");
			paramList.add(shiftRecord.getShiftNo() + "%");
		}

		hql.append(" order by id desc");

		return this.getListForPageDefaultAjax(hql.toString(), paramList.toArray());
	}

}
