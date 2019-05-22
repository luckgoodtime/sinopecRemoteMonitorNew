package com.lng.model.remotemonitor;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

//加气记录表
@Entity(name = "filling_record")
public class FillingRecord {
	
		@Id
		@GenericGenerator(name = "generator", strategy = "native")
		@GeneratedValue(generator = "generator")
		private Integer id;  //主键
		private String recordId;  //站级记录ID
		private String gunNo;  //枪号
		private String carNo;//卡号
		private String holderName;//持卡人姓名
		private String plateNo;//车牌号
		private Date fillTime;//加气时间
		private BigDecimal price;  //单价
		private BigDecimal volume;  //原始气量
		private BigDecimal receivable;  //原始金额
		private BigDecimal discount;  //优惠金额
		private BigDecimal receiptTotal;  //实际金额
		private BigDecimal cardBalance;  //卡余额
		private String cardType;//卡类型 无卡 员工卡 用户卡 计量卡
		private String fillType;//加气类型 LNG CNG
		private String note;// 说明 共几枪
		@Column(length=64)
	    private String stationNo;//加气站编号
		@Column(length=64)
	    private String stationName;//加气站名称
	    private Integer belgon2PartyId;//公司
	    
	    
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getRecordId() {
			return recordId;
		}
		public void setRecordId(String recordId) {
			this.recordId = recordId;
		}
		public String getGunNo() {
			return gunNo;
		}
		public void setGunNo(String gunNo) {
			this.gunNo = gunNo;
		}
		public String getCarNo() {
			return carNo;
		}
		public void setCarNo(String carNo) {
			this.carNo = carNo;
		}
		public String getHolderName() {
			return holderName;
		}
		public void setHolderName(String holderName) {
			this.holderName = holderName;
		}
		public String getPlateNo() {
			return plateNo;
		}
		public void setPlateNo(String plateNo) {
			this.plateNo = plateNo;
		}
		public Date getFillTime() {
			return fillTime;
		}
		public void setFillTime(Date fillTime) {
			this.fillTime = fillTime;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public BigDecimal getVolume() {
			return volume;
		}
		public void setVolume(BigDecimal volume) {
			this.volume = volume;
		}
		public BigDecimal getReceivable() {
			return receivable;
		}
		public void setReceivable(BigDecimal receivable) {
			this.receivable = receivable;
		}
		public BigDecimal getDiscount() {
			return discount;
		}
		public void setDiscount(BigDecimal discount) {
			this.discount = discount;
		}
		public BigDecimal getReceiptTotal() {
			return receiptTotal;
		}
		public void setReceiptTotal(BigDecimal receiptTotal) {
			this.receiptTotal = receiptTotal;
		}
		public BigDecimal getCardBalance() {
			return cardBalance;
		}
		public void setCardBalance(BigDecimal cardBalance) {
			this.cardBalance = cardBalance;
		}
		public String getCardType() {
			return cardType;
		}
		public void setCardType(String cardType) {
			this.cardType = cardType;
		}
		public String getFillType() {
			return fillType;
		}
		public void setFillType(String fillType) {
			this.fillType = fillType;
		}
		public String getNote() {
			return note;
		}
		public void setNote(String note) {
			this.note = note;
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
	    
		
	    
	    
	   
	      
	  
}
