package com.lng.action.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lng.service.main.BaseService;

@SuppressWarnings( { "unchecked" })
public class BaseController {
	@Resource
	protected BaseService baseService;

	public static final String TIP = "error/tip";
	public static final String LOGIN = "login";
	public static final String VALIDATE_ERROR = "error/validateError";
	
   protected HttpServletRequest request;
   protected HttpServletResponse response;
   protected HttpSession session;
   
   
	
	@InitBinder    
   public void initBinder(WebDataBinder binder) {    
       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
       dateFormat.setLenient(true);    
       binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
   }
   
   
   @ModelAttribute
   public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
       this.request = request;
       this.response = response;
       this.session = request.getSession();
   }
	
	public String suc() {
		return suc(null);
	}
	// 提示操作成功
	public String suc(String tip,String... url) {
		if (StringUtils.isBlank(tip)) {
			return tip(true, "操作成功",url);
		} else {
			return tip(true, tip,url);
		}
	}

	
	// 提示操作成功
	public String err(String tip) {
		if (StringUtils.isBlank(tip)) {
			return tip(false, "操作失败");
		} else {
			return tip(false, tip);
		}
	}

	public String tip(boolean suc, String tip,String... url) {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		req.setAttribute("suc", suc);
		req.setAttribute("tip", tip);
		req.setAttribute("url", url);
		return TIP;
	}

	/** ********************************************************************************************************************* */

	public Map sucMap(String tip) {
		Map m = new HashMap();
		m.put("suc", true);
		if (StringUtils.isBlank(tip)) {
			m.put("tip", "操作成功");
		} else {
			m.put("tip", tip);
		}
		return m;
	}

	public Map errMap(String tip) {
		Map m = new HashMap();
		m.put("suc", false);
		if (StringUtils.isBlank(tip)) {
			m.put("tip", "操作失败");
		} else {
			m.put("tip", tip);
		}
		return m;
	}

	public void outPrint(HttpServletResponse response, String msg) {
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(msg);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *获取请求端的IP,考虑了代理服务器
	 *
	 * 方法来源:<br>
	 * https://www.cnblogs.com/icerainsoft/p/3584532.html
	 * 
	 */
	public String getRemoteHost(javax.servlet.http.HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
}
