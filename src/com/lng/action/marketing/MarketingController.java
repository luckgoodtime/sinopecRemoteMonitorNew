package com.lng.action.marketing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/market")
public class MarketingController {
	
	public static final String DIR = "market/";
	public static final String R = "redirect:/market/listall.do";

	/**
	 * @category 列出全部的市场行情
	 */
	@RequestMapping("/listall.do")
	public String listAll(Model model, HttpSession session,HttpServletRequest request) {
		//model.addAttribute("p", customerService.getListForPageDefault());
		return DIR +  "listAll";
	}
	
	/**
	 * @category 列出关注的市场行情
	 */
	@RequestMapping("/listfocus.do")
	public String listFocus(Model model, HttpSession session,HttpServletRequest request) {
		//model.addAttribute("p", customerService.getListForPageDefault());
		return DIR +  "listFocus";
	}
}
