package com.silicolife.anote2daemon.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeneratePassword {

	public static String generate(String password) {
		BCryptPasswordEncoder pass = new BCryptPasswordEncoder(13);
		String a = pass.encode(password);
		return a;
	}

	public static void main(String[] args) {
		
		System.out.print(GeneratePassword.generate("admin"));

	}
}
