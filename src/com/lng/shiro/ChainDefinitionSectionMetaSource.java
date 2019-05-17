package com.lng.shiro;

import java.text.MessageFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;

import com.lng.model.base.Menu;
import com.lng.service.base.RoleService;

/**
 * 获取url与授权的资源
 * 
 * @Description: 授权链，服务启动的时候会加载
 * 
 */
public class ChainDefinitionSectionMetaSource implements
		FactoryBean<Ini.Section> {
	private String filterChainDefinitions;
	public static final String PREMISSION_STRING = "perms[\"{0}\"]";
	@Resource
	public RoleService roleService;

	public Section getObject() throws BeansException {
		List menuList = roleService.getMenuList();
		Ini ini = new Ini();
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		for (int i = 0; i < menuList.size(); i++) {
			Menu m = (Menu) menuList.get(i);
			if (StringUtils.isNotEmpty(m.getUrl())
					&& StringUtils.isNotEmpty(m.getPerm())) {
				String per = m.getPerm();
				String format = per.contains(":") ? MessageFormat.format(
						PREMISSION_STRING, m.getPerm()) : per;
				section.put(m.getUrl(), format);
			}
		}
		section.put("/**", "authc");
		return section;
	}

	public String getFilterChainDefinitions() {
		return filterChainDefinitions;
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	public Class<?> getObjectType() {
		return this.getClass();
	}

	public boolean isSingleton() {
		return false;
	}
	
	public static void main(String[] args) {
		
		String per = "corporation:updateDo";
		
		String format = per.contains(":") ? MessageFormat.format(
				PREMISSION_STRING, per) : per;
				
		System.out.println(format);		
		
	}

}
