package com.kopliverpool.framework.hibernate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.GenericGenerator;

import com.kopliverpool.framework.core.CoreConst;

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
 * 2014年11月4日	 KOP          1.0       1.0 Version 
 * </pre>
 */
public class BaseModel implements Serializable, Comparable<BaseModel>{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String Id;

	@Override
	public int compareTo(BaseModel o) {
		return 0;
	}

}
