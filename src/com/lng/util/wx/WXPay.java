package com.lng.util.wx;

import java.io.Serializable;

public class WXPay implements Serializable {

	private static final long serialVersionUID = 6658639759570459898L;

	private String paySign;
	private String prepay_id;
	private String nonce_str;
	private String timeStamp;

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "WxPay [paySign=" + paySign + ", prepay_id=" + prepay_id
				+ ", nonce_str=" + nonce_str + ", timeStamp=" + timeStamp + "]";
	}
}
