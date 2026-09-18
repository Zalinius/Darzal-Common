package com.darzalgames.darzalcommon.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * Functional style loop programming with factory methods, for simplicity and avoiding loop index errors
 */
public class Make {

	private Make() {}

	/**
	 * Get from a Supplier x times and return the result in a list.
	 * @param <E>           The type of the returned list
	 * @param x             The number of times to get the Supplier
	 * @param factoryMethod The Supplier to get from
	 * @return A modifable List containing the x invocations of the factory method
	 */
	public static <E> List<E> xTimes(int x, Supplier<E> factoryMethod) {
		List<E> list = new ArrayList<>();
		Do.xTimes(x, () -> list.add(factoryMethod.get()));
		return list;
	}

	/**
	 * Apply from an index to a function x times and return the result in a list.
	 * It's a cleaner for loop with a counter, where the index is available
	 * As with normal for loops, the index will run from 0 to x-1
	 * @param <E>           The type of the returned list
	 * @param x             The number of times to run the function
	 * @param factoryMethod The function to execute with the current loop index
	 * @return A modifable List containing the x invocations of the factory method with index
	 */
	public static <E> List<E> xTimesWithI(int x, IntFunction<E> factoryMethod) {
		List<E> list = new ArrayList<>();
		Do.xTimesWithI(x, i -> list.add(factoryMethod.apply(i)));
		return list;
	}

}
