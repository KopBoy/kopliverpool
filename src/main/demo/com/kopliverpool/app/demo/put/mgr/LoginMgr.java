package com.kopliverpool.app.demo.put.mgr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.kopliverpool.app.demo.put.model.UserModel;
import com.kopliverpool.framework.hibernate.mgr.BaseHibernateMgr;

@Component
public class LoginMgr extends BaseHibernateMgr<UserModel>{

	 /**
	 * Description: 根据用户名喝密码查询用户Model
	 *
	 * @param 
	 * @return UserModel
	 * @throws 
	 * @Author KOP
	 * Create Date: 2015年3月10日 下午11:13:52
	 */
	@SuppressWarnings("unchecked")
	public UserModel findUserByUsernameAndPwd(String username, String password){
		final String hql = "from UserModel where username=:username and password=:password";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("password", password);
		List<UserModel> userModels = query(hql, params, LockMode.NONE);
		if(userModels != null && userModels.size() == 1){
			return userModels.get(0);
		}
		return null;
	}
	
}
