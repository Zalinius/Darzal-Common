package com.darzalgames.darzalcommon.functional;

import java.util.function.Consumer;

/**
 * A class for convenience consumers
 */
public class Consumers {
	private Consumers() {}
	
	/**
	 * @param <E> The Consumer's type
	 * @return A consumer which has no effect when accepting.
	 */
	public static <E> Consumer<E> nullConsumer(){
		return e -> {};
	}
}
