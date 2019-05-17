package com.lng.model.biz.partner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity

public class Supplier {
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	private Integer id; // 主键自增
	/**属于公司，表明是属于什么公司的供应商*/
	private Integer belong2CorpPartyId;
	/**供应商公司Id，是其他某公司的供应商*/
	private Integer corpPartyId;
	/**供应商编号*/
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
	@Transient
	private String corpName;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getBelong2CorpPartyId() {
		return belong2CorpPartyId;
	}
	public void setBelong2CorpPartyId(Integer belong2CorpPartyId) {
		this.belong2CorpPartyId = belong2CorpPartyId;
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
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	
	
}
