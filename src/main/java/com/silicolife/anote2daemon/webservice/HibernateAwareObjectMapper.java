package com.silicolife.anote2daemon.webservice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class HibernateAwareObjectMapper extends ObjectMapper {
	private static final long serialVersionUID = 1L;

	public HibernateAwareObjectMapper() {
		super();
		registerModule(new Hibernate4Module());
	}
}