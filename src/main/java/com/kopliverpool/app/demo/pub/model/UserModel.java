package com.kopliverpool.app.demo.pub.model;

import com.kopliverpool.framework.core.model.BaseModel;

/** 
 *
 * Description: User模型
 *
 * @author KOP
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014年11月2日	 KOP          1.0       1.0 Version 
 * </pre>
 */
public class UserModel extends BaseModel{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String username;
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
