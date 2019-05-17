package com.lng.model.base.party;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import org.hibernate.annotations.GenericGenerator;


@Entity

public class PartyAttribute {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	@Column(name="partyAttributeId")
	private Integer partyAttributeId;
	private Integer partyId;
    private String attrName;
    private String attrValue;
	public Integer getPartyAttributeId() {
		return partyAttributeId;
	}
	public void setPartyAttributeId(Integer partyAttributeId) {
		this.partyAttributeId = partyAttributeId;
	}
	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
    

    
    
}
