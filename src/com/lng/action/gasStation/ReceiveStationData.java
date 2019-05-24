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
import com.lng.model.remotemonitor.FillingRecord;
import com.lng.model.remotemonitor.PriceList;
import com.lng.model.remotemonitor.RechargeRecord;
import com.lng.model.remotemonitor.ShiftRecord;

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
	
	
	

}
