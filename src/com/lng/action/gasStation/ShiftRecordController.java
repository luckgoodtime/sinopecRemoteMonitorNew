package com.lng.action.gasStation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lng.action.main.BaseController;
import com.lng.model.base.Person;
import com.lng.model.gasStation.ShiftRecord;
import com.lng.service.gasStation.GasStationService;

/**
 * 
 */
@Controller
@RequestMapping("/shiftRecord")
public class ShiftRecordController extends BaseController {

	public static final String DIR = "gasStation/shiftRecord/";
	public static final String R = "forward:/shiftRecord/list.do";

	@Resource
	private GasStationService gasStationService;

	// 列表
	@RequestMapping("/list.do")
	public String list() {
		return DIR + "list";
	}

	// ajax列表
	@RequestMapping("/ajaxList.do")
	@ResponseBody
	public Object ajaxList(ShiftRecord shiftRecord, HttpServletRequest request) {

		Person person = (Person) request.getSession().getAttribute("person");
		return gasStationService.getListForShiftRecord(shiftRecord, person);
	}

}
