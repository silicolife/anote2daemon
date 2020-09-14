package com.silicolife.anote2daemon.utils;

import com.silicolife.anote2daemon.security.GeneratePassword;

public class GeneratePasswordDBHash {

	public static void main(String[] args) throws Exception {
		if(args.length != 1) {
			String message = "The GeneratePasswordDBHash command must have <passwordToHash>, given:";
	        for (String s: args) 
	        	message = message + " " + s;
	        
	        throw new Exception(message);
		}
		
		String password = args[0];
		String genpass = GeneratePassword.generate(password);
		System.out.println("Please replace the generated hash to aut_users.au_password table in the line of the user to be modified:");
		System.out.println(genpass);
	}
	
}
