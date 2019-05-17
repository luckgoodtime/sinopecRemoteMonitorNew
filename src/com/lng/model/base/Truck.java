package com.lng.model.base;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity

public class Truck {

	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	@Column(name="id")	
	private Integer id;
	
	private String truckType;//类型：车头，罐车
	
	private String plateNo;//车牌号
	
	private String nextCheckDate;//下次检测日期
	
	private BigDecimal tonnage;//荷载吨数 
	
	private String lastTankerNo;//最新关联的槽罐号
	
	private Integer corpPartyId;//所属公司Id 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTruckType() {
		return truckType;
	}

	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getNextCheckDate() {
		return nextCheckDate;
	}

	public void setNextCheckDate(String nextCheckDate) {
		this.nextCheckDate = nextCheckDate;
	}

	public BigDecimal getTonnage() {
		return tonnage;
	}

	public void setTonnage(BigDecimal tonnage) {
		this.tonnage = tonnage;
	}

	public String getLastTankerNo() {
		return lastTankerNo;
	}

	public void setLastTankerNo(String lastTankerNo) {
		this.lastTankerNo = lastTankerNo;
	}

	public Integer getCorpPartyId() {
		return corpPartyId;
	}

	public void setCorpPartyId(Integer corpPartyId) {
		this.corpPartyId = corpPartyId;
	}
}
