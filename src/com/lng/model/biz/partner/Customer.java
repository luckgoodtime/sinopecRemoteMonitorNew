package com.lng.model.biz.partner;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


/**
 *客户描述的是关系,回答下面的问题
 *什么时间成为了公司的客户,我将该公司划分到什么区域,由谁负责,目前状态怎样（是否终止的供应关系） <br>
 *
 *add on 2018-06-14 07:42<br/>
 *
 *客户的建立需要考虑两种模式，一钟属于平台模式，一种属于SaaS模式。对于平台模式,客户首先是平台的注册会员<br>
 *然后通过从平台引用，建立客户关系；对于SaaS模式，是先建立客户关系，然后平台通过数据观察，内部建立网络，从而<br>
 *形成网络，形成平台.
 *
 *
 * 
 */

@Entity
@SuppressWarnings("unchecked")
public class Customer {

	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	private Integer id; // 主键自增
	/**属于公司*/
	private Integer belgon2PartyId;
	/**客户公司Id,在SaaS模式下开始可能是空，后续平台来填充*/
	private Integer corpPartyId; 
	/**客户编号*/
	private String serialNo;
	/**区域*/
	private String region;
	/**我方客户经理,关联Person*/
	private Integer bizManager;
	/**业务开始日期*/
	private String bizStartDate;
	/**创建日期*/
	private String createDate;
	/**创建人*/
	private Integer createParty;	
	/**购买次数*/
	private Integer buytimes;	
	/**客户名称*/
	private String cusName;	
	/**客户简称*/
	private String cusShortName;
	/**联系人*/
	private String cusContact;
	/**联系电话*/
	private String cusContactTel;
	/**联系人身份证号*/
	private String cusContactIdNo;
	/**送气地址*/
	private String receiptPlace;
	
	/**解析送气地址结果：省*/
	@Transient
	private String province;
	/**解析送气地址结果：市*/
	@Transient
	private String city;
	/**解析送气地址结果：县区市*/
	@Transient
	private String county;
	/**解析送气地址结果：镇*/
	@Transient
	private String town;
	/**解析送气地址结果：村庄（地址其他部分）*/
	@Transient
	private String village;
	
	
	/**客户类型(居民户 ,企事业)*/
	private String cusCategory;		
	/**行业性质（工业，商业，企事业）*/
	private String cusType;
	/**用气证号*/
	private String gasCardNo;	
	/**销售类型（批发|直销）*/
	private String saleType;	
	/**钢瓶租用方式（全额押金|部分押金|免押金）*/
	private String bottleRentType;
	/**用气周期（天）*/	
	private Integer cycleDays;
	/**结算周期（现结|月结|周结）*/
	private String payCycle;
	
	/**业务代表*/
	private String bizRep;
	
	/**定价方式（浮动价|月价|半月价|季度价|年价|议价）*/
	private String priceType;	
	/**企业信用代码*/
	private String corpCreditCode;	
	/**上级客户编码*/
	private String parentSerialNo;
	/**上级客户名称*/
	private String parentCusName;
	/**上级客户Id*/
	private Integer parentCusId;
	/**结算客户名称*/
	private String payCustomer;
	/**结算客户Id*/
	private Integer payCustomerId;
	/**销户时间*/
	private String logoffDate;
	/**销户人*/
	private Integer logoffParty;
	/**绑定的微信Id,可能有多个,逗号分隔,支持子女给父母定气*/
	private String bindOpenIds;
	/**状态*/
	private String state;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBelgon2PartyId() {
		return belgon2PartyId;
	}
	public void setBelgon2PartyId(Integer belgon2PartyId) {
		this.belgon2PartyId = belgon2PartyId;
	}
	public Integer getCorpPartyId() {
		return corpPartyId;
	}
	public void setCorpPartyId(Integer corpPartyId) {
		this.corpPartyId = corpPartyId;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getBizManager() {
		return bizManager;
	}
	public void setBizManager(Integer bizManager) {
		this.bizManager = bizManager;
	}
	public String getBizStartDate() {
		return bizStartDate;
	}
	public void setBizStartDate(String bizStartDate) {
		this.bizStartDate = bizStartDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Integer getCreateParty() {
		return createParty;
	}
	public void setCreateParty(Integer createParty) {
		this.createParty = createParty;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusShortName() {
		return cusShortName;
	}
	public void setCusShortName(String cusShortName) {
		this.cusShortName = cusShortName;
	}
	public String getCusContact() {
		return cusContact;
	}
	public void setCusContact(String cusContact) {
		this.cusContact = cusContact;
	}
	public String getCusContactTel() {
		return cusContactTel;
	}
	public void setCusContactTel(String cusContactTel) {
		this.cusContactTel = cusContactTel;
	}
	public String getCusContactIdNo() {
		return cusContactIdNo;
	}
	public void setCusContactIdNo(String cusContactIdNo) {
		this.cusContactIdNo = cusContactIdNo;
	}
	public String getReceiptPlace() {
		return receiptPlace;
	}
	public void setReceiptPlace(String receiptPlace) {
		this.receiptPlace = receiptPlace;
	}
	public String getCusCategory() {
		return cusCategory;
	}
	public void setCusCategory(String cusCategory) {
		this.cusCategory = cusCategory;
	}
	public String getCusType() {
		return cusType;
	}
	public void setCusType(String cusType) {
		this.cusType = cusType;
	}
	public String getGasCardNo() {
		return gasCardNo;
	}
	public void setGasCardNo(String gasCardNo) {
		this.gasCardNo = gasCardNo;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	public String getBottleRentType() {
		return bottleRentType;
	}
	public void setBottleRentType(String bottleRentType) {
		this.bottleRentType = bottleRentType;
	}
	public Integer getCycleDays() {
		return cycleDays;
	}
	public void setCycleDays(Integer cycleDays) {
		this.cycleDays = cycleDays;
	}
	public String getPayCycle() {
		return payCycle;
	}
	public void setPayCycle(String payCycle) {
		this.payCycle = payCycle;
	}
	
	public String getBizRep() {
		return bizRep;
	}
	public void setBizRep(String bizRep) {
		this.bizRep = bizRep;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public String getCorpCreditCode() {
		return corpCreditCode;
	}
	public void setCorpCreditCode(String corpCreditCode) {
		this.corpCreditCode = corpCreditCode;
	}
	public String getParentSerialNo() {
		return parentSerialNo;
	}
	public void setParentSerialNo(String parentSerialNo) {
		this.parentSerialNo = parentSerialNo;
	}
	public String getParentCusName() {
		return parentCusName;
	}
	public void setParentCusName(String parentCusName) {
		this.parentCusName = parentCusName;
	}
	public Integer getParentCusId() {
		return parentCusId;
	}
	public void setParentCusId(Integer parentCusId) {
		this.parentCusId = parentCusId;
	}
	public String getPayCustomer() {
		return payCustomer;
	}
	public void setPayCustomer(String payCustomer) {
		this.payCustomer = payCustomer;
	}
	public Integer getPayCustomerId() {
		return payCustomerId;
	}
	public void setPayCustomerId(Integer payCustomerId) {
		this.payCustomerId = payCustomerId;
	}
	public String getLogoffDate() {
		return logoffDate;
	}
	public void setLogoffDate(String logoffDate) {
		this.logoffDate = logoffDate;
	}
	public Integer getLogoffParty() {
		return logoffParty;
	}
	public void setLogoffParty(Integer logoffParty) {
		this.logoffParty = logoffParty;
	}
	public String getBindOpenIds() {
		return bindOpenIds;
	}
	public void setBindOpenIds(String bindOpenIds) {
		this.bindOpenIds = bindOpenIds;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public Integer getBuytimes() {
		return buytimes;
	}
	public void setBuytimes(Integer buytimes) {
		this.buytimes = buytimes;
	}
	
}
