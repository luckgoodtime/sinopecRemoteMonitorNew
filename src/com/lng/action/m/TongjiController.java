package com.lng.action.m;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.action.main.BaseMController;

@Controller
@RequestMapping("/tongji")
public class TongjiController extends BaseMController{

	public static final String DIR = "tongji/";
	/**
	 * @category 概述
	 */
	@RequestMapping("/summary.do")
	public String list(HttpServletRequest request) {
		
		
		return DIR+"summary";
	}
	
	/**
	 * @category 里程分布
	 */
	@RequestMapping("/mileageDistribution.do")
	public String mileageDistribution(HttpServletRequest request) {
		
		//http://192.168.15.6:8080/lng/tongji/mileageDistribution.do
//		System.err.println(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+request.getServletPath() );
		return DIR+"mileageDistribution";
	}
	/**
	 * @category 客户类型
	 */
	@RequestMapping("/customType.do")
	public String customType(HttpServletRequest request) {
		
		return DIR+"customType";
	}
	/**
	 * @category 地理分布
	 */
	@RequestMapping("/geographicDistribution.do")
	public String geographicDistribution(HttpServletRequest request) {
		
		return DIR+"geographicDistribution";
	}
}
