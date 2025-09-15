package com.darzalgames.darzalcommon.data;

/**
 * A class representing a pair of values, of the same type
 * @param <E> The type of the pairs elements
 * @param e1  the first element of the tuple
 * @param e2  the second element of the tuple
 */
public record Pair<E>(E e1, E e2) {

	/**
	 * Creates a tuple equivalent to this pair
	 * @return A tuple with the elements being the same as this pair
	 */
	public Tuple<E, E> toTuple() {
		return new Tuple<>(e1, e2);
	}
}