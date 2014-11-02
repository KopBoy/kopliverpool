package com.kopliverpool.framework.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/** 
 *
 * Description: 通过listener初始化SpringUtil
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
public class InitSpringUtilListener implements ServletContextListener{
	
private Logger log = Logger.getLogger(InitSpringUtilListener.class);
	
	public void contextDestroyed(ServletContextEvent evt) {}

	public void contextInitialized(ServletContextEvent evt) {
		log.info("开始初始化SpringUtil...");
		ServletContext ctx = evt.getServletContext();
		SpringUtil.init(ctx);
		String webRootAbsPath = evt.getServletContext().getRealPath("/");
		log.info("web root abs path="+webRootAbsPath);
		SpringUtil.webRootAbsPath = webRootAbsPath;
		log.info("SpringUtil初始化完成.");
	}

}
