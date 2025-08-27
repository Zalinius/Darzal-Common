package com.darzalgames.darzalcommon.data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Creates convenient MODIFIABLE lists
 */
public class ListFactory {
	private ListFactory() {}

	/**
	 * Creates a modifiable list from a variable number of values
	 * @param <E> The collection type
	 * @param values A variable number of values to put into the list
	 * @return A modifiable list of the values, in order
	 */
	@SafeVarargs
	public static <E> List<E> of(E... values){
		List<E> list = new ArrayList<>();
		Collections.addAll(list, values);
		return list;
	}

	/**
	 * Flattens a list of lists into a single modifiable list
	 * @param <E> The collection's type
	 * @param listOfList a list of nested lists
	 * @return A flat modifiable list of the values, in order of the list of lists
	 */
	public static <E> List<E> flattenListOfLists(List<List<E>> listOfList) {
		return listOfList.stream()
				.flatMap(Collection::stream)
				.collect(Collectors.toCollection(ArrayList::new));
	}

}
