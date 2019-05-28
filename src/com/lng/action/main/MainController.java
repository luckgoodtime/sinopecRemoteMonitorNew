package com.lng.action.main;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.model.base.Menu;
import com.lng.service.biz.order.ArticleService;
import com.lng.service.main.PermService;

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
//		if (Util.isMobile()) {
//			//获取新闻列表
//			request.setAttribute("articleList", articleService.getList(6));
//		} 
		//显示第一个默认菜单
		List<Menu> returnMenuList = permService.menuForUser();
		if(returnMenuList != null && returnMenuList.size() >0) {
			for(int i=0;i<returnMenuList.size();i++){
				Menu m = returnMenuList.get(i);
				if(m.getParentId() != null && !m.getParentId().equals(0) 
						&& StringUtils.isNotBlank(m.getUrl())){
					request.setAttribute("m",m);
					break;
				}
			}
			
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