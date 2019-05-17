package com.lng.model.biz.price;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


/**
 *对外公布的价格，包括是哪家公司的，价格，生效日期，是否对外发布 
 * 
 */

@Entity
@SuppressWarnings("unchecked")

public class Price {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")	
	private int id;
	
	private Integer price;
	
	/**开始生效日期*/
	private String effectString;
	
	/**未发布，发布*/
	private String status;
	
	/**属于公司*/
	private Integer belgon2PartyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getEffectString() {
		return effectString;
	}

	public void setEffectString(String effectString) {
		this.effectString = effectString;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getBelgon2PartyId() {
		return belgon2PartyId;
	}

	public void setBelgon2PartyId(Integer belgon2PartyId) {
		this.belgon2PartyId = belgon2PartyId;
	}

	
}
