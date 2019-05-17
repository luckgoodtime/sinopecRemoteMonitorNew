package com.lng.model.base.party;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Party {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	@Column(name="partyId")
    private Integer partyId;
    private Integer partyTypeId;
    private String description;
    private Integer statusId;
    private String createdDate;
    private Integer createdByUser;
    private String lastModifiedDate;
    private Integer lastModifiedByUserLogin;
    
    
	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
	public Integer getPartyTypeId() {
		return partyTypeId;
	}
	public void setPartyTypeId(Integer partyTypeId) {
		this.partyTypeId = partyTypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getCreatedByUser() {
		return createdByUser;
	}
	public void setCreatedByUser(Integer createdByUser) {
		this.createdByUser = createdByUser;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Integer getLastModifiedByUserLogin() {
		return lastModifiedByUserLogin;
	}
	public void setLastModifiedByUserLogin(Integer lastModifiedByUserLogin) {
		this.lastModifiedByUserLogin = lastModifiedByUserLogin;
	}
	
    
    
    
}
