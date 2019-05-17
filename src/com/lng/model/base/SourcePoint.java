package com.lng.model.base;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity

public class SourcePoint {
	@Id
	@GeneratedValue(generator = "generator")     
	@GenericGenerator(name = "generator", strategy = "assigned")  
	@Column(name="partyId")
    private Integer partyId;
    private String sourceName;
    private String sourceShortName;  
    private String fullPinyin;
    private String simplePinyin;    
    /**类型,生产型的，存储型的*/
    private String sourceType;
    /**生产日能力*/
    private BigDecimal dailyAbility;
    /**生产年能力*/
    private BigDecimal annualAbility;
    /**地址Id*/
    //@NotEmpty(message="{address.not.empty}") 
    private Integer addressId;    
    /**热值*/
    private BigDecimal calorificValue;    
    /**气化率*/
    private BigDecimal gasificationRate;
	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getSourceShortName() {
		return sourceShortName;
	}
	public void setSourceShortName(String sourceShortName) {
		this.sourceShortName = sourceShortName;
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
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public BigDecimal getDailyAbility() {
		return dailyAbility;
	}
	public void setDailyAbility(BigDecimal dailyAbility) {
		this.dailyAbility = dailyAbility;
	}
	public BigDecimal getAnnualAbility() {
		return annualAbility;
	}
	public void setAnnualAbility(BigDecimal annualAbility) {
		this.annualAbility = annualAbility;
	}
	
	public BigDecimal getCalorificValue() {
		return calorificValue;
	}
	public void setCalorificValue(BigDecimal calorificValue) {
		this.calorificValue = calorificValue;
	}
	public BigDecimal getGasificationRate() {
		return gasificationRate;
	}
	public void setGasificationRate(BigDecimal gasificationRate) {
		this.gasificationRate = gasificationRate;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
}
