package com.silicolife.anote2daemon.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Identify the permissions of the anote2daemon
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@Component
public class Permissions {

	private static final List<String> fullGrant = new ArrayList<String>(Arrays.asList("owner", "read", "read_write"));
	private static final List<String> writeGrant = new ArrayList<String>(Arrays.asList("owner", "read_write"));

	public static List<String> getFullgrant() {
		return fullGrant;
	}

	public static List<String> getWritegrant() {
		return writeGrant;
	}

}
