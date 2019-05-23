package com.lng.action.gasStation;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lng.action.main.BaseController;
import com.lng.model.base.Person;
import com.lng.model.remotemonitor.FillingRecord;
import com.lng.service.remotemonitor.FillingRecordService;

/**
 * 
 */
@Controller
@RequestMapping("/fillingRecord")
public class FillingRecordController extends BaseController {
	
	
	public static final String DIR = "businessexec/fillingRecord/";
	public static final String R = "forward:/fillingRecord/list.do";
	
	@Resource
	private FillingRecordService fillingRecordService;	
	
	//列表
	@RequestMapping("/list.do")
	public String list() {
		return DIR + "list";
	}

	//ajax列表
	@RequestMapping("/ajaxList.do")
	@ResponseBody
	public Object ajaxList(FillingRecord fillingRecord,HttpServletRequest request) {
		
		Person person = (Person) request.getSession().getAttribute("person");
		return fillingRecordService.getListForPageDefaultAjax(fillingRecord,person);
	}
	
	
	

	
	
}
