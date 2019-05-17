package com.lng.action.main;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.service.biz.order.ArticleService;
import com.lng.service.main.PermService;
import com.lng.util.Util;

/**
 * 首页
 */
@Controller
public class MainController extends BaseController {

	@Resource
	protected PermService permService;
	@Resource
	private ArticleService articleService;
	
	/**
	 * @category 首页面
	 */
	@RequestMapping("/main.do")
	public String main(HttpServletRequest request) {
		if (Util.isMobile()) {
			//获取新闻列表
			request.setAttribute("articleList", articleService.getList(6));
		} 
		return "main";
	}
	
	@RequestMapping("/top.do")
	public String top(Model model) {
		model.addAttribute("menuList",permService.menuForUser()); 
		return "top";
	}
	@RequestMapping("/left.do")
	public String left(Model model) {
		model.addAttribute("menuList",permService.menuForUser()); 
		return "left";
	}
	
	
}