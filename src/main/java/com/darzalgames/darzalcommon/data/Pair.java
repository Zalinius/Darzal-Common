package com.darzalgames.darzalcommon.data;

/**
 * A class representing an ordered pair of values, of the same type
 * @param <E> The type of the pairs elements
 * @param e1  the first element of the tuple
 * @param e2  the second element of the tuple
 */
public record Pair<E>(E e1, E e2) {

	/**
	 * Creates a tuple equivalent to this pair
	 * @return A tuple with the elements and ordering being the same as this pair
	 */
	public Tuple<E, E> toTuple() {
		return new Tuple<>(e1, e2);
	}

	/**
	 * Creates an unordered pair with the elements of this pair
	 * @return An unordered pair with the elements being the same as this pair
	 */
	public UPair<E> toUPair() {
		return new UPair<>(e1, e2);
	}

	/**
	 * Creates a new Pair with the opposite ordering of this pair
	 * @return a reversed pair
	 */
	public Pair<E> reverse() {
		return new Pair<>(e2, e1);
	}

}