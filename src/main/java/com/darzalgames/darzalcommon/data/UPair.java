package com.darzalgames.darzalcommon.data;

import java.util.*;

/**
 * A class representing an unordered pair of values, of the same type<br>
 * This means that the UPairs {a, b} and {b, a} are equal, since their order does not matter
 * @param <E> The type of the unordered pair's elements
 */
public class UPair<E> {

	private final Set<E> elements;

	/**
	 * Creates an unordered pair of two elements.<br>
	 * The order the elements are passed into the constructor is irrelevant
	 * @param value      an element of the unordered pair
	 * @param otherValue the other element of the unordered pair
	 */
	public UPair(E value, E otherValue) {
		elements = new HashSet<>();
		elements.add(value);
		elements.add(otherValue);
	}

	/**
	 * Get the complementary value of this unordered pair
	 * @param value the value in the pair to match
	 * @return the other value of this pair
	 * @throws IllegalArgumentException if the value passed in isn't part of the pair
	 */
	public E getOther(E value) {
		if (!elements.contains(value)) {
			throw new IllegalArgumentException("value must be in the pair to get it's complement: " + value + " is not in " + this);
		}
		if (elements.size() == 1) {
			return elements.iterator().next();
		} else {
			Set<E> copy = values();
			copy.remove(value);
			return copy.iterator().next();
		}
	}

	/**
	 * Get this unordered pair as a Set
	 * @return a set of the values
	 */
	public Set<E> values() {
		return new HashSet<>(elements);
	}

	/**
	 * Creates an ordered pair equivalent to this pair<br>
	 * There is no guarantee on the ordering of the resulting pair
	 * @return An ordered pair, with the elements being the same as this unordered pair.
	 */
	public Pair<E> toPair() {
		List<E> values = new ArrayList<>(elements);
		if (elements.size() == 1) {
			values.add(values.getFirst());
		}
		return new Pair<>(values.get(0), values.get(1));
	}

	/**
	 * Creates a tuple equivalent to this unordered pair.
	 * There is no guarantee on the ordering of the resulting tuple
	 * @return A tuple with the elements being the same as this unordered pair
	 */
	public Tuple<E, E> toTuple() {
		Pair<E> pair = toPair();
		return new Tuple<>(pair.e1(), pair.e2());
	}

	@Override
	public int hashCode() {
		return Objects.hash(elements);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UPair<?> other = (UPair<?>) obj;
		return Objects.equals(elements, other.elements);
	}

	@Override
	public String toString() {
		if (elements.size() == 1) {
			E value = elements.iterator().next();
			return "UPair[" + value + ", " + value + "]";
		} else {
			return "UPair" + elements;
		}
	}

}
