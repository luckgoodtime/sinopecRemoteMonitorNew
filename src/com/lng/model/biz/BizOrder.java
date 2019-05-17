package com.lng.model.biz;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class BizOrder {

	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")	
	private Integer id;
	
	/*********查询参数开始*************/
	@Transient
	private Date startTime;//开始时间
	@Transient
	private Date endTime;//结束时间
	
	/*********查询参数结束*************/
	
	private String gasType;//气品
	
	/**运输单Id*/
	private Integer shippingOrderId;
	
	/**车牌号*/
	private String plateNo;
	
	/**挂车号*/
	private String tankNo;
	
	/**司机partyId*/
	private Integer driverPartyId;
	
	/**司机姓名*/
	private String driverName;
	
	/**司机电话*/
	private String driverTel;
	
	/**运输商Id*/
	private Integer logisticsPartyId;
	
	/**运输商名称*/
	private String logisticsName;
	
	/**供应公司Id*/
	private Integer supCorpPartyId;
	
	/**供应商名称*/
	private String supplierName;
	
	/**气源点*/
	private Integer sourcePartyId;
	
	/**气源点名称*/
	private String sourceName;
	
	/**客户公司Id*/
	private Integer cusCorpPartyId;
	
	/**客户名称*/
	private String customerName;
	
	/**终端卸气点1*/
	private Integer endPointPartyId1;
	
	/**终端点名称1*/
	private String endPointName1;
	
	/**终端卸气点2*/
	private Integer endPointPartyId2;
	
	/**终端点名称2*/
	private String endPointName2;	
	
	/**终端卸气点3*/
	private Integer endPointPartyId3;
	
	/**终端点名称3*/
	private String endPointName3;		
	
	/**要求日期*/
	private String requiredString;
	
	/**计划提货量*/
	private BigDecimal quantity;
	
	/**是否分卸,是，否*/
	private String unLoadOnRoad;
	
	/**计划装车日期*/
	private String planLoadingString;
	
	/**装车时间*/
	private String loadingTime;
	
	/**装车皮重(Tare Weight)*/
	private BigDecimal loadingTW;
	
	/**装车毛重(Gross Weight)*/
	private BigDecimal loadingGW;
	
	/**装车净重(Net Weight)*/
	private BigDecimal loadingNW;
	
	/**卸车时间*/
	private String unloadingTime;
	
	/**卸车毛重*/
	private BigDecimal unLoadingGW;	

	/**卸车皮重*/
	private BigDecimal unLoadingTW;	
	
	/**卸车净重*/
	private BigDecimal unLoadingNW;	
	
	/**结算量*/
	private BigDecimal settleWeight;
	
	/**磅差费*/
	private BigDecimal weightGagFee;
	
	/**增加运费*/
	private BigDecimal addingFreight;
	
	/**总结算金额*/
	private BigDecimal totalSettleAmount;	
	
	/**创建者*/
	private Integer createPartyId;	
	
	/**单价*/
	private BigDecimal unitPrice;
	
	/**运费单价*/
	private BigDecimal freightPrice;
	
	/**运费承担（买家或卖家）*/
	private BigDecimal transportFee;	
	
	/**金额=单价unitPrice × 装车净重loadingNW*/
	private BigDecimal money;	
	
	/**采购成本 = 采购金额+运费*/
	private BigDecimal purchaseCost;
	
	/**单据号***/
	private String trackNumber;  
	
	/**入库标记,接收方负责填写*/
	private String inStockFlag;
	
	public BigDecimal getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(BigDecimal transportFee) {
		this.transportFee = transportFee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getGasType() {
		return gasType;
	}

	public void setGasType(String gasType) {
		this.gasType = gasType;
	}

	public Integer getShippingOrderId() {
		return shippingOrderId;
	}

	public void setShippingOrderId(Integer shippingOrderId) {
		this.shippingOrderId = shippingOrderId;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
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

	public Integer getLogisticsPartyId() {
		return logisticsPartyId;
	}

	public void setLogisticsPartyId(Integer logisticsPartyId) {
		this.logisticsPartyId = logisticsPartyId;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public Integer getSupCorpPartyId() {
		return supCorpPartyId;
	}

	public void setSupCorpPartyId(Integer supCorpPartyId) {
		this.supCorpPartyId = supCorpPartyId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getEndPointPartyId1() {
		return endPointPartyId1;
	}

	public void setEndPointPartyId1(Integer endPointPartyId1) {
		this.endPointPartyId1 = endPointPartyId1;
	}

	public String getEndPointName1() {
		return endPointName1;
	}

	public void setEndPointName1(String endPointName1) {
		this.endPointName1 = endPointName1;
	}

	public Integer getEndPointPartyId2() {
		return endPointPartyId2;
	}

	public void setEndPointPartyId2(Integer endPointPartyId2) {
		this.endPointPartyId2 = endPointPartyId2;
	}

	public String getEndPointName2() {
		return endPointName2;
	}

	public void setEndPointName2(String endPointName2) {
		this.endPointName2 = endPointName2;
	}

	public Integer getEndPointPartyId3() {
		return endPointPartyId3;
	}

	public void setEndPointPartyId3(Integer endPointPartyId3) {
		this.endPointPartyId3 = endPointPartyId3;
	}

	public String getEndPointName3() {
		return endPointName3;
	}

	public void setEndPointName3(String endPointName3) {
		this.endPointName3 = endPointName3;
	}

	public Integer getSourcePartyId() {
		return sourcePartyId;
	}

	public void setSourcePartyId(Integer sourcePartyId) {
		this.sourcePartyId = sourcePartyId;
	}

	public Integer getCusCorpPartyId() {
		return cusCorpPartyId;
	}

	public void setCusCorpPartyId(Integer cusCorpPartyId) {
		this.cusCorpPartyId = cusCorpPartyId;
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

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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

	public BigDecimal getWeightGagFee() {
		return weightGagFee;
	}

	public void setWeightGagFee(BigDecimal weightGagFee) {
		this.weightGagFee = weightGagFee;
	}

	public BigDecimal getAddingFreight() {
		return addingFreight;
	}

	public void setAddingFreight(BigDecimal addingFreight) {
		this.addingFreight = addingFreight;
	}

	public BigDecimal getTotalSettleAmount() {
		return totalSettleAmount;
	}

	public void setTotalSettleAmount(BigDecimal totalSettleAmount) {
		this.totalSettleAmount = totalSettleAmount;
	}

	public Integer getCreatePartyId() {
		return createPartyId;
	}

	public void setCreatePartyId(Integer createPartyId) {
		this.createPartyId = createPartyId;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public BigDecimal getFreightPrice() {
		return freightPrice;
	}

	public void setFreightPrice(BigDecimal freightPrice) {
		this.freightPrice = freightPrice;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(BigDecimal purchaseCost) {
		this.purchaseCost = purchaseCost;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(String trackNumber) {
		this.trackNumber = trackNumber;
	}

	public String getInStockFlag() {
		return inStockFlag;
	}

	public void setInStockFlag(String inStockFlag) {
		this.inStockFlag = inStockFlag;
	}
	
}
