package com.lng.service.main;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lng.dao.BaseDao;
import com.lng.util.Const;
import com.lng.util.Page;

@Service
@SuppressWarnings("unchecked")
public class BaseService {

	@Resource
	private BaseDao baseDao;

	// 批量插入或更新
	public void batchSaveOrUpdate(List objList) {
		baseDao.batchSaveOrUpdate(objList);
	}

	public Serializable save(Object o) {
		return baseDao.save(o);
	}

	public void update(Object o) {
		baseDao.update(o);
	}

	public void del(Object o) {
		baseDao.del(o);
	}

	public Object get(Class c, Serializable id) {
		return baseDao.get(c, id);
	}

	public List getList(String hql) {
		return baseDao.getList(hql);
	}

	public List getList(String hql, Object[] params) {
		return baseDao.getList(hql, params);
	}

	public List getListSQL(String sql) {
		return baseDao.getListSQL(sql);
	}

	public Object getListOne(String hql) {
		return baseDao.getListOne(hql);
	}

	public Object getListOne(String hql, Object[] params) {
		return baseDao.getListOne(hql, params);
	}

	public int getTotalCount(String hql) {
		return baseDao.getTotalCount(hql, null);
	}

	public int getTotalCount(String hql, Object[] params) {
		return baseDao.getTotalCount(hql, params);
	}

	/**
	 * @category 带参数分页查询
	 */
	public Page getListForPage(String hql, Object[] params, int pageSize) {
		int totalRows = baseDao.getTotalCount(hql, params);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		Page p = Page.getPager(request, totalRows, pageSize);
		List dataList = baseDao.getList(hql, p.getStartRow(), p.getPageSize(),
				params);
		p.setDataList(dataList);
		return p;
	}
	/**
	 * @category 带参数分页查询
	 */
	public Page getListForPageSQL(String sql, Object[] params, int pageSize) {
		int totalRows = baseDao.getTotalCountSQL(sql, params);
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		Page p = Page.getPager(request, totalRows, pageSize);
		List dataList = baseDao.getListSQL(sql, p.getStartRow(), p.getPageSize(),
				params);
		p.setDataList(dataList);
		return p;
	}
	
	/**
	 * @category 默认分页查询
	 */
	public Page getListForPageDefault(String hql) {
		return getListForPage(hql, null, Const.PAGESIZE_DEFAULT);
	}

	/**
	 * @category 默认分页查询ajax
	 */
	public Map<String,Object> getListForPageDefaultAjax(String hql) {
		Page p = getListForPage(hql, null, Const.PAGESIZE_DEFAULT);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("total", p.getTotalRows());
		returnMap.put("rows", p.getDataList());
		return returnMap;
	}
	
	/**
	 * @category 默认分页查询ajax
	 */
	public Map<String,Object> getListForPageDefaultAjax(String hql, Object[] params) {
		Page p = getListForPage(hql, params, Const.PAGESIZE_DEFAULT);
		Map<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("total", p.getTotalRows());
		returnMap.put("rows", p.getDataList());
		return returnMap;
	}
	
	/**
	 * @category 默认分页查询
	 */
	public Page getListForPageDefaultSQL(String sql) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String pageSize = request.getParameter("pageSize");
		int pageSize_int = Const.PAGESIZE_DEFAULT;
		try {
			pageSize_int =Integer.parseInt(pageSize);
		} catch (Exception e) {
		}
		return getListForPageSQL(sql, null, pageSize_int);
	}
	/**
	 * @category 默认分页查询
	 */
	public Page getListForPageDefault(String hql, Object[] params) {
		return getListForPage(hql, params, Const.PAGESIZE_DEFAULT);
	}

	// 查询前几条记录
	public List getList(String hql, int num) {
		return baseDao.getList(hql, num);
	}

	public int executeUpdate(String hql) {
		return baseDao.executeUpdate(hql);
	}

	public int executeUpdate(String hql, Object[] params) {
		return baseDao.executeUpdate(hql, params);
	}

	public int executeUpdateSQL(String sql) {
		return baseDao.executeUpdateSQL(sql);
	}
	
	public Criteria getCriteria(Class class1) {		
		Criteria criteria = this.baseDao.getCriteria(class1);		
		return 	criteria;	
	}
	
	public List getList(Criteria criteria){
		
		return this.baseDao.getList(criteria);
	}
}