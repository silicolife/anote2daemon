package com.silicolife.anote2daemon.utils;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenerateRandomId {

	public static Long generateID() {

		long time = System.currentTimeMillis();
		long nanoTime = System.nanoTime();
		long freeMemory = Runtime.getRuntime().freeMemory();
		long addressHashCode;

		try {
			InetAddress inetAddress;
			inetAddress = InetAddress.getLocalHost();
			addressHashCode = inetAddress.getCanonicalHostName().hashCode() ^ inetAddress.getHostName().hashCode() ^ inetAddress.getHostAddress().hashCode();
		} catch (Exception err) {
			addressHashCode = GenerateRandomId.class.hashCode();
		}
		Random random1 = new Random(time);
		Random random2 = new Random(nanoTime);
		Random random3 = new Random(addressHashCode ^ freeMemory);

		return Math.abs(random1.nextLong() ^ random2.nextLong() ^ random3.nextLong());
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
