package com.lng.model.biz;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


/**
 * 运输单描述一个运输任务，一个运输任务可能运输多个订单，比如分卸<br>
 * 运输单描述了某一个执行运输的运输公司，给某一个公司从一个气源点运到某个卸气点<br>
 * 记录装车，卸车的情况，如果是多点卸货，卸货总量为多点卸货之和。
 * 
 * 
 */



@Entity
@SuppressWarnings("unchecked")
public class ShippingOrder {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")	
	private Integer id;
	
	/**承担运输任务的公司Id--运输公司*/
	private Integer transportCorpPartyId;	
	
	/**运输服务业主(一般是运费承担公司)---业主（买家或卖家）*/
	private Integer transportOwner;	
	
	/**车牌号*/
	private String truckNo;
	
	/**挂车号*/
	private String tankNo;
	
	/**司机partyId*/
	private Integer driverPartyId;
	
	/**司机姓名*/
	private String driverName;
	
	/**司机电话*/
	private String driverTel;
	
	/**气源点Id*/
	private Integer sourcePartyId;	
	
	/**气源点名称*/
	private String sourceName;
	
	/**卸气点Id*/
	private Integer endPointPartyId;
	
	/**卸气点名称*/
	private String endPointName;
	
	/**要求日期*/
	private String requiredString;
	
	/**是否分卸,是，否*/
	private String unLoadOnRoad;
	
	/**计划装车日期*/
	private String planLoadingString;
	
	/**到达装车地时间*/
	private String arriveLoadTime;
	
	/**装车时间*/
	private String loadingTime;
	
	/**装车皮重(Tare Weight)*/
	private BigDecimal loadingTW;
	
	/**装车毛重(Gross Weight)*/
	private BigDecimal loadingGW;
	
	/**装车净重(Net Weight)*/
	private BigDecimal loadingNW;
	
	/**到达卸车地时间*/
	private String arriveUnLoadTime;
	
	/**卸车时间*/
	private String unloadingTime;
	
	/**卸车毛重*/
	private BigDecimal unLoadingGW;	

	/**卸车皮重*/
	private BigDecimal unLoadingTW;	
	
	/**卸车净重*/
	private BigDecimal unLoadingNW;	
	
	/**运费结算量*/
	private BigDecimal settleWeight;

	
	/**状态:未出发、装液路上、到装液点未装、开始装液、去卸液路上、卸载液点1、完成*/
	private String state;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTransportCorpPartyId() {
		return transportCorpPartyId;
	}

	public void setTransportCorpPartyId(Integer transportCorpPartyId) {
		this.transportCorpPartyId = transportCorpPartyId;
	}
	
	public String getTruckNo() {
		return truckNo;
	}

	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}

	public String getTankNo() {
		return tankNo;
	}

	public void setTankNo(String tankNo) {
		this.tankNo = tankNo;
	}

	public Integer getDriverPartyId() {
		return driverPartyId;
	}

	public void setDriverPartyId(Integer driverPartyId) {
		this.driverPartyId = driverPartyId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverTel() {
		return driverTel;
	}

	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}

	public Integer getTransportOwner() {
		return transportOwner;
	}

	public void setTransportOwner(Integer transportOwner) {
		this.transportOwner = transportOwner;
	}

	public Integer getSourcePartyId() {
		return sourcePartyId;
	}

	public void setSourcePartyId(Integer sourcePartyId) {
		this.sourcePartyId = sourcePartyId;
	}

	public Integer getEndPointPartyId() {
		return endPointPartyId;
	}

	public void setEndPointPartyId(Integer endPointPartyId) {
		this.endPointPartyId = endPointPartyId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getEndPointName() {
		return endPointName;
	}

	public void setEndPointName(String endPointName) {
		this.endPointName = endPointName;
	}

	public String getRequiredString() {
		return requiredString;
	}

	public void setRequiredString(String requiredString) {
		this.requiredString = requiredString;
	}

	public String getUnLoadOnRoad() {
		return unLoadOnRoad;
	}

	public void setUnLoadOnRoad(String unLoadOnRoad) {
		this.unLoadOnRoad = unLoadOnRoad;
	}

	public String getPlanLoadingString() {
		return planLoadingString;
	}

	public void setPlanLoadingString(String planLoadingString) {
		this.planLoadingString = planLoadingString;
	}

	public String getLoadingTime() {
		return loadingTime;
	}

	public void setLoadingTime(String loadingTime) {
		this.loadingTime = loadingTime;
	}

	public BigDecimal getLoadingTW() {
		return loadingTW;
	}

	public void setLoadingTW(BigDecimal loadingTW) {
		this.loadingTW = loadingTW;
	}

	public BigDecimal getLoadingGW() {
		return loadingGW;
	}

	public void setLoadingGW(BigDecimal loadingGW) {
		this.loadingGW = loadingGW;
	}

	public BigDecimal getLoadingNW() {
		return loadingNW;
	}

	public void setLoadingNW(BigDecimal loadingNW) {
		this.loadingNW = loadingNW;
	}

	public String getUnloadingTime() {
		return unloadingTime;
	}

	public void setUnloadingTime(String unloadingTime) {
		this.unloadingTime = unloadingTime;
	}

	public BigDecimal getUnLoadingGW() {
		return unLoadingGW;
	}

	public void setUnLoadingGW(BigDecimal unLoadingGW) {
		this.unLoadingGW = unLoadingGW;
	}

	public BigDecimal getUnLoadingTW() {
		return unLoadingTW;
	}

	public void setUnLoadingTW(BigDecimal unLoadingTW) {
		this.unLoadingTW = unLoadingTW;
	}

	public BigDecimal getUnLoadingNW() {
		return unLoadingNW;
	}

	public void setUnLoadingNW(BigDecimal unLoadingNW) {
		this.unLoadingNW = unLoadingNW;
	}

	public BigDecimal getSettleWeight() {
		return settleWeight;
	}

	public void setSettleWeight(BigDecimal settleWeight) {
		this.settleWeight = settleWeight;
	}

	public String getArriveLoadTime() {
		return arriveLoadTime;
	}

	public void setArriveLoadTime(String arriveLoadTime) {
		this.arriveLoadTime = arriveLoadTime;
	}

	public String getArriveUnLoadTime() {
		return arriveUnLoadTime;
	}

	public void setArriveUnLoadTime(String arriveUnLoadTime) {
		this.arriveUnLoadTime = arriveUnLoadTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
