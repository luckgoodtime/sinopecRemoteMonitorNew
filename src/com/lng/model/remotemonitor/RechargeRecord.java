package com.lng.model.remotemonitor;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

//充值表
@Entity
public class RechargeRecord {
	
		@Id
		@GenericGenerator(name = "generator", strategy = "native")
		@GeneratedValue(generator = "generator")
		private Integer id;  //主键
		private String recordId;  //记录ID
		private String carNo;//卡号
		private String holderName;//持卡人姓名
		private Date operateTime;//操作时间
		private BigDecimal bizAmount;  //交易金额 有正有负
		private BigDecimal rebate;  //返利
		private BigDecimal balance;  //交易后余额
		private String cardType;//卡类型-员工卡 用户卡 计量卡 公司账户
		private String bizType;//交易类型 充值 扣款 圈存 注销
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
		public Date getOperateTime() {
			return operateTime;
		}
		public void setOperateTime(Date operateTime) {
			this.operateTime = operateTime;
		}
		public BigDecimal getBizAmount() {
			return bizAmount;
		}
		public void setBizAmount(BigDecimal bizAmount) {
			this.bizAmount = bizAmount;
		}
		public BigDecimal getRebate() {
			return rebate;
		}
		public void setRebate(BigDecimal rebate) {
			this.rebate = rebate;
		}
		public BigDecimal getBalance() {
			return balance;
		}
		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}
		public String getCardType() {
			return cardType;
		}
		public void setCardType(String cardType) {
			this.cardType = cardType;
		}
		public String getBizType() {
			return bizType;
		}
		public void setBizType(String bizType) {
			this.bizType = bizType;
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
