package com.lng.test;

public class GeneralTest {

	public static void main(String[] args) {
		
		String s = ",3413002705,自有,浙江海圳荣液化石油气工业有限公司,浙江银盾压力容器有限公司,2016-11-30,B2333341000002,110040067,2016-11-30,液化石油气,YSP35.5,液化石油气钢瓶,2016-11-30,2020-11-30,正常,,0,35.5,2.5,0,2.1";
		
		
		String[] ss = s.split(",");
		
		System.out.println(ss.length);
		
		System.out.println(ss[0].length());
		
	}
}
