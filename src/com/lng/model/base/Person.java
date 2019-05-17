package com.lng.model.base;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;



@Entity

public class Person {
	
	@Id
	@GeneratedValue(generator = "generator")     
	@GenericGenerator(name = "generator", strategy = "native")  
	@Column(name="partyId")
    private Integer partyId;
	private String username;//登录帐号
	private String firstName;//姓名
	private String password;
	private String logDate;
	private String openid;//微信openid
	/**手机号码*/
    private String mobile;
    private String QQ;
    private String email;
	/**审核状态：待审核、通过、未通过*/
    private String audit;
    /**是否激活：未激活、已激活*/
    private String active;
    /**公司Id,代表当前这个人所在公司*/
   	private Integer corpPartyId;
   	/**数据库不存在，登录后赋值*/
    @Transient
	private Corporation corp;
    @Transient
   	private String defaultPwd;
    /**工作号码*/
    private String phone;
	/**信息是否公开,如果公开，将作为公司对外联系人出现*/
	private String isOpen;
    
    private String salutation;
  
    private String middleName;
    private String lastName;
    private String personalTitle;
    private String suffix;
    private String nickname;
    private String firstNameLocal;
    private String middleNameLocal;
    private String lastNameLocal;
    private String otherLocal;
    private String gender;
    private String birthDate;
    private String deceasedDate;
    private String height;
    private String weight;
    private String mothersMaidenName;
    private String maritalStatus;
    private Long socialSecurityNumber;
    private Long passportNumber;
    private String passportExpireDate;
    private String totalYearsWorkExperience;
    private String comments;
    private Integer employmentStatusEnumId;
    private Integer residenceStatusEnumId;
    private String occupation;
    private Integer yearsWithEmployer;
    private Integer monthsWithEmployer;
    private String existingCustomer;
    private Long cardId;
	
	
    
    
	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPersonalTitle() {
		return personalTitle;
	}
	public void setPersonalTitle(String personalTitle) {
		this.personalTitle = personalTitle;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFirstNameLocal() {
		return firstNameLocal;
	}
	public void setFirstNameLocal(String firstNameLocal) {
		this.firstNameLocal = firstNameLocal;
	}
	public String getMiddleNameLocal() {
		return middleNameLocal;
	}
	public void setMiddleNameLocal(String middleNameLocal) {
		this.middleNameLocal = middleNameLocal;
	}
	public String getLastNameLocal() {
		return lastNameLocal;
	}
	public void setLastNameLocal(String lastNameLocal) {
		this.lastNameLocal = lastNameLocal;
	}
	public String getOtherLocal() {
		return otherLocal;
	}
	public void setOtherLocal(String otherLocal) {
		this.otherLocal = otherLocal;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getDeceasedDate() {
		return deceasedDate;
	}
	public void setDeceasedDate(String deceasedDate) {
		this.deceasedDate = deceasedDate;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getMothersMaidenName() {
		return mothersMaidenName;
	}
	public void setMothersMaidenName(String mothersMaidenName) {
		this.mothersMaidenName = mothersMaidenName;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public Long getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setSocialSecurityNumber(Long socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	public Long getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(Long passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getPassportExpireDate() {
		return passportExpireDate;
	}
	public void setPassportExpireDate(String passportExpireDate) {
		this.passportExpireDate = passportExpireDate;
	}
	public String getTotalYearsWorkExperience() {
		return totalYearsWorkExperience;
	}
	public void setTotalYearsWorkExperience(String totalYearsWorkExperience) {
		this.totalYearsWorkExperience = totalYearsWorkExperience;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Integer getEmploymentStatusEnumId() {
		return employmentStatusEnumId;
	}
	public void setEmploymentStatusEnumId(Integer employmentStatusEnumId) {
		this.employmentStatusEnumId = employmentStatusEnumId;
	}
	public Integer getResidenceStatusEnumId() {
		return residenceStatusEnumId;
	}
	public void setResidenceStatusEnumId(Integer residenceStatusEnumId) {
		this.residenceStatusEnumId = residenceStatusEnumId;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public Integer getYearsWithEmployer() {
		return yearsWithEmployer;
	}
	public void setYearsWithEmployer(Integer yearsWithEmployer) {
		this.yearsWithEmployer = yearsWithEmployer;
	}
	public Integer getMonthsWithEmployer() {
		return monthsWithEmployer;
	}
	public void setMonthsWithEmployer(Integer monthsWithEmployer) {
		this.monthsWithEmployer = monthsWithEmployer;
	}
	public String getExistingCustomer() {
		return existingCustomer;
	}
	public void setExistingCustomer(String existingCustomer) {
		this.existingCustomer = existingCustomer;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getCardId() {
		return cardId;
	}
	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	public Integer getCorpPartyId() {
		return corpPartyId;
	}
	public void setCorpPartyId(Integer corpPartyId) {
		this.corpPartyId = corpPartyId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public String getAudit() {
		return audit;
	}
	public void setAudit(String audit) {
		this.audit = audit;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public Corporation getCorp() {
		return corp;
	}
	public void setCorp(Corporation corp) {
		this.corp = corp;
	}
	public String getDefaultPwd() {
		return defaultPwd;
	}
	public void setDefaultPwd(String defaultPwd) {
		this.defaultPwd = defaultPwd;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	
    
    
}
