package com.silicolife.anote2daemon.utils;

import org.hibernate.Hibernate;

/**
 * Hibernate utils
 * 
 * @author Joel Azevedo costa
 * @year 2015
 *
 */
public class HibernateUtils {
	/**
	 * Force the inicialization of a object
	 * 
	 * @param object
	 */
	public static void forceInicialization(Object object) {
		if (!Hibernate.isInitialized(object)) {
			Hibernate.initialize(object);
		}
	}
}
