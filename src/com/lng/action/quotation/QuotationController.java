package com.lng.action.quotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quotation")
public class QuotationController {

	public static final String DIR = "quotation/";
	public static final String R = "redirect:/market/listall.do";

	/**
	 * @category 列出出厂报价（大众报价爱）
	 */
	@RequestMapping("/listPublic.do")
	public String listPublic(Model model, HttpSession session,HttpServletRequest request) {
		//model.addAttribute("p", customerService.getListForPageDefault());
		return DIR +  "listPublic";
	}
	
	/**
	 * @category 列出具体报价
	 */
	@RequestMapping("/listSpecific.do")
	public String listSpecific(Model model, HttpSession session,HttpServletRequest request) {
		//model.addAttribute("p", customerService.getListForPageDefault());
		return DIR +  "listSpecific";
	}
	
	/**
	 * @category 列出具体报价
	 */
	@RequestMapping("/listEnquiry.do")
	public String listEnquiry(Model model, HttpSession session,HttpServletRequest request) {
		//model.addAttribute("p", customerService.getListForPageDefault());
		return DIR +  "listEnquiry";
	}
}
