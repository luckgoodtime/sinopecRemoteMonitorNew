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
import com.lng.util.Util;

@Service
public class GasStationService extends BaseService {

	//加气记录
	public Map<String, Object> getListForFillingRecord(FillingRecord fillingRecord, Person person) {

		StringBuffer hql = new StringBuffer("from FillingRecord t where 1=1");
		List<Object> paramList = new ArrayList<Object>();

		if (StringUtils.isNotBlank(fillingRecord.getHolderName())) {
			hql.append(" and holderName like ?");
			paramList.add(fillingRecord.getHolderName() + "%");
		}
		if (StringUtils.isNotBlank(fillingRecord.getFillTimeBegin())) {
			hql.append(" and fillTime >= ?");
			paramList.add(Util.strtoDate(fillingRecord.getFillTimeBegin()));
		}
		if (StringUtils.isNotBlank(fillingRecord.getFillTimeEnd())) {
			hql.append(" and fillTime <= ?");
			paramList.add(Util.strtoDate(fillingRecord.getFillTimeEnd()));
		}
		if (StringUtils.isNotBlank(fillingRecord.getPlateNo())) {
			hql.append(" and plateNo like ?");
			paramList.add(fillingRecord.getPlateNo() + "%");
		}
		if (StringUtils.isNotBlank(fillingRecord.getCardType())) {
			hql.append(" and cardType = ?");
			paramList.add(fillingRecord.getCardType());
		}
		if (StringUtils.isNotBlank(fillingRecord.getFillType())) {
			hql.append(" and fillType = ?");
			paramList.add(fillingRecord.getFillType());
		}
		
		
		
		

		hql.append(" order by id desc");

		return this.getListForPageDefaultAjax(hql.toString(), paramList.toArray());
	}

	
	//价格表
	public Map<String, Object> getListForPriceList(PriceList priceList, Person person) {

		StringBuffer hql = new StringBuffer("from PriceList t where 1=1");
		List<Object> paramList = new ArrayList<Object>();

		if (StringUtils.isNotBlank(priceList.getGunNo())) {
			hql.append(" and gunNo like ?");
			paramList.add(priceList.getGunNo() + "%");
		}
		if (StringUtils.isNotBlank(priceList.getPublishTimeBegin())) {
			hql.append(" and publishTime >= ?");
			paramList.add(Util.strtoDate(priceList.getPublishTimeBegin()));
		}
		if (StringUtils.isNotBlank(priceList.getPublishTimeEnd())) {
			hql.append(" and publishTime <= ?");
			paramList.add(Util.strtoDate(priceList.getPublishTimeEnd()));
		}
		if (StringUtils.isNotBlank(priceList.getStationName())) {
			hql.append(" and stationName like ?");
			paramList.add(priceList.getStationName() + "%");
		}
		if (StringUtils.isNotBlank(priceList.getGunNo())) {
			hql.append(" and gunNo like ?");
			paramList.add(priceList.getGunNo() + "%");
		}
		
		hql.append(" order by id desc");

		return this.getListForPageDefaultAjax(hql.toString(), paramList.toArray());
	}

	//充值表
	public Map<String, Object> getListForRechargeRecord(RechargeRecord rechargeRecord, Person person) {

		StringBuffer hql = new StringBuffer("from RechargeRecord t where 1=1");
		List<Object> paramList = new ArrayList<Object>();

		if (StringUtils.isNotBlank(rechargeRecord.getCarNo())) {
			hql.append(" and carNo like ?");
			paramList.add(rechargeRecord.getCarNo() + "%");
		}
		if (StringUtils.isNotBlank(rechargeRecord.getOperateTimeBegin())) {
			hql.append(" and operateTime >= ?");
			paramList.add(Util.strtoDate(rechargeRecord.getOperateTimeBegin()));
		}
		if (StringUtils.isNotBlank(rechargeRecord.getOperateTimeEnd())) {
			hql.append(" and operateTime <= ?");
			paramList.add(Util.strtoDate(rechargeRecord.getOperateTimeEnd()));
		}
		if (StringUtils.isNotBlank(rechargeRecord.getStationName())) {
			hql.append(" and stationName like ?");
			paramList.add(rechargeRecord.getStationName() + "%");
		}
		if (StringUtils.isNotBlank(rechargeRecord.getHolderName())) {
			hql.append(" and holderName like ?");
			paramList.add(rechargeRecord.getHolderName() + "%");
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
