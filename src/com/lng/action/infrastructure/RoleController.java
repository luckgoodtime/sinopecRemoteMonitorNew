package com.lng.action.infrastructure;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lng.action.main.BaseController;
import com.lng.model.base.Role;
import com.lng.model.base.RoleMenu;
import com.lng.service.base.RoleService;


@Controller
@RequestMapping("/role")
@SuppressWarnings("unchecked")
public class RoleController extends BaseController {

	public static final String DIR = "infrastructure/role/";
	public static final String R = "redirect:/role/list.do";
	
	@Resource
	private RoleService roleService;

	/**
	 * @category 列表
	 */
	@RequestMapping("/list.do")
	public String list(Model model) {
		
		model.addAttribute("p", roleService.getListForPageDefault());
		return DIR + "list";
	}

	
	@RequestMapping(value = "/addOrUpdate.do")
	public String addOrUpdate(Model model, Role o, HttpSession session) {
		if (o.getId() !=null) {
			Role r = (Role)baseService.get(Role.class, o.getId());
			if(r!=null) {
				List menuIdList = baseService.getList("select menuId from RoleMenu where roleId="+r.getId());
				model.addAttribute("menuIds",StringUtils.join(menuIdList, ","));
			}
			model.addAttribute("o",r);
		}
		return DIR + "addOrUpdate";
	}
	
	

	/**
	 * @category 新增
	 */
	@RequestMapping(value = "/addDo.do")
	public String addDo(Model model, Role o,HttpServletRequest request, HttpSession session) throws ParseException {

		// 新增
		roleService.save(o);
		saveMenus(request,o);
		return R;
	}
	
	/**
	 * @category 更新
	 */
	@RequestMapping(value = "/updateDo.do")
	public String updateDo(Model model, Role o,HttpServletRequest request, HttpSession session) throws ParseException {

		//修改
		Role o2 =  (Role)baseService.get(Role.class, o.getId());
		if(o2!=null){
			o2.setName(o.getName());
			roleService.executeUpdate("delete from RoleMenu where roleId =" + o2.getId());
			roleService.update(o2);
			saveMenus(request,o);
		}
		
		return R;
	}
	
	private void saveMenus(HttpServletRequest request,Role o){
		String menuIds = request.getParameter("menuIds");
		if(StringUtils.isNotBlank(menuIds)) {
			List<RoleMenu> objList = new ArrayList<RoleMenu>();
			String[] menuIdStrs = menuIds.split(",");
			for(int i=0;i<menuIdStrs.length;i++){
				if(!"0".equals(menuIdStrs[i])) {
					RoleMenu rm = new RoleMenu();
					rm.setMenuId(Integer.valueOf(menuIdStrs[i]));
					rm.setRoleId(o.getId());
					objList.add(rm);
				}
			}
			baseService.batchSaveOrUpdate(objList);
		}
	}

	/**
	 * @category 删除
	 */
	@RequestMapping(value = "/del")
	public String del(String ids) {

		if (StringUtils.isNotBlank(ids)) {
			roleService.del(ids);
		}
		return R;
	}
	
	
	
}