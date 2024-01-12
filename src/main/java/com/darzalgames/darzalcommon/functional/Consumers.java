package com.darzalgames.darzalcommon.functional;

import java.util.function.*;

/**
 * A class for convenience consumers
 */
public class Consumers {
	private Consumers() {}
	
	/**
	 * @param <E> The Consumer's type
	 * @return A consumer which has no effect when accepting.
	 */
	public static <E> Consumer<E> nullConsumer() {
		return e -> {};
	}
	
	/**
	 * @return An IntConsumer which has no effect when accepting.
	 */
	public static IntConsumer nullIntegerConsumer() {
		return e -> {};
	}
	
	/**
	 * @return A DoubleConsumer which has no effect when accepting.
	 */
	public static DoubleConsumer nullDoubleConsumer() {
		return e -> {};
	}
	
	/**
	 * @return A LongConsumer which has no effect when accepting.
	 */
	public static LongConsumer nullLongConsumer() {
		return e -> {};
	}
}
