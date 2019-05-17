package com.lng.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

/**
 * MD5
 */
public class MD5FileUtils {

	public static String getMd5ByFile(File file) throws FileNotFoundException {
		String value = null;
		FileInputStream in = new FileInputStream(file);
		try {
			MappedByteBuffer byteBuffer = in.getChannel().map(
					FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	
	public static String getMd5ByFile2(byte[] cont) throws FileNotFoundException {
		String value = null;
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(cont);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return value;
	}
	
	public static byte[] readToString(String fileName) {
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return filecontent;
	
	}

	public static void main(String[] args) throws IOException {

//		 String path="E:\\安装包\\commons-codec-1.11-bin.zip";
		String path = "E:\\安装包\\deepin-15.5-amd64.iso";

//		String v = getMd5ByFile(new File(path));
//		String v = getMd5ByFile2(readToString(path));
//		
//		System.out.println("MD5:" + v.toUpperCase());

		FileInputStream fis = new FileInputStream(path);
		String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
		IOUtils.closeQuietly(fis);
		System.out.println("MD5:" + md5);

		// 0e6313486b10abb5187bfbe8768ae4bf
		
		
	}

}
