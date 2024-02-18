package com.darzalgames.darzalcommon.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Creates convenient MODIFIABLE lists
 * Is GWT compatible
 */
public class ListFactory {
	private ListFactory() {}

	/**
	 * @param <E> The collection type
	 * @param values A variable number of values to put into the list
	 * @return A modifiable ArrayList of the values, in order
	 */
	@SafeVarargs
	public static <E> List<E> of(E... values){
		List<E> list = new ArrayList<>();
		Collections.addAll(list, values);
		return list;
	}
}
