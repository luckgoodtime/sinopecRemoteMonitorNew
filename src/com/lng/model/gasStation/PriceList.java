package com.lng.model.gasStation;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

//价格表
@Entity
public class PriceList {
	
		@Id
		@GenericGenerator(name = "generator", strategy = "native")
		@GeneratedValue(generator = "generator")
		private Integer id;  //主键
		private String gunNo;//枪号
		@Column(precision=18, scale=2)
		private BigDecimal oldPrice;  //旧价格
		@Column(precision=18, scale=2)
		private BigDecimal newPrice;  //新价格
		private Date publishTime;//下发时间
		@Column(length=64)
	    private String stationNo;//加气站编号
		@Column(length=64)
	    private String stationName;//加气站名称
	    private Integer belgon2PartyId;//公司
	    
	    @Transient
	    private String publishTimeBegin;//加气开始时间
	    @Transient
	    private String publishTimeEnd;//加气结束时间
	    
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getGunNo() {
			return gunNo;
		}
		public void setGunNo(String gunNo) {
			this.gunNo = gunNo;
		}
		public BigDecimal getOldPrice() {
			return oldPrice;
		}
		public void setOldPrice(BigDecimal oldPrice) {
			this.oldPrice = oldPrice;
		}
		public BigDecimal getNewPrice() {
			return newPrice;
		}
		public void setNewPrice(BigDecimal newPrice) {
			this.newPrice = newPrice;
		}
		public Date getPublishTime() {
			return publishTime;
		}
		public void setPublishTime(Date publishTime) {
			this.publishTime = publishTime;
		}
		public String getStationNo() {
			return stationNo;
		}
		public void setStationNo(String stationNo) {
			this.stationNo = stationNo;
		}
		public String getStationName() {
			return stationName;
		}
		public void setStationName(String stationName) {
			this.stationName = stationName;
		}
		public Integer getBelgon2PartyId() {
			return belgon2PartyId;
		}
		public void setBelgon2PartyId(Integer belgon2PartyId) {
			this.belgon2PartyId = belgon2PartyId;
		}
		public String getPublishTimeBegin() {
			return publishTimeBegin;
		}
		public void setPublishTimeBegin(String publishTimeBegin) {
			this.publishTimeBegin = publishTimeBegin;
		}
		public String getPublishTimeEnd() {
			return publishTimeEnd;
		}
		public void setPublishTimeEnd(String publishTimeEnd) {
			this.publishTimeEnd = publishTimeEnd;
		}
		
	    
	    
	   
	      
	  
}
