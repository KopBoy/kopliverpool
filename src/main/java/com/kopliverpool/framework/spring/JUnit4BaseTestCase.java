package com.kopliverpool.framework.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

/** 
 *
 * Description: JUnit4测试基类，启动时加载Spring配置文件和log4j配置，并初始化SpringUtil工具类
 *
 * @author KOP
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2015年1月7日	 KOP          1.0       1.0 Version 
 * </pre>
 */
@RunWith(JUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class })
@ContextConfiguration(locations = "classpath:system-context.xml")
public class JUnit4BaseTestCase implements ApplicationContextAware{

	protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * The {@link ApplicationContext} that was injected into this test instance
	 * via {@link #setApplicationContext(ApplicationContext)}.
	 */
	protected ApplicationContext applicationContext;

	/**
	 * Set the {@link ApplicationContext} to be used by this test instance,
	 * provided via {@link ApplicationContextAware} semantics.
	 */
	public final void setApplicationContext(final ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Before
	public void init(){
		log.info("开始初始化SpringUitl。。。");
		try{
			SpringUtil.init(applicationContext);
			log.info("初始化SpringUtil成功!");
		}catch(Exception e){
			log.error("初始化SpringUtil失败", e);
		}
	}
	
}
