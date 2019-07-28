package com.lng.action.gasStation;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lng.action.main.BaseController;


/**
 * 加气记录
 */
@Controller
@RequestMapping("/stationMgAPP")
public class StationMonitorAPPController extends BaseController {
	
	/**
	 * 
	 *今日/月销量与充值
	 *
	 **/
	@RequestMapping("/generalPerformance.do")
	@ResponseBody
	public JSONObject generalPerformance() {		
		
		JSONObject json = new JSONObject();		
		
		//今日销售量
		Double todaySaleWeight = 20.2;
		//今日销售金额
		Double todaySaleAmount = 132000d;
		//月销售量
		Double monthSaleWeight = 500d;
		//月销售金额
		Double monthSaleAmount = 3000000d;
		//今日存款金额
		Double todayDeposit = 20000d;
		//月存款金额
		Double monthDeposit = 300000d;
		
		
		json.put("todaySaleWeight", todaySaleWeight);

		json.put("todaySaleAmount", todaySaleAmount);

		json.put("monthSaleWeight", monthSaleWeight);
		
		json.put("monthSaleAmount", monthSaleAmount);
		
		json.put("todayDeposit", todayDeposit);

		json.put("monthDeposit", monthDeposit);
		
		return json;
	}
	
	
	/**
	 * 
	 *日销售支付分析
	 *
	 **/
	@RequestMapping("/payWayStat.do")
	@ResponseBody
	public JSONObject payWayStat() {
	
		JSONObject json = new JSONObject();	
		
		String[] payWay = new String[]{"现金", "加气卡", "秒加", "货车帮", "微信", "支付宝", "转账"};
		Double[] data = new Double[]{90.0d, 140.0d, 170.0d, 230.0d, 270.0d, 250.0d, 210.0d}; 

		json.put("payWay", payWay);
		json.put("data", data);
		
		return json;
	}
	
	
	
	
	/**
	 * 
	 *本站班报
	 *
	 *
	 *
	 **/
	@RequestMapping("/stationDailyReport.do")
	@ResponseBody
	public JSONArray stationDailyReport() {
	
		JSONArray json = new JSONArray();	
		
		
		JSONObject shift1 = new JSONObject();
		shift1.put("shiftNo", "早班");
		shift1.put("shiftSaleWeight", 12);
		shift1.put("shiftshiftSaleAmount", 5000);
		shift1.put("shiftSaleCount", 12);//加气笔数		
		
		JSONObject shift2 = new JSONObject();
		shift2.put("shiftNo", "中班");
		shift2.put("shiftSaleWeight", 7);
		shift2.put("shiftshiftSaleAmount", 3567);
		shift2.put("shiftSaleCount", 8);//加气笔数
		
		JSONObject shift3 = new JSONObject();
		
		shift3.put("shiftNo", "晚班");
		shift3.put("shiftSaleWeight", 16);
		shift3.put("shiftshiftSaleAmount", 8124);
		shift3.put("shiftSaleCount", 52);//加气笔数
					
		json.add(shift1);			
		json.add(shift2);
		json.add(shift3);
		
		return json;
	}
	
	
	
	

}
