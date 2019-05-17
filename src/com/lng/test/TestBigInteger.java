package com.lng.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * 
 * 随意输入一个十六进制字符串，转化成一个超大的数字,比如<br>
 * 
 * 7890CFFF234234234234245634563456 转化成为 160259268914700327621454354730011997270
 * 
 */

public class TestBigInteger {
	
	
	public static void main(String[] args) {
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		String line ;
		BigInteger base = new BigInteger("16");
		try {
			while((line = bufr.readLine()) != null) {
				
				line = line.toUpperCase();		//全部转化成大写		

				BigInteger result = new BigInteger("0");
				for(int i = 0; i < line.length(); i++) {
					char ch = line.charAt(line.length()-1-i);
					if(ch >= 'A' && ch <= 'F'){
						
						//add by MYB on 2018-07-08,怎么计算每一位代表的值
						//那位单数字值 ×（进制所在位数的次方）						
						
						BigInteger tmp = base.pow(i).multiply(new BigInteger(Integer.toString((ch - 'A' + 10))));
						result = result.add(tmp);
					} else {
						BigInteger tmp = base.pow(i).multiply(new BigInteger(Character.toString(ch)));
						result = result.add(tmp);
					}
				}
				System.out.println(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
