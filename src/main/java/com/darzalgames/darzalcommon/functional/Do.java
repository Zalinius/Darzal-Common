package com.darzalgames.darzalcommon.functional;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * Functional style loop programming, for simplicity and avoiding loop index errors
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

	/**
	 * Executes an index-fed Consumer x times.
	 * It's a cleaner for loop with a counter, where the index is available
	 * As with normal for loops, the index will run from 0 to x-1
	 * @param x      The number of times to run the consumer
	 * @param action The consumer to execute with the current loop index
	 */
	public static void xTimesWithI(int x, IntConsumer action) {
		IntStream.range(0, x).forEach(action::accept);
	}

}
