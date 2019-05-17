package com.lng.action.m;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lng.action.main.BaseMController;
import com.lng.model.biz.Article;
import com.lng.model.biz.ShippingOrder;
import com.lng.service.biz.order.ArticleService;

@Controller
@RequestMapping("/articleM")
public class ArticleMController extends BaseMController{

	public static final String DIR = "article/";
	@Resource
	private ArticleService articleService;
	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request) {
		
//		request.setAttribute("p", shippingOrderService.getListForPageDefault());
		return DIR+"list";
	}
	
	/**
	 * @category 列表
	 */
	@RequestMapping("/listJson.do")
	@ResponseBody
	public Object listJson(HttpServletRequest request) {
		
		return articleService.getListForPageDefault();
	}
	
	/**
	 * @category 详细页
	 */
	@RequestMapping("/view.do")
	public String view(HttpServletRequest request) {

		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			request.setAttribute("article",baseService.get(Article.class, Integer.parseInt(id)));
		}
		
		return DIR+"view";
	}
	

}
