package com.lng.util;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpClientUtil {
	public static final MediaType JSON = MediaType
			.parse("application/json;charset=utf-8");
	public static final MediaType XML = MediaType
			.parse("application/xml;charset=utf-8");

	public static String httpGet(String url) throws Exception {
		try {
			OkHttpClient httpClient = new OkHttpClient();
			Request request = new Request.Builder().url(url).build();
			Response response = httpClient.newCall(request).execute();
			return response.body().string(); // 返回的是string 类型，json的mapper可以直接处理
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String httpPostXml(String url, String xml) throws IOException {
		return httpPost(url,xml,XML);
	}
	public static String httpPostJson(String url, String json) throws IOException {
		return httpPost(url,json,JSON);
	}
	public static String httpPost(String url, String content,MediaType contentType) throws IOException {
		OkHttpClient httpClient = new OkHttpClient();
		RequestBody requestBody = RequestBody.create(contentType, content);
		Request request = new Request.Builder().url(url).post(requestBody).build();
		Response response = httpClient.newCall(request).execute();
		return response.body().string();
	}
	
	public static void main(String[] args) throws Exception {
//		String url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx707c260de2cc5a8c&secret=a36cdf8c50904732756f14e82c43cc5e&code=0711pgiM0LdnD32C4iiM0ad3iM01pgiN&grant_type=authorization_code";
//		url ="https://api.weixin.qq.com/sns/userinfo?access_token=9_4ZO1vM0aSvuGeOib6u9TqdS2uQ4hkncEku-gy2DBnNTX0BiaXRfvQG6uV_iL_1U_UfLcRlAwvM_uW786CVUAPw&openid=oW2s4005yfhPKHBWd8W0lcVKyAhI";
//		String returnStr = OkHttpClientUtil.httpGet(url);
//		System.out.println("returnStr:"+returnStr);
		
		postJSON();
	}
	
	public static void postJSON() throws IOException {
		
		  MediaType JSON = MediaType.parse("application/json; charset=utf-8");
		  
		  String s = "{"
		  + "recordType: \"FillingRecord\","
		  + "stationNo: \"L02-01-0001\","
		  + "stationName: \"朗润测试站\","
		  + "data: [{  \"recordId\": \"100\",  \"gunNo\": \"01\",  \"cardNo\": \"01000411200000000003\",  \"holderName\": \"张三\",  \"plateNo\": \"津A12345\",  \"fillTime\": \"2019-05-11 20:55:04\",  \"price\": \"5.23\",  \"volume\": \"34.67\",  \"receivable\": \"181.32\",  \"discount\": \"0.32\",  \"receiptTotal\": \"181.00\",  \"cardBalance\": \"4565.65\",  \"cardType\": \"员工卡\",  \"fillType\": \"LNG\",  \"ttc\": \"2019051100001\",  \"note\": \" \",  \"stationNo\": \"10-0001\",  \"stationName\": \"朗润集团\"},{  \"recordId\": \"101\",  \"gunNo\": \"04\",  \"cardNo\": \"01000111200000000002\",  \"holderName\": \"李四\",  \"plateNo\": \"津A67890\",  \"fillTime\": \"2019-05-12 10:33:56\",  \"price\": \"4.25\",  \"volume\": \"14.06\",  \"receivable\": \"59.76\",  \"discount\": \"0.76\",  \"receiptTotal\": \"59.00\",  \"cardBalance\": \"1233.02\",  \"cardType\": \"用户卡\",  \"fillType\": \"CNG\",  \"ttc\": \"2019051200021\",  \"note\": \" \",  \"stationNo\": \"10-0001\",  \"stationName\": \"朗润集团\"}]"
		 +   "}";
		  
		  s = "{"
		  + "recordType: \"ShiftRecord\","
		  + "stationNo: \"L02-01-0001\","
		  + "stationName: \"朗润测试站\","
		  + "data: [{  \"recordId\": \"45\",  \"shiftNo\": \"2019050101\",  \"startTime\": \"2019-05-01 08:00:00\",  \"endTime\": \"2019-05-01 20:00:00\",  \"confirmTime\": \"2019-05-01 20:10:00\",  \"stationNo\": \"10-0001\",  \"stationName\": \"朗润集团\"},{  \"recordId\": \"46\",  \"shiftNo\": \"2019050102\",  \"startTime\": \"2019-05-01 20:00:00\",  \"endTime\": \"2019-05-02 07:59:00\",  \"confirmTime\": \"2019-05-02 08:11:00\",  \"stationNo\": \"10-0001\",  \"stationName\": \"朗润集团\"}]"
		  + "}";
		  
		  s = "{"
		  + "recordType: \"PriceList\","
		  + "stationNo: \"L02-01-0001\","
		  + "stationName: \"朗润测试站\","
		  + "data: [{  \"recordId\": \"13\",  \"gunNo\": \"02\",  \"oldPrice\": \"4.21\",  \"newPrice\": \"4.23\",  \"publishTime\": \"2019-05-03 11:33:20\",  \"stationNo\": \"10-0001\",  \"stationName\": \"朗润集团\"}]"
		  + "}";
		  
		  s = "{"
		  + "recordType: \"RechargeRecord\","
		  + "stationNo: \"L02-01-0001\","
		  + "stationName: \"朗润测试站\","
		  + "data: [{  \"recordId\": \"36\",  \"carNo\": \"01000411200000000002\",  \"holderName\": \"李四\",  \"operateTime\": \"2019-05-08 21:22:20\",  \"bizAmount\": \"3600.00\",  \"rebate\": \"0.00\",  \"balance\": \"7200.00\",  \"cardType\": \"用户卡\",  \"bizType\": \"充值\",  \"stationNo\": \"10-0001\",  \"stationName\": \"朗润集团\"},{  \"recordId\": \"37\",  \"carNo\": \"01000411200000000005\",  \"holderName\": \"王五\",  \"operateTime\": \"2019-05-08 23:03:11\",  \"bizAmount\": \"-1200.00\",  \"rebate\": \"0.00\",  \"balance\": \"500.00\",  \"cardType\": \"用户卡\",  \"bizType\": \"扣款\",  \"stationNo\": \"10-0001\",  \"stationName\": \"朗润集团\"},{  \"recordId\": \"38\",  \"carNo\": \"01000511200000000009\",  \"holderName\": \"海南公交集团\",  \"operateTime\": \"2019-05-09 11:45:21\",  \"bizAmount\": \"-700.00\",  \"rebate\": \"0.00\",  \"balance\": \"0.00\",  \"cardType\": \"公司账户\",  \"bizType\": \"注销\",  \"stationNo\": \"10-0001\",  \"stationName\": \"朗润集团\"}]"
		  + "}";
		  
		  //String url = "http://47.93.246.231:8084/gasStation/acceptStationData.do";
		  String url = "http://localhost:8080/gasStation/acceptStationData.do";

		  String message = OkHttpClientUtil.httpPostJson(url,s);
		  
		  System.out.println(message);
		
	}
}
