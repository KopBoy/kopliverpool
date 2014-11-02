package com.kopliverpool.app.demo.pub.action;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kopliverpool.app.demo.pub.model.UserModel;
import com.kopliverpool.framework.springmvc.BaseController;

/** 
*
* Description: 登录Controller
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
@Controller
@RequestMapping(value = "/app/demo/pub")
public class LoginController extends BaseController{

	/**
	 * Description: RequestParam注解的使用方法，ModelMap的使用方法（相当于session）
	 *
	 * @param 
	 * @return String
	 * @throws 
	 * @Author KOP
	 * Create Date: 2014年11月2日 上午8:39:34
	 */
	@RequestMapping(value = "/loginByModelMap.do", method = RequestMethod.POST)
	public String loginByModelMap(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap map){
		System.out.println("method: loginByModelMap");
		System.out.println("username:" + username + "   password:" + password);
		log.info("username:" + username + "   password:" + password);
		
		UserModel user = new UserModel();
		user.setId(UUID.randomUUID().toString());
		user.setUsername(username);
		user.setPassword(password);
		map.put("user", user);
		return "index";
	}
	
	/**
	 * Description: 参数与Model绑定，自动转换 的使用方法，Form表单提交
	 *
	 * @param 
	 * @return ModelAndView
	 * @throws 
	 * @Author KOP
	 * Create Date: 2014年11月2日 上午8:39:34
	 */
	@RequestMapping(value = "/loginByForm.do", method = RequestMethod.POST)
	public ModelAndView loginByForm(UserModel user){
		System.out.println("method: loginByForm");
		System.out.println("username:" + user.getUsername() + "   password:" + user.getPassword());
		log.info("username:" + user.getUsername() + "   password:" + user.getPassword());
		user.setId(UUID.randomUUID().toString());
		
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addObject("user", user);
		return model;
	}
	
	/**
	 * Description: 参数与Model绑定，自动转换 的使用方法，Ajax表单提交（Json）
	 *
	 * @param 
	 * @return ModelAndView
	 * @throws 
	 * @Author KOP
	 * Create Date: 2014年11月2日 上午8:39:34
	 */
	@RequestMapping(value = "/loginByJson.do", method = RequestMethod.POST)
	public ModelAndView loginByJson(@RequestBody UserModel userModel){
		System.out.println("method: loginByJson");
		System.out.println("username:" + userModel.getUsername() + "   password:" + userModel.getPassword());
		log.info("username:" + userModel.getUsername() + "   password:" + userModel.getPassword());
		userModel.setId(UUID.randomUUID().toString());
		
		return responseByJson("index", true, "登录成功", userModel);
	}
	
	private ModelAndView responseByJson(String viewManme, boolean code, String msg, Object data){
		ModelAndView model = new ModelAndView();
		model.setViewName(viewManme);
		model.addObject("code", code);
		model.addObject("msg", msg);
		model.addObject("data", data);
		return model;
	}
	
}
