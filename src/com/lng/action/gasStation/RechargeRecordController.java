package com.lng.action.gasStation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lng.action.main.BaseController;
import com.lng.model.base.Person;
import com.lng.model.gasStation.RechargeRecord;
import com.lng.service.gasStation.GasStationService;

/**
 * 
 */
@Controller
@RequestMapping("/rechargeRecord")
public class RechargeRecordController extends BaseController {

	public static final String DIR = "gasStation/rechargeRecord/";
	public static final String R = "forward:/rechargeRecord/list.do";

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
	public Object ajaxList(RechargeRecord rechargeRecord, HttpServletRequest request) {

		Person person = (Person) request.getSession().getAttribute("person");
		return gasStationService.getListForRechargeRecord(rechargeRecord, person);
	}

}
