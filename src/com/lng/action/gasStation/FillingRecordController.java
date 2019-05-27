package com.lng.action.gasStation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lng.action.main.BaseController;
import com.lng.model.base.Dic;
import com.lng.model.base.Person;
import com.lng.model.gasStation.FillingRecord;
import com.lng.service.base.DicService;
import com.lng.service.gasStation.GasStationService;
import com.lng.util.Const;

/**
 * 加气记录
 */
@Controller
@RequestMapping("/fillingRecord")
public class FillingRecordController extends BaseController {

	public static final String DIR = "gasStation/fillingRecord/";
	public static final String R = "forward:/fillingRecord/list.do";

	@Resource
	private GasStationService gasStationService;
	@Resource
	private DicService dicService;// 数据字典
	
	// 列表
	@RequestMapping("/list.do")
	public String list() {
		
		//卡类型
		Dic dic = new Dic();
		dic.setType(Const.CARD_TYPE);
		request.setAttribute("cardTypeList",dicService.getDicList(dic));
		
		//加气类型
		dic.setType(Const.FILL_TYPE);
		request.setAttribute("fillTypeList",dicService.getDicList(dic));
		
		return DIR + "list";
	}

	// ajax列表
	@RequestMapping("/ajaxList.do")
	@ResponseBody
	public Object ajaxList(FillingRecord fillingRecord, HttpServletRequest request) {

		Person person = (Person) request.getSession().getAttribute("person");
		return gasStationService.getListForFillingRecord(fillingRecord, person);
	}

}
