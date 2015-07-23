package org.dumpling.commons;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 能从spring的ApplicationContext中获取Bean实例
 * @author rabbit
 * @date  2013-8-11
 *
 */
public abstract class AbstractBeanFactory implements ApplicationContextAware {

	private static ApplicationContext cxt = null;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		cxt = applicationContext;
	}

	public static ApplicationContext getApplicationContext(){
		return cxt;
	}
}
