package com.lng.util.set;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lng.model.base.Truck;

public class AutoSet {
	/**
	 * 根据属性名获取属性值
	 */
	private Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据属性名获取属性值
	 */
	private Object setFieldValueByName(String fieldName, Object o,Class type,Object value) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "set" + firstLetter + fieldName.substring(1);
			
			Method method = o.getClass().getDeclaredMethod(getter,type);
			return method.invoke(o, value);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取属性名数组
	 */
	private String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getType());
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}

	/**
	 * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
	 */
	private List getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		List list = new ArrayList();
		Map infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap();
			infoMap.put("type", fields[i].getType());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
			list.add(infoMap);
		}
		return list;
	}

	public static void setObject(Object source,Object target){
		//获取对象source的所有属性和属性值
		AutoSet as = new AutoSet();
		List list = as.getFiledsInfo(source);
		//设置对象target对应属性值，不包括属性id、partyId
		Map infoMap = null;
		for(int i=0;i<list.size();i++){
			infoMap = (Map)list.get(i);
			String name = String.valueOf(infoMap.get("name"));
			if(!name.equals("id")&&!name.equals("partyId")){
				as.setFieldValueByName(name, target,(Class)infoMap.get("type"),infoMap.get("value"));
			}
		}
		
	}
	

}
