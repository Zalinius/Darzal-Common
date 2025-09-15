package com.darzalgames.darzalcommon.functional;

import java.util.stream.IntStream;

/**
 * Functional convenience stuff
 */
public class Do {

	private Do() {}

	/**
	 * Executes a Runnable x times.
	 * It's a cleaner for loop with a counter, where the counter doesn't matter
	 * @param x      The number of times to run the runnable
	 * @param action The runnable to execute
	 */
	public static void xTimes(int x, Runnable action) {
		IntStream.range(0, x).forEach(i -> action.run());
	}
}
