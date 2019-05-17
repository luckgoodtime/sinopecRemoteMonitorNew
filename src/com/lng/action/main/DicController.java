package com.lng.action.main;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lng.model.base.Dic;
import com.lng.service.base.DicService;
import com.lng.util.BeanUtils;

/**
 * 数据字典
 */
@Controller
@RequestMapping("/dic")
public class DicController extends BaseController {

	public static final String DIR = "infrastructure/dic/";
	public static final String R = "forward:/dic/list.do";

	@Resource
	private DicService dicService;

	// 列表
	@RequestMapping("/list.do")
	public String list() {
		return DIR + "list";
	}

	// ajax列表
	@RequestMapping("/ajaxList.do")
	@ResponseBody
	public Object ajaxList(Dic dic) {
		return dicService.getListForPageDefaultAjax(dic);
	}

	// 新增页面
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Dic o) {
		if (o.getId() != null) {
			request.setAttribute("o", dicService.get(Dic.class, o.getId()));
		}
		return DIR + "addOrUpdate";
	}

	//新增页面保
	@RequestMapping(value = "/addDo.do")
	public String addDo(Dic o) {
		dicService.save(o);
		return R;
	}

	@RequestMapping(value = "/updateDo.do")
	public String updateDo(Dic o) {

		if (o.getId() != null) {
			Dic dic = (Dic) dicService.get(Dic.class, o.getId());
			if (dic != null) {
				BeanUtils.copyPropertiesIgnoreNull(o, dic);
				dicService.update(dic);
			}
		}
		return R;
	}

}
