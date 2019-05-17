package com.lng.model.biz.price;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 * 报价单，关键属性包括这是哪家公司发给它的某个客户的报价单，包括生效日期，自提价格
 * 
 * 在贸易中，价格有多种类型，包括:离岸价FOB(Free On board)/到岸价CIF(Cost Insurance and Freight)
 * 
 */


@Entity
@SuppressWarnings("unchecked")
public class Quotation {

	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")	
	private int id;
	
	/**开始生效日期*/
	private String effectString;
	
	/**失效日期*/
	private String expiredString;
	
	/**自提价格*/
	private Integer fobPrice;
	
	/**接受，未接受*/
	private String status;
	
	/**客户公司Id*/
	private Integer cusCorpPartyId;
	
	/**报价公司Id*/
	private Integer offerCorpPartyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEffectString() {
		return effectString;
	}

	public void setEffectString(String effectString) {
		this.effectString = effectString;
	}

	public String getExpiredString() {
		return expiredString;
	}

	public void setExpiredString(String expiredString) {
		this.expiredString = expiredString;
	}
	
	public Integer getFobPrice() {
		return fobPrice;
	}

	public void setFobPrice(Integer fobPrice) {
		this.fobPrice = fobPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCusCorpPartyId() {
		return cusCorpPartyId;
	}

	public void setCusCorpPartyId(Integer cusCorpPartyId) {
		this.cusCorpPartyId = cusCorpPartyId;
	}

	public Integer getOfferCorpPartyId() {
		return offerCorpPartyId;
	}

	public void setOfferCorpPartyId(Integer offerCorpPartyId) {
		this.offerCorpPartyId = offerCorpPartyId;
	}


	
}
