package com.lng.util.comparator;

import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import com.lng.model.base.Menu;

public class ComparatorMenu implements Comparator {

	public int compare(Object arg0, Object arg1) {
		Menu menu0 = (Menu) arg0;
		Menu menu1 = (Menu) arg1;
		if (StringUtils.isNotBlank(menu0.getMenuOrder())
				&& StringUtils.isNotBlank(menu1.getMenuOrder())) {
			return menu0.getMenuOrder().compareTo(menu1.getMenuOrder());
		}
		return 0;

	}
}
