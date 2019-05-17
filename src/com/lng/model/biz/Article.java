package com.lng.model.biz;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Article {
	
		@Id
		@GenericGenerator(name = "generator", strategy = "native")
		@GeneratedValue(generator = "generator")
		private Integer id;  
		private String title;  
		private String content; 
	    private Integer audit;  
	    private Integer userId;
	    private String userName;
	    private String updateDate;
	    private Integer lookNum;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public Integer getAudit() {
			return audit;
		}
		public void setAudit(Integer audit) {
			this.audit = audit;
		}
		public Integer getUserId() {
			return userId;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(String updateDate) {
			this.updateDate = updateDate;
		}
		public Integer getLookNum() {
			return lookNum;
		}
		public void setLookNum(Integer lookNum) {
			this.lookNum = lookNum;
		}
	    
	  
	    
	   
	      
	  
}
