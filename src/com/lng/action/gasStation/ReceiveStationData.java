package com.lng.action.gasStation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lng.action.main.BaseController;
import com.lng.model.remotemonitor.FillingRecord;

@Controller
@RequestMapping("/gasStation")

public class ReceiveStationData extends BaseController{

	
	/**
	 * @category 接收站级数据<br/>
	 * 
	 * 
		{
		recordType:” FillingRecord”,
		stationNo：“0101010”，
		stationName：“山西晋城XXXX加气站“，
		data:[{ “gunNo” :”02”,” cardNo”:”99991232340001”….},{},{}]
		}

	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@RequestMapping(value="/acceptStationData.do", method = RequestMethod.POST)
	@ResponseBody
	public Map acceptStationData(HttpServletRequest request) {
	
		JSONObject obj = JSON.parseObject((String)request.getParameter("p"));
		
		//本次操作是否成功,如果成功，actionResult为保存的记录数
		int result = -1;		 
		 
		System.out.println("-----------");
		System.out.println(obj.toJSONString());
		System.out.println("-----------");
		Map message = new HashMap();
		
		String recordType = obj.getString("recordType");
		String stationNo = obj.getString("stationNo");
		String stationName = obj.getString("stationName");
		
		JSONArray data = (JSONArray)obj.get("data");	
		
		if("FillingRecord".equals(recordType))
			;
		else if("PriceList".equals(recordType)) 
			;
		else if("RechargeRecord".equals(recordType))
			;
		else if("ShiftRecord".equals(recordType))
			;
		
		
		
		message.put("result", result);
		return message;
	}
	
	public static void main(String[] args) {
		String jsonStr = "" + 
		"{"
		+ "\"recordType\":\"FillingRecord\","
		+ "\"stationNo\":\"0101010\","
		+ "\"stationName\":\"山西晋城XXXX加气站\","
		+ "\"data\":[{ \"gunNo\" :\"02\",\"cardNo\":\"99991232340001\"}]"
		+ "}";
		
		JSONObject obj = JSON.parseObject(jsonStr);
		
		System.out.println(obj.toJSONString());
		
		
		// JSONObect to Java Object
		
		//JSONArray.toJavaObject(json, clazz)
		
		//System.out.println(record.getGunNo());
	}
}
