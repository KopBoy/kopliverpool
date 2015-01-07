package com.kopliverpool.framework.spring;

import java.io.FileNotFoundException;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

/** 
 *
 * Description: 继承SpringJUnit4ClassRunner，启动时默认加载classpath路径下的log4j-config.xml
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
public class JUnit4ClassRunner extends SpringJUnit4ClassRunner  {
	
	
	public JUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
		try {  
            Log4jConfigurer.initLogging("classpath:log4j-config.xml");
        } catch (FileNotFoundException ex) {  
            System.err.println("Cannot Initialize log4j");  
        }
	}

}
