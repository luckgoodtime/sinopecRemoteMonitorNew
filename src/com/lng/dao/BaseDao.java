package com.lng.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;



@Repository
@SuppressWarnings("unchecked")
public class BaseDao {

	private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);
	
	@Resource
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	// 批量插入或更新
	public void batchSaveOrUpdate(List objList) {
		for (int i = 0; i < objList.size(); i++) {
			getCurrentSession().saveOrUpdate(objList.get(i));
			if (i % 15 == 0) {
				getCurrentSession().flush();
				getCurrentSession().clear();
			}
		}
	}

	// 新增
	public Serializable save(Object o) {
		return getCurrentSession().save(o);
	}

	public void update(Object o) {
		getCurrentSession().update(o);
	}

	public void del(Object o) {
		getCurrentSession().delete(o);
		
	}

	public Object get(Class c, Serializable id) {
		return getCurrentSession().get(c, id);
	}

	public List getList(String hql) {
		return getList(hql, null);
	}

	public List getList(String hql, Object[] params) {
		Query q = getCurrentSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				logger.info(i+":"+params[i]);
				q.setParameter(i, params[i]);
			}
		}
		return q.list();
	}

	public List getListSQL(String hql) {
		return getListSQL(hql, null);
	}

	public List getListSQL(String sql, Object[] params) {
		Query q = getCurrentSession().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				  if(params[i] instanceof Object[]){  
	            	  q.setParameterList(String.valueOf(i),(Object[])params[i]); 
	              }else{  
	            	  q.setParameter(i,params[i]); 
	              }  
			}
		}
		return q.list();
	}

	// hql查询数量
	public int getTotalCount(String hql, Object[] params) {
		// 去掉select a
		if (hql.indexOf("select") > -1) {
			hql = hql.substring(hql.indexOf("from"), hql.length());
		}

		Query q = getCurrentSession().createQuery("select count(*) " + hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return ((Long) q.list().get(0)).intValue();
	}
	// sql查询数量
	public int getTotalCountSQL(String sql, Object[] params) {
		// 去掉select a
		if (sql.indexOf("select") > -1) {
			sql = sql.substring(sql.indexOf("from"), sql.length());
		}

		Query q = getCurrentSession().createSQLQuery("select count(*) " + sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return ((BigInteger) q.list().get(0)).intValue();
	}
	
	
	public List getList(String hql, int firstResult, int maxresult,
			Object[] params) {
		Query q = getCurrentSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				logger.info(i+":"+params[i]);
				q.setParameter(i, params[i]);
			}
		}

		q.setFirstResult(firstResult);
		q.setMaxResults(maxresult);
		return q.list();
	}

	public List getListSQL(String sql, int firstResult, int maxresult,
			Object[] params) {
		Query q = getCurrentSession().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}

		q.setFirstResult(firstResult);
		q.setMaxResults(maxresult);
		return q.list();
	}

	// 查询前几条记录
	public List getList(String hql, int num) {
		Query q = getCurrentSession().createQuery(hql);
		q.setFirstResult(0);
		q.setMaxResults(num);
		return q.list();
	}

	public int executeUpdate(String hql) {
		Query q = getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	public int executeUpdate(String hql, Object[] params) {
		Query q = getCurrentSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		return q.executeUpdate();
	}

	public int executeUpdateSQL(String sql) {
		Query q = getCurrentSession().createSQLQuery(sql);
		return q.executeUpdate();
	}

	public Object getListOne(String hql) {
		List l = getList(hql);
		return (l.size() > 0) ? (l.get(0)) : null;
	}
	public Object getListOne(String hql, Object[] params) {
		List l = getList(hql,params);
		return (l.size() > 0) ? (l.get(0)) : null;
	}
	
	public Criteria getCriteria(Class class1) {		
		Criteria criteria = this.getCurrentSession().createCriteria(class1);		
		return 	criteria;	
	}
	
	public List getList(Criteria criteria){
		
		return criteria == null? null:criteria.list();
	}
}