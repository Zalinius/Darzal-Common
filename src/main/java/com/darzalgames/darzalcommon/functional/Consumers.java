package com.darzalgames.darzalcommon.functional;

import java.util.function.*;

/**
 * A class for convenience consumers
 */
public class Consumers {
	private Consumers() {}

	/**
	 * Creates a generic consumer with no effect
	 * @param <E> The Consumer's type
	 * @return A consumer which has no effect when accepting.
	 */
	public static <E> Consumer<E> nullConsumer() {
		return e -> {};
	}

	/**
	 * Creates an integer consumer with no effect
	 * @return An IntConsumer which has no effect when accepting.
	 */
	public static IntConsumer nullIntegerConsumer() {
		return e -> {};
	}

	/**
	 * Creates an double consumer with no effect
	 * @return A DoubleConsumer which has no effect when accepting.
	 */
	public static DoubleConsumer nullDoubleConsumer() {
		return e -> {};
	}

	/**
	 * Creates a long consumer with no effect
	 * @return A LongConsumer which has no effect when accepting.
	 */
	public static LongConsumer nullLongConsumer() {
		return e -> {};
	}
}
