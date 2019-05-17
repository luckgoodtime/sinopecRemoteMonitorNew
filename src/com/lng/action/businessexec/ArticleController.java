package com.lng.action.businessexec;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.action.main.BaseController;
import com.lng.model.base.Person;
import com.lng.model.biz.Article;
import com.lng.service.biz.order.ArticleService;
import com.lng.util.Util;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController{

	public static final String DIR = "businessexec/article/";
	public static final String R = "redirect:/article/list.do";
	
	@Resource
	private ArticleService articleService;
	
	
	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request) {
		
		request.setAttribute("p", articleService.getListForPageDefault());
		return DIR + "list";
	}
	
	
	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Article o, HttpServletRequest request) {
		if (o.getId() !=null) {
			Article o2 = (Article)baseService.get(Article.class, o.getId());
			request.setAttribute("o", o2);
			
		}
		return DIR + "addOrUpdate";
	}

	/**
	 * @throws ParseException 
	 * @category 新增
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(@Valid @ModelAttribute("o")Article o,Errors errors,HttpServletRequest request, HttpSession session) {

		if (errors.hasErrors()) return VALIDATE_ERROR;
		// 新增
		Person person = (Person) session.getAttribute("person");
		o.setUserId(person.getPartyId());
		o.setUserName(person.getUsername());
		o.setUpdateDate(Util.dateToStr2(new Date()));
		o.setAudit(0);
		o.setLookNum(0);
		baseService.save(o);
	
		return R;
	}
	
	/**
	 * @throws ParseException 
	 * @category 更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(@Valid @ModelAttribute("o")Article o, Errors errors,HttpServletRequest request, HttpSession session) {

		if (errors.hasErrors()) return VALIDATE_ERROR;
		//修改
		Article o2 =  (Article)baseService.get(Article.class, o.getId());
		if(o2!=null){
			
			o2.setTitle(o.getTitle());
			o2.setContent(o.getContent());
			Person person = (Person) session.getAttribute("person");
			o.setUserId(person.getPartyId());
			o.setUserName(person.getUsername());
			o.setUpdateDate(Util.dateToStr2(new Date()));
			
			baseService.update(o2);
		}
	
		return R;
	}

	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del")
	public String del(String ids) {

		if (StringUtils.isNotBlank(ids)) {
			articleService.del(ids);
		}
		return R;
	}


}
