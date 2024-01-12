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
	 * @return An DoubleConsumer which has no effect when accepting.
	 */
	public static DoubleConsumer nullDoubleConsumer() {
		return e -> {};
	}
	
	/**
	 * for with new ja@return ASeeing LongConsumer which has no effect when accepting.
	 */
	public static LongConsumer nullLongConsumer() {
		return e -> {};
	}
}
