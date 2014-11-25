package com.kopliverpool.framework.hibernate.mgr;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.kopliverpool.framework.hibernate.model.BaseModel;
import com.kopliverpool.framework.hibernate.util.HibernateUtil;

/** 
 *
 * Description: 
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
public class BaseHibernateMgr<T extends BaseModel> extends AbstractHibernateMgr implements Mgr<T>{
	
	protected Class<T> entityClass;//对应实体类的类型
	
	/**
	 * 获取对应的实体类类名
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseHibernateMgr() {//获取对应的实体类类名
		super();
		this.entityClass = null;
		Class c = getClass();
		Type t = c.getGenericSuperclass(); 
		if (t instanceof ParameterizedType) { 
			Type[] p = ((ParameterizedType) t).getActualTypeArguments(); 
			this.entityClass = (Class) p[0]; 
		}
	}

	@Override
	public void save(T entity) {
		getHibernateTemplate().save(entity);
		getHibernateTemplate().flush();
	}

	@Override
	public void deteleById(String id) {
		//TODO detelt by id
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		final String queryHql = "from " +  entityClass.getName() + " order by sortIdx"; 
		return (List<T>)getHibernateTemplate().find(queryHql);
	}

	@Override
	public T findById(String id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	/* (non-Javadoc)
	 * @see com.kopliverpool.framework.hibernate.mgr.Mgr#findByField(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByField(String fieldName, String fieldValue) {
		final Map<String, Object> params = new HashMap<String, Object>();
		params.put(fieldName, fieldValue);
		String hql = "from " +  entityClass.getName() + " where 1=1 ";
		hql += HibernateUtil.putParamsEqual(params, new String[]{fieldName}, new Object[]{fieldValue});
		hql += HibernateUtil.orderBy("sortIndex", false, null);
		return query(hql, params, LOCK_MODE_DEFAULT);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public List query(final String hql, final Map<String, Object> params, final LockMode lock){
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public List doInHibernate(final Session session) throws HibernateException,
					SQLException {
				final Query query = session.createQuery(hql);
				query.setLockOptions(new LockOptions(lock));
				if(null!=params){
					HibernateUtil.setParams4Query(params, query);
				}
				return query.list();
			}
		});
	}

}
