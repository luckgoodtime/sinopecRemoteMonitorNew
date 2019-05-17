package com.lng.action.infrastructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.lng.action.main.BaseController;
import com.lng.model.base.Menu;
import com.lng.service.main.PermService;

/**
 * 菜单管理:增、删、改、列表
 */
@Controller
@RequestMapping("/menu")
@SuppressWarnings( { "unchecked","rawtypes" })
public class MenuController extends BaseController {

	public static final String DIR = "infrastructure/menu/";

	@Resource
	protected PermService permService;

	@RequestMapping("/list.do")
	public String list() {
		return DIR + "list";
	}

	@RequestMapping("/listJson.do")
	@ResponseBody
	public List listJson(HttpServletRequest request) {

		String id = request.getParameter("id");
		if (StringUtils.isBlank(id)) {
			id = "0";
		}
		List menuList = baseService.getList("from Menu where parentId=" + id);
		for(int i=0;i<menuList.size();i++){
			Menu r = (Menu)menuList.get(i);
			if(!permService.isLeaf(r.getId())){
				r.setState("closed");
			}
		}
		
		if ("true".equals(request.getParameter("text"))) {
			// 需返回格式数据：{"id":"10","text":"河北省","state":"closed"}
			List retList = new ArrayList();
			for (int i = 0; i < menuList.size(); i++) {
				Menu m = (Menu) menuList.get(i);
				Map menuMap = new HashMap();
				menuMap.put("id", m.getId());
				menuMap.put("text", m.getDes());
				if (!permService.isLeaf(m.getId())) {
					menuMap.put("state", "closed");
				}
				retList.add(menuMap);
			}
			return retList;
		}
		return menuList;
	}

	/**
	 * @category 返回所有菜单，json格式
	 */
	@RequestMapping("/allMenuJson.do")
	@ResponseBody
	public List allMenuJson(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		return permService.allMenuJson(roleId);
	}
	/**
	 * @category 新增或修改页面
	 */
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Model model, Menu o) {
		if (o.getId() != null) {
			model.addAttribute("o", baseService.get(Menu.class, o.getId()));
		}
		return DIR + "addOrUpdate";
	}

	/**
	 * @category 新增或更新
	 */
	@RequestMapping(value = "/addDo.do")
	@ResponseBody
	public Map addDo(Model model, Menu m) {
		 
		m.setParentId(m.getParentId()==null?new Integer(0):m.getParentId());
		
		String layer = "1";
		if(!m.getParentId().equals(0)){
			Menu m2 =  (Menu)baseService.get(Menu.class, m.getParentId());
			if(m2!=null){
				layer = String.valueOf(Integer.parseInt(m2.getLayer())+1);
			}
		}
		m.setLayer(layer);
		baseService.save(m);
	
		return sucMap(null);
	}
	
	/**
	 * @category 新增或更新
	 */
	@RequestMapping(value = "/updateDo.do")
	@ResponseBody
	public Map updateDo(Model model, Menu m) {

		Menu m2 =  (Menu)baseService.get(Menu.class, m.getId());
		m2.setDes(m.getDes());
		m2.setPerm(m.getPerm());
		m2.setUrl(m.getUrl());
		m2.setMenuOrder(m.getMenuOrder());
		m2.setImage(m.getImage());
		m2.setParentId(m.getParentId());
		String layer = "1";
		if(!m2.getParentId().equals(0)){
			Menu m3 =  (Menu)baseService.get(Menu.class, m2.getParentId());
			if(m3!=null){
				layer = String.valueOf(Integer.parseInt(m3.getLayer())+1);
			}
		}
		m2.setLayer(layer);
		
		baseService.update(m2);
	
		return sucMap(null);
	}

	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del.do")
	@ResponseBody
	public Map del(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			baseService.executeUpdate("delete from Menu where id in(" + ids+ ")");
		}
		return sucMap(null);
	}

	
	
	@RequestMapping("/addMenu.do")
	public String addMenu() {
		return DIR + "addMenu";
	}
	
	@RequestMapping("/addMenuDo.do")
	public String addMenuDo(HttpServletRequest request) {
		String des = request.getParameter("des");
		String pre = request.getParameter("pre");
		String parentId = request.getParameter("parentId");
		
		Menu m = new Menu();
		m.setDes(des);
		m.setParentId(Integer.valueOf(parentId));
		m.setPerm(pre+":list");
		m.setUrl("/"+pre+"/list.do");
		m.setLayer("2");
		baseService.save(m);
		
		Integer id = m.getId();
		 m = new Menu();
		m.setDes(des+"-列表");
		m.setParentId(id);
		m.setPerm(pre+":list");
		m.setUrl("/"+pre+"/list.do");
		m.setLayer("3");
		baseService.save(m);
		
		
		 m = new Menu();
		m.setDes(des+"-新增");
		m.setParentId(id);
		m.setPerm(pre+":addDo");
		m.setUrl("/"+pre+"/addDo.do");
		m.setLayer("3");
		baseService.save(m);
		
		 m = new Menu();
		m.setDes(des+"-修改");
		m.setParentId(id);
		m.setPerm(pre+":updateDo");
		m.setUrl("/"+pre+"/updateDo.do");
		m.setLayer("3");
		baseService.save(m);
		
		 m = new Menu();
		m.setDes(des+"-删除");
		m.setParentId(id);
		m.setPerm(pre+":del");
		m.setUrl("/"+pre+"/del.do");
		m.setLayer("3");
		baseService.save(m);
		
		
		
		return suc();
	}
}
