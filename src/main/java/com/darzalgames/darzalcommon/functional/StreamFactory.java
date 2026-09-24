package com.darzalgames.darzalcommon.functional;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A factory method class for creating Streams
 */
public class StreamFactory {

	private StreamFactory() {}

	/**
	 * Creates a Stream from an iterator
	 * @param <E>      The type returned by the iterator and resulting Stream
	 * @param iterator an Iterator
	 * @return A Stream of the elements returned by the Iterator
	 */
	public static <E> Stream<E> of(Iterator<E> iterator) {
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED),
				false
		);
	}

}
