package com.kopliverpool.framework.hibernate.mgr;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;

import com.kopliverpool.framework.hibernate.util.HibernateUtil;

/** 
 *
 * Description: TODO 注释
 *
 * @author KOP
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014年11月13日	 KOP          1.0       1.0 Version 
 * </pre>
 */
public class AbstractHibernateMgr {
	protected Logger log = Logger.getLogger(this.getClass());
	protected HibernateTemplate hibernateTemplate;
	protected static final LockMode LOCK_MODE_DEFAULT = LockMode.NONE;// 默认锁定模式
	protected static final LockMode LOCK_MODE_OPT = LockMode.OPTIMISTIC;// 乐观锁
	protected static final LockMode LOCK_MODE_PES = LockMode.PESSIMISTIC_WRITE;// 悲观锁－写


	/* ======================= helpers ====================== */
	/**
	 * 创建参数Map
	 * 
	 * @param names
	 *            名称
	 * @param values
	 *            值
	 * @return 参数Map
	 */
	protected Map<String, Object> createParamMap(String[] names, Object[] values) {
		return HibernateUtil.createParamMap(names, values);
	}

	/**
	 * 启用缓存
	 */
	public void enableCache() {
		if (!getHibernateTemplate().isCacheQueries()) {
			getHibernateTemplate().setCacheQueries(true);
		}
	}

	/**
	 * 不启用缓存
	 */
	public void disableCache() {
		if (getHibernateTemplate().isCacheQueries()) {
			getHibernateTemplate().setCacheQueries(false);
		}
	}

	protected HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Autowired
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
		enableCache();// 默认启用缓存
	}
}
