package com.lng.model.base;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity

public class Address {
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	@Column(name="addressId")	
	private Integer addressId;	
	private String state;
	private String city;
	private String county;
	private String address1;
	private String address2;
	private String postalCode;//邮编
	private String tel;//联系电话
	private String mobile;//手机
	@Column(precision=12, scale=8)
	private BigDecimal longitude;//经度、南北，精度12，小数位8
	@Column(precision=12, scale=8)
	private BigDecimal latitude;//纬度
	private BigDecimal altitude;//海拔
	
	private String corpNames;//单位名称
	
	
	public String getCorpNames() {
		return corpNames;
	}
	public void setCorpNames(String corpNames) {
		this.corpNames = corpNames;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getAltitude() {
		return altitude;
	}
	public void setAltitude(BigDecimal altitude) {
		this.altitude = altitude;
	}
	
	public String getAddressDes(){
		StringBuilder builder = new StringBuilder();
		builder.append(this.getState() == null?"":this.getState());
		builder.append(this.getCity() == null?"":this.getCity());
		builder.append(this.getCounty() == null?"":this.getCounty());
		builder.append(this.getAddress1() == null?"":this.getAddress1());
		return builder.toString();
	}
	
	
	
}
