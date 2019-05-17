package com.lng.action.m;

import java.io.IOException;
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
import com.lng.util.Const;
import com.lng.util.Util;
import com.lng.util.wx.UtilWX;
import com.lng.util.wx.WXUser;

/**
 * 送气工收到的派单任务和相关操作
 * 
 */

@Controller
@RequestMapping("/airDelivery")
public class AirDeliveryMController extends BaseMController {

	@Resource
	private PersonService personService;

	private static final Logger logger = LoggerFactory
			.getLogger(AirDeliveryMController.class);

	public static final String DIR = "airDelivery/";

	@RequestMapping("/list.do")
	public String list(HttpServletRequest request) {

		Person person = (Person) request.getSession().getAttribute("person");
		if(person == null) {
			return LOGIN;
		}
		
		request.setAttribute("p", baseService.getListForPage(
				"from RequestOrder where acceptPerson=?", new Object[] {person.getUsername()},
				Const.PAGESIZE_DEFAULT_M));
		
		return DIR + "list";
	}

}
