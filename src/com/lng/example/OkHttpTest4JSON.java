package com.lng.example;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpTest4JSON {

	
	public static void main(String[] args) {
		
		 String json = " {" + 
			"scanBatchNo:'201802010001'," +
		  	"emptyOrFull:'FULL'," +
		  	"inOut:'OUT'," +
 			"bizType:'SALE'," +
 			"longitude:123.45645645," +
 			"latitude:56.34234," +
 			"deviceNo:'X0394059843095'," +
 			"scanParty:'01X00'," +
 			"scanTime:'2018-02-01 16:16:16'," +
 			"customerName:'XXX海鲜大酒楼'," +
 			"money:256," +
 			"note:'今天没给钱,下周五给'," +
 			"scanItems:[" +
		  				"{" +
		  					"scanTime:'2018-02-01 16:10:10'," +
		   					"cylinderNo:'3148343445'" +
		  				"}," +
		   				"{" +
		   					"scanTime:'2018-02-01 16:15:24'," +
		   					"cylinderNo:'3245456456'" +
		  				"}" +
		  			"]" +
				"}";
		
		 OkHttpClient client = new OkHttpClient();
		 
	    RequestBody formBody = new FormBody.Builder()
		            .add("p", json)
		            .build();
		 
		 //String UserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36";
		 
		 
		 Request request = new Request.Builder()
				 .url("http://localhost:8080/smartlpg/saveScanRecords.do")
				 //.removeHeader("User-Agent")
				 //.addHeader("User-Agent", UserAgent)
				 .post(formBody)
				 .build();
		 
		 client.newCall(request).enqueue(new Callback() {

			@Override
			public void onFailure(Call arg0, IOException arg1) {
				System.out.println("----failure------");
				arg1.printStackTrace();
				
			}

			@Override
			public void onResponse(Call arg0, Response resp) throws IOException {
				System.out.println("----success------");
				System.out.println(resp.body().string());
				
			}
			 
		 });
	}
	
}
