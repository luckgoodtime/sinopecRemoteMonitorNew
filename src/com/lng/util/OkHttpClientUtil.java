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
		  
		  String url = "http://localhost:8080/gasStation/acceptStationData.do";
		  String message = OkHttpClientUtil.httpPostJson(url,s);
		  
		  System.out.println(message);
		
	}
}
