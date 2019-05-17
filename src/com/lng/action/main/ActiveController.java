package com.lng.action.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.model.base.Person;
import com.lng.util.Const;
import com.lng.util.Util;

/**
 * 激活人员，生成帐号
 *
 */
@Controller
@RequestMapping("/active")
@SuppressWarnings({"unchecked" })
public class ActiveController extends BaseController{
	
	public static final String DIR = "active/";
	public static final String R = "redirect:/address/list.do";
	
	
	/**
	 * @category 输入激活信息
	 */
	@RequestMapping("/active")
	public String list(Model model, HttpSession session) {
		
		return DIR + "active";
	}
	
	/**
	 * @category 激活结果
	 */
	@RequestMapping(value = "/activeDo")
	public String activeDo(Model model,HttpServletRequest request) {
		String info = request.getParameter("info");
		if(StringUtils.isBlank(info)) {
			return err("请填写信息");
		}
		Person p = (Person)baseService.getListOne("from Person where email=? or QQ=? or mobile=?", new Object[]{info,info,info});
		if(p==null) {
			return err( "您的信息不存在，请认真核对");
		}
		if(Const.YJH.equals(p.getActive())) {
			return err( "已激活过，请不要重复");
		}
		request.setAttribute("p", p);
		
		return DIR + "activeDo";
	}

	/**
	 * @category 报错密码
	 */
	@RequestMapping(value = "/savePwd")
	public String savePwd(Model model,HttpServletRequest request) {
		
		String pwd = request.getParameter("pwd");
		if(StringUtils.isBlank(pwd)) {
			return err( "请填写密码");
		}
		if(pwd.length()<6 || pwd.length()>20){
			return err( "密码长度必须为6~20个字符");
		}
		String pwd2 = request.getParameter("pwd2");
		if(!pwd.equals(pwd2)) {
			return err( "两次密码不一致");
		}
		
		String msg = activeDo(model,request);
		Person p = (Person)request.getAttribute("p");
		if(p==null){
			return err( msg);
		}
		p.setPassword(Util.md5(pwd));
		baseService.update(p);
		
		return suc( "恭喜，激活成功","<a href='"+request.getContextPath()+"/'>完成</a>");
		
	}
}
