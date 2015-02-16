package com.silicolife.anote2daemon.utils;

import org.hibernate.Hibernate;

public class HibernateUtils {
	public static void forceInicialization(Object object) {
		if (!Hibernate.isInitialized(object)) {
			Hibernate.initialize(object);
			
		}
	}
}
