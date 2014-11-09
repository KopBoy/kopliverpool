package com.kopliverpool.framework.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.memory.InMemoryDaoImpl;

/** 
 *
 * Description: 继承InMemoryDaoImpl，扩展IChangePassword接口，为用户提供登录和密码修改的功能
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
@SuppressWarnings("deprecation")
public class InMemoryChangePasswordDaoImpl extends InMemoryDaoImpl implements IChangePassword{
	
	private final Log log = LogFactory.getLog(getClass());

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		log.info("load User By Username:" + username);
		return super.loadUserByUsername(username);
	}

	/* (non-Javadoc)
	 * @see com.kopliverpool.framework.security.IChangePassword#changePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public void changePassword(String username, String password) {
		User userDetails = (User)getUserMap().getUser(username);
		User newUserDetails = new User(userDetails.getUsername(), password, userDetails.isEnabled(), 
				userDetails.isAccountNonExpired(), userDetails.isCredentialsNonExpired(), userDetails.isAccountNonExpired(), 
				userDetails.getAuthorities()) ;
		getUserMap().addUser(newUserDetails);
	}

}
