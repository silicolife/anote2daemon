package com.silicolife.anote2daemon.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Represent the application context of anote2daemon
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		ctx = appContext;

	}
	/**
	 * get the application context
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}
}
