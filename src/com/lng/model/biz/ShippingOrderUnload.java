package com.lng.model.biz;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 *运输单卸车记录，主要应对多点卸液 
 */


@Entity
@SuppressWarnings("unchecked")
public class ShippingOrderUnload {

	@Id
	@GenericGenerator(name = "generator", strategy = "native")
	@GeneratedValue(generator = "generator")	
	private Integer id;
	
	/**运输单Id*/
	private Integer shippingOrderId;
	
	/**到达卸液点时间*/
	private String arrivedTime;
	
	/**开始卸液时间*/
	private String startUnload;
	
	/**卸液结束时间*/
	private String endUnload;	
	
	/**卸车毛重*/
	private BigDecimal unLoadingGW;	

	/**卸车皮重*/
	private BigDecimal unLoadingTW;	
	
	/**卸车净重*/
	private BigDecimal unLoadingNW;	
	
	/**离开时间*/
	private String leavingTime;
	
	/**备注*/
	private String memo;

	/**卸气点Id*/
	private Integer endPointPartyId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShippingOrderId() {
		return shippingOrderId;
	}

	public void setShippingOrderId(Integer shippingOrderId) {
		this.shippingOrderId = shippingOrderId;
	}

	public String getArrivedTime() {
		return arrivedTime;
	}

	public void setArrivedTime(String arrivedTime) {
		this.arrivedTime = arrivedTime;
	}

	public String getStartUnload() {
		return startUnload;
	}

	public void setStartUnload(String startUnload) {
		this.startUnload = startUnload;
	}

	public String getEndUnload() {
		return endUnload;
	}

	public void setEndUnload(String endUnload) {
		this.endUnload = endUnload;
	}

	public BigDecimal getUnLoadingGW() {
		return unLoadingGW;
	}

	public void setUnLoadingGW(BigDecimal unLoadingGW) {
		this.unLoadingGW = unLoadingGW;
	}

	public BigDecimal getUnLoadingTW() {
		return unLoadingTW;
	}

	public void setUnLoadingTW(BigDecimal unLoadingTW) {
		this.unLoadingTW = unLoadingTW;
	}

	public BigDecimal getUnLoadingNW() {
		return unLoadingNW;
	}

	public void setUnLoadingNW(BigDecimal unLoadingNW) {
		this.unLoadingNW = unLoadingNW;
	}

	public String getLeavingTime() {
		return leavingTime;
	}

	public void setLeavingTime(String leavingTime) {
		this.leavingTime = leavingTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getEndPointPartyId() {
		return endPointPartyId;
	}

	public void setEndPointPartyId(Integer endPointPartyId) {
		this.endPointPartyId = endPointPartyId;
	}
}
