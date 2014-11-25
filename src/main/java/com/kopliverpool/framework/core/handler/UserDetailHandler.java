package com.kopliverpool.framework.core.handler;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.kopliverpool.framework.core.mgr.UserDetailMgr;
import com.kopliverpool.framework.core.model.UserModel;

public class UserDetailHandler implements UserDetailsService {
	
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserDetailMgr userDetailMgr;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		log.debug("用户名【" + username + "】");
		try{
			List<UserModel> userModels = userDetailMgr.findByField("username", username);
			if(userModels == null || userModels.size() != 1){
				log.info("根据用户名【" + username + "】查询用户失败");
				return null;
			}else {
				UserModel userModel = userModels.get(0);
				List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(userModel.getAuthorities());
				log.info("根据用户名【" + username + "】查询用户成功，角色【" + authorities.toString() + "】");
				return new User(username, userModel.getPassword(), authorities);
			}
		}catch(Exception e){
			log.error("根据用户名【" + username + "】查询用户失败,系统异常", e);
			return null;
		}
	}

}
