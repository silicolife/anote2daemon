package com.silicolife.anote2daemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@ImportResource({"classpath*:application-context.xml"})

public class Anote2DaemonSpringBoot {
	
	public static void main(String[] args) {
		SpringApplication.run(Anote2DaemonSpringBoot.class, args);
//		ApplicationContext ctx = SpringApplication.run(Anote2DaemonSpringBoot.class, args);
//        String[] beanNames = ctx.getBeanDefinitionNames();
//        
//        Arrays.sort(beanNames);
// 
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
	}

}
