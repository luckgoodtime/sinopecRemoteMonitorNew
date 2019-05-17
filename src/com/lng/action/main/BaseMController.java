package com.lng.action.main;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/m")
public class BaseMController  extends BaseController {
	public static final String PRE = "m/";//移动文件夹前缀
	public static final String TIP = PRE+"tip";//对用户提示一般和错误信息的页面
	public static final String INDEX = PRE+"index";//首页
	
	/**
	 * 提示操作失败
	 * @param model
	 * @param tip
	 * @return
	 */
	public  String tip(Model model,String tip){  
		model.addAttribute("tip",tip);
		model.addAttribute("suc",false);
		return TIP;
    }
	
	//提示操作成功
	public  String tipSuc(Model model,String tip){  
		model.addAttribute("tip",tip);
		model.addAttribute("suc",true);
		return TIP;
    }
	//默认提示操作成功
	public  String tipSuc(Model model){  
		return tipSuc(model,"操作成功");
    }
}
