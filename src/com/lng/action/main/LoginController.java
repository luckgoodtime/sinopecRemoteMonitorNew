package com.lng.action.main;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.aop.SystemLog;
import com.lng.model.base.Corporation;
import com.lng.model.base.Person;
import com.lng.util.Util;

/**
 * 登录
 */
@Controller
public class LoginController extends BaseController {

	/**
	 * @category 登录页面
	 */
	@SystemLog(module = "LoginController模块",methods = "login方法")
	@RequestMapping("/login")
	public String login() {
//		if (Util.isMobile()) {  
//			//移动端
//			System.out.println("LoginController:我是移动");
//		} else {
//			//pc
//			System.out.println("LoginController:我是pc");
//		}
		
		return LOGIN;
	}

	/**
	 * @category 登录处理
	 */
	@RequestMapping("/loginDo.do")
	public String loginDo(Model model, HttpSession session,
			HttpServletRequest request) {

		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");

		if (StringUtils.isBlank(username)) {
			err("登录名不能空");
			return LOGIN;
		}
		if (StringUtils.isBlank(pwd)) {
			err("密码不能空");
			return LOGIN;
		}

		List memberList = baseService.getList("select p from Person p where (p.username=? or p.email=? or p.QQ=? or p.mobile=? ) and p.password=? "
				,new Object[]{username,username,username,username,Util.md5(pwd)});
		
		Person person;
		if (memberList.size() == 0) {
			err("登录名或密码错误");
			return LOGIN;
		} else {
			person = (Person) memberList.get(0);
		}
		person.setLogDate(Util.dateToStr2(new Date()));
		baseService.update(person);
		/**************登录帐号、人员信息、公司对象存入session***********************/
		person.setCorp((Corporation)baseService.get(Corporation.class, person.getCorpPartyId()));//公司
		session.setAttribute("person", person);//登录帐号
		
		Subject currentUser = SecurityUtils.getSubject();  
		UsernamePasswordToken token = new UsernamePasswordToken(username, Util.md5(pwd));  
        token.setRememberMe(true);  
        try {  
            currentUser.login(token);  
        } catch (AuthenticationException e) {  
            e.printStackTrace();  
            session.invalidate();
            err("认证错误"+e.getMessage());
            return LOGIN;
        }  
        if(currentUser.isAuthenticated()){  
        	return "redirect:/main.do";
        }else {
        	 return LOGIN;
        }
        
	}
	
	/**
	 * @category 退出
	 */
	@RequestMapping("/loginOut.do")
	public String loginOut() {
		SecurityUtils.getSubject().logout();
		return LOGIN;
	}

}