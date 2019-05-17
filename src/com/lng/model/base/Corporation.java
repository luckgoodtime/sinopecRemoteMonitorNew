package com.lng.model.base;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity

public class Corporation {
	@Id
	@GeneratedValue(generator = "generator")     
	@GenericGenerator(name = "generator", strategy = "assigned")  
	@Column(name="partyId")
    private Integer partyId;
    private String corpName;
    @NotEmpty(message="{corpShortName.not.empty}") 
    private String corpShortName;
    /**名称首字母缩写(小写)*/
    private String fullPinyin;    
    /**名称全拼(小写)*/
    private String simplePinyin;
    /**上级公司,默认为空*/
    private String parentCorp;
    /**上级公司名称,默认为空*/
    private String parentCorpName;
    /**是否独立核算,如果是将会有账户*/
    private boolean selfAccounting;
    /**企业税号*/
    private String corporationTax;
    /**公司营业执照号*/
    private String license;
    /**公司创建日期*/
    private String buildDate;
    /**创建日期*/
    private String createDate;
    /**激活日期*/
    private String activateDate;    
    /**地址Id*/
    private Integer addressId;
    /**地址描述*/
    private String addressDes;
    /**公司联系电话*/
    private String phone;
    /**传真号*/
    private String fax;
    /**企业网址*/
    private String webUrl;
    /**企业邮箱*/
    private String email;
    /**企业微信*/
    private String webChatId;
    /**企业是否经过认证*/
    private boolean hasBeenAuthenticated;
    /**企业激活者*/
    private Integer authenticatedParty;
    /**详述*/
    @Column(length=1000)
    private String detail;
    

    public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
	public String getCorpName() {
		return corpName;
	}
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}
	public String getCorpShortName() {
		return corpShortName;
	}
	public void setCorpShortName(String corpShortName) {
		this.corpShortName = corpShortName;
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
	public String getParentCorp() {
		return parentCorp;
	}
	public void setParentCorp(String parentCorp) {
		this.parentCorp = parentCorp;
	}
	public String getParentCorpName() {
		return parentCorpName;
	}
	public void setParentCorpName(String parentCorpName) {
		this.parentCorpName = parentCorpName;
	}
	public boolean isSelfAccounting() {
		return selfAccounting;
	}
	public void setSelfAccounting(boolean selfAccounting) {
		this.selfAccounting = selfAccounting;
	}
	public String getCorporationTax() {
		return corporationTax;
	}
	public void setCorporationTax(String corporationTax) {
		this.corporationTax = corporationTax;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getBuildDate() {
		return buildDate;
	}
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getActivateDate() {
		return activateDate;
	}
	public void setActivateDate(String activateDate) {
		this.activateDate = activateDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getAddressDes() {
		return addressDes;
	}
	public void setAddressDes(String addressDes) {
		this.addressDes = addressDes;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebChatId() {
		return webChatId;
	}
	public void setWebChatId(String webChatId) {
		this.webChatId = webChatId;
	}
	public boolean isHasBeenAuthenticated() {
		return hasBeenAuthenticated;
	}
	public void setHasBeenAuthenticated(boolean hasBeenAuthenticated) {
		this.hasBeenAuthenticated = hasBeenAuthenticated;
	}
	public Integer getAuthenticatedParty() {
		return authenticatedParty;
	}
	public void setAuthenticatedParty(Integer authenticatedParty) {
		this.authenticatedParty = authenticatedParty;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
    
}
