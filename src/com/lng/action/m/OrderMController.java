package com.lng.action.m;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.action.main.BaseMController;
import com.lng.model.base.Corporation;
import com.lng.model.base.Person;
import com.lng.model.biz.partner.Customer;
import com.lng.service.base.PersonService;
import com.lng.util.Util;
import com.lng.util.wx.UtilWX;
import com.lng.util.wx.WXUser;


/**
 * 订气业务
 * 
 * 微信登录流程：1、用户同意授权，获取code 2、通过code换取网页授权access_token 3、刷新access_token（如果需要） 4、拉取用户信息(需scope为 snsapi_userinfo)
 * 注意：为了识别用户，每个用户针对每个公众号会产生一个安全的OpenID，如果需要在多公众号
 * 、移动应用之间做用户共通，则需前往微信开放平台，将这些公众号和应用绑定到一个开放平台账号下
 * ，绑定后，一个用户虽然对多个公众号和应用有多个不同的OpenID，
 * 但他对所有这些同一开放平台账号下的公众号和应用，只有一个UnionID，可以在用户管理-获取用户基本信息（UnionID机制）文档了解详情
 */

@Controller
@RequestMapping("/orderM")
public class OrderMController extends BaseMController {

	@Resource
	private PersonService personService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(OrderMController.class);

	public static final String BIND = "wx/bind";
	
	/**
	 * 微信登录：跳转指定地址，获取用户授权
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/wxLogin.do")
	public String wxLogin(HttpServletRequest request) throws ServletException,
			IOException {

		String state = request.getParameter("state");
		if(StringUtils.isBlank(state)) {
			state = "state";
		}
		Calendar c = Calendar.getInstance();
		
		String returnUrl = "/orderM/startOrder.do";
		returnUrl += "?t="+c.getTimeInMillis();
		
		String authorizeUrl = UtilWX.getAuthorizeUrl(returnUrl,state);
		logger.info("authorizeUrl:" + authorizeUrl);

		return "redirect:" + authorizeUrl;
	}

	/**
	 * 跳转到订气页面 获取微信用户信息，然后跳转到订气页面
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/startOrder.do")
	public String startOrder(HttpServletRequest request) throws Exception {

	/*	//测试
		WXUser wxUser = new WXUser();
		wxUser.setOpenid("openid");
		request.getSession().setAttribute("wxUser",wxUser); 
		return "forward:/orderM/airDelivery.do";*/
		
		logger.info("t="+request.getParameter("t"));
		
		WXUser wxUser = UtilWX.getWxUser(request);// 获取微信用户信息
		request.getSession().setAttribute("wxUser", wxUser); 
		
		String state = request.getParameter("state");
		if ("airDelivery".equals(state)) {// 送气派单页面
			return "forward:/orderM/airDelivery.do";
		}else {
			return "wx/customer/startSUI_Order";// 默认用户订气页面
		}
	}

	/**
	 * 送气工派单页面
	 * 根据openid，查询系统是否存在已绑定的用户，如果查询到，则直接登录；如果没有查询到，则开始绑定操作
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/airDelivery.do")
	public String airDelivery(HttpServletRequest request) throws Exception {

		WXUser wxUser = (WXUser)request.getSession().getAttribute("wxUser");// 微信用户信息
		if(wxUser != null && StringUtils.isNotBlank(wxUser.getOpenid())) {
			List<Person> personList	= personService.getListByOpenid(wxUser.getOpenid());
			if(personList != null && personList.size() >0) {//查询到多个用户，只取第一个，然后登录
				Person person = personList.get(0);
				person.setLogDate(Util.dateToStr2(new Date()));
				person.setCorp((Corporation)baseService.get(Corporation.class, person.getCorpPartyId()));//公司
				personService.update(person);
				session.setAttribute("person", person);
				
				Subject currentUser = SecurityUtils.getSubject();  
				UsernamePasswordToken token = new UsernamePasswordToken(person.getUsername(), person.getPassword());  
		        token.setRememberMe(true);  
		        currentUser.login(token);  
		       
		        return "forward:/airDelivery/list.do";//派单列表
			}
		}

		return BIND;//没有查询到，则开始绑定操作
	}
	
	
	/**
	 * 绑定操作
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/bind.do")
	public String bind(HttpServletRequest request) throws Exception {

		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");

		if (StringUtils.isBlank(username)) {
			err("登录名不能空");
			return BIND;
		}
		if (StringUtils.isBlank(pwd)) {
			err("密码不能空");
			return BIND;
		}

		List memberList = baseService.getList("select p from Person p where (p.username=? or p.email=? or p.QQ=? or p.mobile=? ) and p.password=? "
				,new Object[]{username,username,username,username,Util.md5(pwd)});
		
		if (memberList != null && memberList.size() >0) {
			Person person = (Person) memberList.get(0);
			WXUser wxUser = (WXUser)request.getSession().getAttribute("wxUser");// 微信用户信息
			person.setOpenid(wxUser.getOpenid());
			personService.update(person);
			
			 return "forward:/orderM/airDelivery.do";//绑定完成，开始登录
		} else {
			err("登录名或密码错误");
			return BIND;
		}
	}
}
