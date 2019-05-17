package com.lng.shiro;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import com.lng.model.base.Menu;
import com.lng.model.base.Person;
import com.lng.model.base.Role;
import com.lng.service.base.RoleService;

public class MyRealm extends AuthorizingRealm {
	
	
	@Resource
	public RoleService roleService;
	
	/**
	 * 为当前登录的Subject授予角色和权限
	 * 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Session session = SecurityUtils.getSubject().getSession(); 
		Person p = (Person)session.getAttribute("person");
		if(p==null) return null;
		List roleList = roleService.getRoleListFromPerson(p.getPartyId());
		 //为当前用户设置角色和权限
		 SimpleAuthorizationInfo simpleAuthorInfo = new  SimpleAuthorizationInfo();
		 for(int i=0;i<roleList.size();i++){
			 Role r = (Role)roleList.get(i);
			 simpleAuthorInfo.addRole(r.getName());
			 
			 List menuList = roleService.getMenuListByRole(r.getId());
			 for(int j=0;j<menuList.size();j++){
				 Menu  menu =  (Menu)menuList.get(j);
				 simpleAuthorInfo.addStringPermission(menu.getPerm());
			 }
			 
		 }
		 
		return simpleAuthorInfo;
	}

	/**
	 * 验证当前登录的Subject
	 * 
	 * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		// 获取基于用户名和密码的令牌
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		// 两个token的引用都是一样的
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		
		AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(token.getUsername(),token.getPassword(), this.getName());
		return authcInfo;
	}
	
}