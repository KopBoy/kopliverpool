package com.kopliverpool.framework.spring;

import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kopliverpool.framework.core.CoreConst;

/** 
 *
 * Description: Spring IOC Util
 *
 * @author KOP
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014年8月28日	 KOP          1.0       1.0 Version 
 * </pre>
 */
public class SpringUtil {
	
	private static ApplicationContext ctx;
	static String webRootAbsPath;
	
	public static void setWebRootAbsPath(String webRootAbsPath) {
		SpringUtil.webRootAbsPath = webRootAbsPath;
	}
	/**
	 * 构造方法
	 */
	private SpringUtil(){}
	/**
	 * 由web容器初始化（用于服务环境）
	 * @param sc servlet上下文
	 */
	public static void init(ServletContext sc){
		ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
	}
	/**
	 * 由类路径下的配置文件初始化（用于测试环境）
	 * @param ctxFilePath 配置文件路径
	 */
	public static void init(String... ctxFilePath){
		ctx = new ClassPathXmlApplicationContext(ctxFilePath);
	}
	/**
	 * 直接传入applicationContext
	 * @param actx 应用程序上下文对象
	 */
	public static void init(ApplicationContext actx){
		ctx = actx;
	}
	
	/**
	 * 获取bean
	 * @param id id标识符
	 * @return bean对象
	 */
	public static Object getBean(String id){
		return ctx.getBean(id);
	}
	
	/**
	 * 
	 * Description: 按类型获取bean
	 *
	 * @param 
	 * @return T
	 * @throws 
	 * @Author fei
	 * Create Date: 2013-9-6 下午2:15:18
	 */
	public static <T> T getBean(Class<T> clazz){
		return ctx.getBean(clazz);
	}
	
	/**
	 * 
	 * Description: 按类型及ID获取bean
	 *
	 * @param 
	 * @return T
	 * @throws 
	 * @Author fei
	 * Create Date: 2013-9-6 下午2:15:29
	 */
	public static <T> T getBean(String id, Class<T> clazz){
		return ctx.getBean(id, clazz);
	}
	
	public static String getWebRootAbsPath(){
		return webRootAbsPath;
	}
	
	/**
	 * 
	 * Description: 取配置文件中的参数
	 *
	 * @param key beanId
	 * @return Object
	 * @throws 
	 * @Author liufei
	 * Create Date: 2011-11-8 下午04:57:54
	 */
	@SuppressWarnings("rawtypes")
	public static String getConfigParam(String key){
		Map configParamMap = (Map) SpringUtil.getBean(CoreConst.KEY_CONFIG_PARAM_MAP);
		return (String)configParamMap.get(key);
	}

}
