package com.lng.model.biz.price;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


/**报价明细，指的到岸价，自提价直接在报价单头展示*/


@Entity
@SuppressWarnings("unchecked")
public class QuotationItem {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")	
	private int id;
	
	private int quotationId;
	
	/**到岸价*/
	private Integer cifPrice;
	
	private Integer sourcePartyId;
	
	private Integer endPointPartyId;
	
	/**报价公司Id*/
	private Integer offerCorpPartyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	public Integer getCifPrice() {
		return cifPrice;
	}

	public void setCifPrice(Integer cifPrice) {
		this.cifPrice = cifPrice;
	}

	public Integer getSourcePartyId() {
		return sourcePartyId;
	}

	public void setSourcePartyId(Integer sourcePartyId) {
		this.sourcePartyId = sourcePartyId;
	}

	public Integer getEndPointPartyId() {
		return endPointPartyId;
	}

	public void setEndPointPartyId(Integer endPointPartyId) {
		this.endPointPartyId = endPointPartyId;
	}

	public Integer getOfferCorpPartyId() {
		return offerCorpPartyId;
	}

	public void setOfferCorpPartyId(Integer offerCorpPartyId) {
		this.offerCorpPartyId = offerCorpPartyId;
	}
	
	
}
