package com.lng.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity

public class EndPoint {

	@Id
	@GeneratedValue(generator = "generator")     
	@GenericGenerator(name = "generator", strategy = "assigned")  
	@Column(name="partyId")
    private Integer partyId;
    private String pointName;
    private String pointShortName;
    private String fullPinyin;
    private String simplePinyin;
    /**类型,加气站,工厂等*/
    private String pointType;
    /**地址Id*/
    private Integer addressId;
	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public String getPointShortName() {
		return pointShortName;
	}
	public void setPointShortName(String pointShortName) {
		this.pointShortName = pointShortName;
	}
	public String getFullPinyin() {
		return fullPinyin;
	}
	public void setFullPinyin(String fullPinyin) {
		this.fullPinyin = fullPinyin;
	}
	public String getSimplePinyin() {
		return simplePinyin;
	}
	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}
	public String getPointType() {
		return pointType;
	}
	public void setPointType(String pointType) {
		this.pointType = pointType;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	
}
