package com.lng.model.base.party;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity

public class PartyType {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")
	@Column(name="partyTypeId")
	private Integer partyTypeId;
	private Integer parentTypeId;
    private String hasTable;
    private String description;
	public Integer getPartyTypeId() {
		return partyTypeId;
	}
	public void setPartyTypeId(Integer partyTypeId) {
		this.partyTypeId = partyTypeId;
	}
	public Integer getParentTypeId() {
		return parentTypeId;
	}
	public void setParentTypeId(Integer parentTypeId) {
		this.parentTypeId = parentTypeId;
	}
	public String getHasTable() {
		return hasTable;
	}
	public void setHasTable(String hasTable) {
		this.hasTable = hasTable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
  

    
    
}
