package com.kopliverpool.app.demo.put.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kopliverpool.framework.security.IChangePassword;
import com.kopliverpool.framework.springmvc.BaseController;

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
 * 2014年11月10日	 KOP          1.0       1.0 Version 
 * </pre>
 */
@Controller
public class AccountController extends BaseController{
	
//	@Autowired
//	private IChangePassword handler;
//
//	@RequestMapping(value = "/showChangePasswordPage.do", method = RequestMethod.GET)
//	public String showChangePasswordPage(){
//		return "changePassword";
//	}
//	
//	@RequestMapping(value = "/changePassword.do", method = RequestMethod.POST)
//	public String changePassword(@RequestParam("newPassword") String newPassword){
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = principal.toString();
//		if(principal instanceof UserDetails){
//			username = ((UserDetails)principal).getUsername();
//		}
//		handler.changePassword(username, newPassword);
//		SecurityContextHolder.clearContext();
//		return "";
//	}
	
}
