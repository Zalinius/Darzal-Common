package com.darzalgames.darzalcommon.functional;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Similar to Java's {@link Collectors} class, members guarantee to collect streams into modifiable collections
 */
public class MutableCollectors {

	private MutableCollectors() {}

	/**
	 * Returns a {@link Collector} that accumulates the input elements into a new {@link List}. <br>
	 * The {@link List} is guaranteed to be mutable.
	 * @param <T> The type of the input elements
	 * @return a {@link Collector} which collects all the input elements into a mutable {@link List}, in encounter order
	 */
	public static <T> Collector<T, ?, List<T>> toList() {
		return Collectors.toCollection(ArrayList::new);
	}

	/**
	 * Returns a Collector that accumulates the input elements into a new {@link Set}. <br>
	 * The {@link Set} is guaranteed to be mutable.
	 * @param <T> The type of the input elements
	 * @return a {@link Collector} which collects all the input elements into a mutable {@link Set}
	 */
	public static <T> Collector<T, ?, Set<T>> toSet() {
		return Collectors.toCollection(HashSet::new);
	}

	/**
	 * Returns a {@link Collector} that accumulates the input elements into a new {@link SortedSet}. <br>
	 * The {@link SortedSet} is guaranteed to be mutable.
	 * @param <T> The type of the input elements
	 * @return a {@link Collector} which collects all the input elements into a mutable {@link SortedSet}
	 */
	public static <T> Collector<T, ?, SortedSet<T>> toSortedSet() {
		return Collectors.toCollection(TreeSet::new);
	}

	/**
	 * Returns a {@link Collector} that accumulates the input elements into a new {@link SortedSet}. <br>
	 * The {@link SortedSet} is guaranteed to be mutable.
	 * @param <T>        The type of the input elements
	 * @param comparator A {@link Comparator} for ordering the elements
	 * @return a {@link Collector} which collects all the input elements into a mutable {@link SortedSet}
	 */
	public static <T> Collector<T, ?, SortedSet<T>> toSortedSet(Comparator<T> comparator) {
		return Collectors.toCollection(() -> new TreeSet<>(comparator));
	}

}
