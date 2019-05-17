
package com.lng.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

/**
 * 基于URL的权限判断过滤器
 * <p>
 * 自动根据URL产生所谓的权限字符串，这一项在Shiro示例中是写在配置文件里面的，默认认为权限不可动态配置
 * <p>
 * URL举例：/User/create.do?***=*** -->权限字符串：/User/create.do
 * 
 */
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {
	/**
	 * 指的是在声明url时指定的权限字符串，如/User/create.do=perms[User:create].
	 */
	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		return super.isAccessAllowed(request, response, buildPermissions(request,mappedValue));
	}

	/**
	 * 根据请求URL产生权限字符串，这里只产生，而比对的事交给Realm 
	 */
	protected String[] buildPermissions(ServletRequest request,Object obj) {
		
		if(obj!=null && obj instanceof String[] )
		{
			String [] str = (String [] )obj;
			if(str.length > 0 && str[0].contains(":")){
				return str;
			}
		}
		
		String[] perms = new String[1];
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletPath();
		perms[0] = path;// path直接作为权限字符串
		
		return perms;
	}

}
