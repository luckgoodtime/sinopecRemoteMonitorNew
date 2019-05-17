package com.lng.service.base;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lng.model.base.Role;
import com.lng.service.main.BaseService;
import com.lng.util.Page;

@Service
public class RoleService extends BaseService {

	public Page getListForPageDefault() {
		return this.getListForPageDefault("from Role order by id desc");
	}

	public int del(String ids) {
		String[] idsStr = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			this.executeUpdate("delete from RoleMenu where roleId ="
					+ idsStr[i]);
		}
		return this.executeUpdate("delete from Role where id in (" + ids + ")");
	}

	public List getRoleList(Integer partyId) {
		List roleList = this.getList("from Role order by id desc");
		if (partyId != null) {
			List roleIdList = this.getList(
					"select roleId from PersonRole where personId=?",
					new Object[] { partyId });
			if (roleIdList.size() > 0) {
				for (int i = 0; i < roleList.size(); i++) {
					Role r = (Role) roleList.get(i);
					if (roleIdList.contains(r.getId())) {
						r.setChecked(true);
					}
				}
			}
		}

		return roleList;
	}

	public List getRoleListFromPerson(Integer partyId) {
		List roleList = this
				.getList(
						"select  r from Role r,PersonRole pr where r.id=pr.roleId and pr.personId =?",
						new Object[] { partyId });
		return roleList;
	}

	public List getMenuListByRole(Integer roleId) {
		List menuList = this
				.getList(
						"select m from Menu m,RoleMenu rm where m.id=rm.menuId and rm.roleId =?",
						new Object[] { roleId });
		return menuList;
	}
	
	public List getMenuList() {
		List menuList = this.getList(" from Menu m");
		return menuList;
	}
}
