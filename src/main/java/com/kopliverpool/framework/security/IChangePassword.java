package com.kopliverpool.framework.security;

import org.springframework.security.core.userdetails.UserDetailsService;

/** 
 *
 * Description: 基于Spring Security，继承UserDetailsService接口，提供修改密码的功能
 *
 * @author KOP
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014年11月10日	 KOP          1.0       1.0 Version 
 * </pre>
 */
public interface IChangePassword extends UserDetailsService{

	public void changePassword(String username, String password);
	
}
