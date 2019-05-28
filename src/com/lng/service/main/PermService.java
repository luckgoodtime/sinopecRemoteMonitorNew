package com.lng.service.main;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lng.model.base.Menu;
import com.lng.model.base.Person;
import com.lng.util.comparator.ComparatorMenu;


@Service
public class PermService extends BaseService{
	
	
//	public List<UserRole> getUserRole(String username){
//		return roleDao.getQuery("from UserRole u where u.username = ?", username).list();
//	}
	
//	public List<List> getPermissions(Long roleId){
//		List<Object[]> list =  roleDao.getPermission(roleId);
//		List<String> perL = new ArrayList();
//		List<String> urlL = new ArrayList();
//		List<List> blist = new ArrayList();
//		if(list!=null){
//			for(int i=0;i<list.size();i++){
//				Object[] obj = list.get(i);
//				if(obj[0]!=null && StringUtils.isNotBlank(obj[0].toString() )){
//					if(obj[0].toString().equals("perms")){
//						perL.add(obj[1].toString());
//					}else{
//						perL.add(obj[0].toString());
//					}
//				}
//				if(obj[1]!=null && StringUtils.isNotBlank(obj[1].toString() )){
//					urlL.add(obj[1].toString());
//				}
//			}
//		}
//		blist.add(perL);
//		blist.add(urlL);
//		return blist;
//	}
	
	/**
	 * 返回所有菜单
	 * roleId 不使用，赋值-1
	 * @return
	 */
	public List allMenuJson(String roleId){
		
		Map rootMap = new HashMap();
		rootMap.put("id", 0);
		rootMap.put("text","根菜单");
		
		//1、取所有菜单
		List allMenuList = getList("from Menu ");
		List menuIdsList = null;
		if(StringUtils.isNotBlank(roleId)){
			menuIdsList = getList("select menuId from RoleMenu where roleId=?",new Object[]{Integer.valueOf(roleId)});
		}
		
		
			//2、先找到一级菜单，然后再找其所有子菜单，递归查询，返回json格式数据
			List childrenList = new ArrayList();//存放一级菜单
			for(int i=0;i<allMenuList.size();i++){
				Menu m = (Menu)allMenuList.get(i);
				if(m.getParentId()==0){
					//当前为一级菜单
					Map firstMenu = new HashMap();
					firstMenu.put("id", m.getId());
					firstMenu.put("text", m.getDes());
//					firstMenu.put("state", "closed");
					if(menuIdsList!=null&&menuIdsList.contains(Integer.valueOf(m.getId()))) {
						firstMenu.put("checked", true);
					}
					//查询其所有子菜单
					childMenu(firstMenu,allMenuList,menuIdsList);
					childrenList.add(firstMenu);
				}
			}
			rootMap.put("children",childrenList);
				
		List rootList = new ArrayList();
		rootList.add(rootMap);
		return rootList;
	}

	/**
	 * 返回某层菜单
	 * @return
	 */
	public List MenuForLevel(String id,String roleId){
		if(StringUtils.isBlank(id)) {
			id ="0";
		}
		
		
		//1、取所有菜单
		List menuList = getList("from Menu where parentId=?",new Object[]{Integer.valueOf(id)});
		List menuIdsList = null;
		if(StringUtils.isNotBlank(roleId)){
			menuIdsList = getList("select menuId from RoleMenu where roleId=?",new Object[]{Integer.valueOf(roleId)});
		}
		
			List childrenList = new ArrayList();//存放一级菜单
			for(int i=0;i<menuList.size();i++){
				Menu m = (Menu)menuList.get(i);
					Map firstMenu = new HashMap();
					firstMenu.put("id", m.getId());
					firstMenu.put("text", m.getDes());
					firstMenu.put("state", "closed");
					if(menuIdsList!=null&&menuIdsList.contains(Integer.valueOf(m.getId()))) {
						firstMenu.put("checked", true);
					}
					
					childrenList.add(firstMenu);
			}
			
			
			if("0".equals(id)) {
				Map rootMap = new HashMap();
				rootMap.put("id", 0);
				rootMap.put("text","根菜单");
				rootMap.put("children",childrenList);
				List rootList = new ArrayList();
				rootList.add(rootMap);
				return rootList;
			}else {
				return childrenList;
			}
		
	}
	
	//递归查询其所有子菜单
	public void childMenu(Map firstMenu, List allMenuList,List menuIdsList) {
		for(int i=0;i<allMenuList.size();i++){
			Menu r = (Menu)allMenuList.get(i);
			if(r.getParentId().equals(firstMenu.get("id"))){
				//当前其下一级菜单
				Map nextMenu = new HashMap();
				nextMenu.put("id", r.getId());
				nextMenu.put("text", r.getDes());
				if(menuIdsList!=null&&menuIdsList.contains(Integer.valueOf(r.getId()))) {
					nextMenu.put("checked", true);
				}
				
				List childrenList = (List)firstMenu.get("children");
				if(childrenList==null){
					childrenList = new ArrayList();
					firstMenu.put("children", childrenList);
				}
				childrenList.add(nextMenu);
				
				//继续寻找下一级菜单的子菜单
				childMenu(nextMenu,allMenuList,menuIdsList);
			}
		}
	}
	
	//是否叶子节点
	public boolean isLeaf(Map menu, List allMenuList){
		Menu m;
		for(int i=0;i<allMenuList.size();i++){
			m = (Menu)allMenuList.get(i);
			if(m.getParentId()==menu.get("id")){
				return false;
			}
		}
		return true;
	}
	
	
	//是否叶子节点
	public boolean isLeaf(Integer id){
		List menuList = getList("from Menu");
		Menu m;
		for(int i=0;i<menuList.size();i++){
			m = (Menu)menuList.get(i);
			if(m.getParentId().equals(id)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 返回用户的一级和二级菜单
	 */
	public List<Menu> menuForUser(){
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String parentId = request.getParameter("parentId");
		if(StringUtils.isBlank(parentId)){
			parentId ="0";
		} 
		
		//取用户权限对应的菜单列表
		Person p = (Person)request.getSession().getAttribute("person");
		List<Menu> menuList = getList("select  m from PersonRole pr,RoleMenu rm,Menu m where pr.roleId=rm.roleId and m.id=rm.menuId and pr.personId=?  "
				,new Object[]{p.getPartyId()});
		
		List<Menu> returnMenuList = new ArrayList<Menu>();
		for(int i=0;i<menuList.size();i++){
			Menu m = (Menu)menuList.get(i);
			if(!returnMenuList.contains(m)){
				returnMenuList.add(m);
			}
			//寻找父菜单
			if(!m.getParentId().equals(0)){
				findFatherMenu(returnMenuList,m);
			}
		}
		//排序
//		Collections.sort(returnMenuList, new ComparatorMenu());
		
		
		return returnMenuList;
	}
	
	public void findFatherMenu(List returnMenuList,Menu m){
		Menu fatherMenu = (Menu)this.get(Menu.class, m.getParentId());
		if(fatherMenu!=null){
			if(!returnMenuList.contains(fatherMenu)){
				returnMenuList.add(fatherMenu);
				if(!fatherMenu.getParentId().equals(0)){
					findFatherMenu(returnMenuList,fatherMenu);
				}
			}
		}
	}
	
}
