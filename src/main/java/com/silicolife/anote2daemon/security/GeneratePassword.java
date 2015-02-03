package com.silicolife.anote2daemon.security;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class GeneratePassword {

	public static String generate(String password, Object salt) {
		ShaPasswordEncoder pass = new ShaPasswordEncoder(256);
		String a = pass.encodePassword(password, salt);
		return a;
	}
	
	public static void main(String[] args) {
		 
		System.out.print(GeneratePassword.generate("teste", null));
	 
	  }
}
