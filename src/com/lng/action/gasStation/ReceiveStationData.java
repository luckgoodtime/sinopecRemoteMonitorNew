package com.lng.action.gasStation;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lng.action.main.BaseController;
import com.lng.model.gasStation.FillingRecord;
import com.lng.model.gasStation.PriceList;
import com.lng.model.gasStation.RechargeRecord;
import com.lng.model.gasStation.ShiftRecord;

@Controller
@RequestMapping("/gasStation")

public class ReceiveStationData extends BaseController{

	
	/**
	 * @param <T>
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
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/acceptStationData.do", method = RequestMethod.POST)
	@ResponseBody
	public <T> Map acceptStationData(HttpServletRequest request) {
	
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
		
		List<T> list = null;
		
		if("FillingRecord".equals(recordType)) {
			list = (List<T>) ReceiveStationData.jsonToList(data.toJSONString(), FillingRecord.class);
		} else if("PriceList".equals(recordType)) {			
			list = (List<T>) ReceiveStationData.jsonToList(data.toJSONString(), PriceList.class);	
		} else if("RechargeRecord".equals(recordType)) {			
			list = (List<T>) ReceiveStationData.jsonToList(data.toJSONString(), RechargeRecord.class);	
		} else if("ShiftRecord".equals(recordType)){
			list = (List<T>) ReceiveStationData.jsonToList(data.toJSONString(), ShiftRecord.class);
		}
		
		if(list != null && list.size() > 0) {
			for(Object bean : list) {
				try {
					BeanUtils.copyProperty(bean, "stationNo", stationNo);
					BeanUtils.copyProperty(bean, "stationName", stationName);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			this.baseService.batchSaveOrUpdate(list);
		}
		
		message.put("result", result);
		return message;
	}
	
	
	public static <T> List<T> jsonToList(String jsonStr, Class<T> clazz) {
		List<T> list = (List<T>) JSON.parseArray(jsonStr, clazz);
		return list;
	}
	
	
	public static <T> void main(String[] args) {
		
		String s = "{ recordType: \"FillingRecord\", stationNo: \"L02-01-0001\", stationName: \"朗润测试站\", data: [{ \"gunNo\": \"01\", \"cardNo\": \"01000411200000000003\", \"holderName\": \"张三\", \"plateNo\": \"津A12345\", \"fillTime\": \"2019-05-11 20:55:04\", \"Price\": \"5.23\", \"volume\": \"34.67\", \"receivable\": \"181.32\", \"discount\": \"0.32\", \"receiptTotal\": \"181.00\", \"cardBalance\": \"4565.65\", \"cardType\": \"员工卡\", \"fillType\": \"LNG\", \"ttc\": \"2019051100001\", \"note\": \" \"},{ \"gunNo\": \"04\", \"cardNo\": \"01000111200000000002\", \"holderName\": \"李四\", \"plateNo\": \"津A67890\", \"fillTime\": \"2019-05-12 10:33:56\", \"Price\": \"4.25\", \"volume\": \"14.06\", \"receivable\": \"59.76\", \"discount\": \"0.76\", \"receiptTotal\": \"59.00\", \"cardBalance\": \"1233.02\", \"cardType\": \"用户卡\", \"fillType\": \"CNG\", \"ttc\": \"2019051200021\", \"note\": \" \"}] }";
		
		JSONObject obj = JSON.parseObject(s);
		
		String recordType = obj.getString("recordType");
		String stationNo = obj.getString("stationNo");
		String stationName = obj.getString("stationName");
		
		JSONArray data = (JSONArray)obj.get("data");
		
		List<T> list = null;
		list = (List<T>) ReceiveStationData.jsonToList(data.toJSONString(), FillingRecord.class);
		
		if(list != null && list.size() > 0) {
			for(Object bean : list) {
				try {
					BeanUtils.copyProperty(bean, "stationNo", stationNo);
					BeanUtils.copyProperty(bean, "stationName", stationName);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("记录数:" + list.size());
			System.out.println(((FillingRecord)list.get(0)).getStationName());
			System.out.println(((FillingRecord)list.get(0)).getStationNo());
			System.out.println(((FillingRecord)list.get(0)).getPrice());
			System.out.println(((FillingRecord)list.get(0)).getVolume());
			System.out.println(((FillingRecord)list.get(0)).getReceivable());
			System.out.println(((FillingRecord)list.get(0)).getTtc());
			System.out.println(((FillingRecord)list.get(0)).getCardType());
		}
	}
	
	

}
