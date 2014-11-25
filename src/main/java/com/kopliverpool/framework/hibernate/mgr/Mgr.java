package com.kopliverpool.framework.hibernate.mgr;

import java.util.List;

import com.kopliverpool.framework.hibernate.model.BaseModel;

public interface Mgr<T extends BaseModel> {

	void save(T entity);
	
	void deteleById(String id);
	
	List<T> findAll();
	
	T findById(String id);
	
	List<T> findByField(String fieldName, String fieldValue);
	
}
