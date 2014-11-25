package com.kopliverpool.framework.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/** 
 *
 * Description: 简单实体类，用来测试hibernate
 *
 * @author KOP
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014年11月5日	 KOP          1.0       1.0 Version 
 * </pre>
 */
@Entity
@Table(name="TEST_SIMPLE")
public class SimpleModel extends BaseModel{

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5564886521174599720L;

}
