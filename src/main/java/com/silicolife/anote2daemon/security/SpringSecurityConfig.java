package com.silicolife.anote2daemon.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:spring-security.xml" })
//@ComponentScan("com.silicolife.anote2daemon.security")
public class SpringSecurityConfig {

	public SpringSecurityConfig() {
	}
}
