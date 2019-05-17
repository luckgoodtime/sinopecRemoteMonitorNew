package com.lng.util;

/**
 *
 * 服务执行返回消息
 * 
 */
public class Message {

	boolean isSuc;
	
	String mesasge;
	
	Integer[] referenceIds;

	public boolean isSuc() {
		return isSuc;
	}

	public void setSuc(boolean isSuc) {
		this.isSuc = isSuc;
	}

	public String getMesasge() {
		return mesasge;
	}

	public void setMesasge(String mesasge) {
		this.mesasge = mesasge;
	}

	public Integer[] getReferenceIds() {
		return referenceIds;
	}

	public void setReferenceIds(Integer[] referenceIds) {
		this.referenceIds = referenceIds;
	}
	
	
	
}
