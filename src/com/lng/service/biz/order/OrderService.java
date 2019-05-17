package com.lng.service.biz.order;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lng.model.biz.BizOrder;
import com.lng.service.main.BaseService;
import com.lng.util.Const;
import com.lng.util.Page;
import com.lng.util.Util;

@Service
public class OrderService extends BaseService {
	
	//分页查询
	public Page getListForPageDefault(BizOrder o, Integer corpPartyId, String biztype) {

		Page page = null;
		
		if(corpPartyId != null && !Util.isEmptyString(biztype)) {
			String hql = this.BizOrderSql(o, corpPartyId, biztype);
			return this.getListForPageDefault(hql);		
		} else {
			page = this.getListForPageDefault("from BizOrder t order by t.requiredString");
		}
		
		return page;
	}
	
	private String BizOrderSql(BizOrder o, Integer corpPartyId, String biztype) {
		
		StringBuilder builder = new StringBuilder("from BizOrder t where 1=1");
		
		if(!StringUtils.isEmpty(o.getStartTime()))
			builder.append(" and t.loadingTime>='").append(Util.datetoStr(o.getStartTime())).append("'");
		
		if(!StringUtils.isEmpty(o.getEndTime()))
			builder.append(" and t.loadingTime<='").append(Util.datetoStr(o.getEndTime())).append("'");
		
		if(!StringUtils.isEmpty(o.getSupplierName()))
			builder.append(" and t.supplierName like '%").append(o.getSupplierName().trim()).append("%'");
		
		if(!StringUtils.isEmpty(o.getSourceName()))
			builder.append(" and t.sourceName like '%").append(o.getSourceName().trim()).append("%'");
		
		if("SALE".equalsIgnoreCase(biztype))
			builder.append(" and t.supCorpPartyId=").append(corpPartyId.toString());
		else if("PURCHASE".equalsIgnoreCase(biztype)) 
			builder.append(" and t.cusCorpPartyId=").append(corpPartyId.toString());
		else if("TRANSPORT".equalsIgnoreCase(biztype))
			builder.append(" and t.logisticsPartyId=").append(corpPartyId.toString());
		
		builder.append(" order by t.id desc");
		
		return builder.toString();
		
	}
	
	public int del(String ids) {
		 return this.executeUpdate("delete from BizOrder where id in (" + ids + ")");
	}

	/**
	 *采购明细报表 
	 */
	public Object purchaseReport(Integer corpPartyId, String startTimeStr, String endTimeStr, String supplierName,
			String sourceName, String gasType) {
		
		StringBuffer buf = new StringBuffer("select t.supplierName,t.sourceName,t.unloadingTime,plateNo,t.gasType,t.loadingTW,loadingGW,loadingNW,unLoadingGW,"
				+ "unLoadingTW,unLoadingNW,"
				+ "settleWeight,unitPrice,freightPrice,money,transportFee,purchaseCost"
				+ " from BizOrder t where 1=1");
		
		if(!Util.isEmptyString(startTimeStr))buf.append(" and t.unloadingTime>='").append(startTimeStr).append("'");
		if(!Util.isEmptyString(endTimeStr))buf.append(" and t.unloadingTime<='").append(endTimeStr).append("'");
		if(!Util.isEmptyString(supplierName))buf.append(" and t.supplierName like '%").append(supplierName).append("%'");
		if(!Util.isEmptyString(sourceName))buf.append(" and t.sourceName like '%").append(sourceName).append("%'");
		
		if(!Util.isEmptyString(gasType))buf.append(" and t.gasType='").append(gasType).append("'");
		
		buf.append(" and t.cusCorpPartyId='").append(corpPartyId).append("'");
		
		buf.append(" order by t.unloadingTime");
		
		List<Object[]> list = this.getList(buf.toString());
		return list;
	}

}
