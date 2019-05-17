package com.lng.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;

import com.lng.model.base.Person;


/**
 * 验证是否登录
 * 
 */
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		Person p = (Person) WebUtils.getSessionAttribute(request, "user");
		if (p == null) {
			String requestType = request.getHeader("X-Requested-With");
			if (requestType != null && requestType.equals("XMLHttpRequest")) {
				// ajax请求
				PrintWriter out = response.getWriter();
				out.print("noLogin");
				out.close();
			} else {
				/**
				 * 普通请求，需要登录 记录前一个页面地址和参数
				 */
				StringBuilder sb = new StringBuilder();
				Enumeration enu = request.getParameterNames();
				while (enu.hasMoreElements()) {
					String paraName = (String) enu.nextElement();
					sb.append(paraName).append("=").append(
							request.getParameter(paraName)).append("&");// 此处用","隔开每个参数，用&时在页面会出错
				}

				StringBuffer returnUrl = request.getRequestURL();
				if (sb.length() > 0) {
					returnUrl.append("?").append(sb.toString());
				}

				request.setAttribute("returnUrl", returnUrl.toString());

				String toLogin = "/login";
				request.getRequestDispatcher(toLogin)
						.forward(request, response);
				// response.sendRedirect("/login");
			}

			return;
		}
		chain.doFilter(servletRequest, servletResponse);

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	public void destroy() {

	}

}
