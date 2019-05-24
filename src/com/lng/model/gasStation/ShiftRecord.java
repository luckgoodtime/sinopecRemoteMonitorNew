package com.lng.model.gasStation;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

//上下班表
@Entity
public class ShiftRecord {
	
		@Id
		@GenericGenerator(name = "generator", strategy = "native")
		@GeneratedValue(generator = "generator")
		private Integer id;  //主键
		private String recordId;  //站级记录ID
		@Column(nullable=true,length=50)
		private String shiftNo;  //班组号
		private Date startTime; //上班时间
	    private Date endTime;//下班时间
	    private Date confirmTime;//审核时间
	    @Column(length=64)
	    private String stationNo;//加气站编号
	    @Column(length=64)
	    private String stationName;//加气站名称
	    private Integer belgon2PartyId;//公司
	    
	    
		public String getRecordId() {
			return recordId;
		}
		public void setRecordId(String recordId) {
			this.recordId = recordId;
		}
		public String getShiftNo() {
			return shiftNo;
		}
		public void setShiftNo(String shiftNo) {
			this.shiftNo = shiftNo;
		}
		public Date getStartTime() {
			return startTime;
		}
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}
		public Date getEndTime() {
			return endTime;
		}
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		public Date getConfirmTime() {
			return confirmTime;
		}
		public void setConfirmTime(Date confirmTime) {
			this.confirmTime = confirmTime;
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
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
	    
	   
	      
	  
}
