package com.lng.util;

import java.security.MessageDigest;

/**
 * MD5 加密
 */
public class MD5Utils {

	private static MD5Utils md5 = null;
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f" };
	/**
	 * 实例化方法
	 * 
	 * @return MD5加密对象
	 */
	public static synchronized MD5Utils getInstance() {
		if (md5 == null) {
			md5 = new MD5Utils();
		}
		return md5;
	}

	/**
	 * 对字符串加密
	 * 
	 * @param input
	 *            明文
	 * @return String 加密后的密文
	 */
	public String encrypt(String input) {
		String ret = null;
		if (input == null) {
			return ret;
		}
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			ret = asHex(md.digest(input.getBytes()));
		} catch (Exception e) {
		}
		return ret;
	}

	private String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");
			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	/**
	 * 对字符串加密
	 *
	 * @param input 明文
	 * @param charset 字符串编码 默认
	 * @return String 加密后的密文
	 */
	public String encrypt(String input,String charset) {
		String resultString = null;
		if(null == charset || "".equals(charset)){
			charset = "UTF-8";
		}
		try {
			resultString = input;
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes(charset)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultString;
	}

	private String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	private String byteArrayToHexString(byte b[]) {
		StringBuilder resultSb = new StringBuilder();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}
}
