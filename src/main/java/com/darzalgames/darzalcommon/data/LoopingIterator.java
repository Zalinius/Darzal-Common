package com.darzalgames.darzalcommon.data;

import java.util.Iterator;

/**
 * An iterator that repeatedly loops through an Iterable
 * @param <E> The iterator parameter
 */
public class LoopingIterator<E> implements Iterator<E> {

	private final Iterable<E> baseIterable;
	private Iterator<E> currentIterator;

	/**
	 * Constructs a Looping iterator for the given iterable
	 * @param baseIterable The iterable which will be iterated repeatedly
	 */
	public LoopingIterator(Iterable<E> baseIterable) {
		this.baseIterable = baseIterable;
		currentIterator = baseIterable.iterator();
		if(!currentIterator.hasNext()) {
			throw new IllegalArgumentException("base iterable must contain at least one element: " + baseIterable.toString());
		}
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public E next() {
		if(!currentIterator.hasNext()) {
			currentIterator = baseIterable.iterator();
		}
		return currentIterator.next();
	}

}
