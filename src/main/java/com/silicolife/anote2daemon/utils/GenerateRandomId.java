package com.silicolife.anote2daemon.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class GenerateRandomId {

	public static Long generateID() {

		try {
			SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
			Long randomNum = new Long(prng.nextLong());
			return Math.abs(randomNum);
		} catch (NoSuchAlgorithmException ex) {
			System.err.println(ex);
		}
		return null;
	}

	public static void main(String args[]) {
		System.out.println("Start.............");

		Set<Long> set = new HashSet<Long>();
		for (long x = 0; x < 1999999999; x++) {
			Long id = GenerateRandomId.generateID();

			if (set.contains(id))
				System.out.println(id);
			else
				set.add(id);

		}

		System.out.println("Finished");
	}

}
