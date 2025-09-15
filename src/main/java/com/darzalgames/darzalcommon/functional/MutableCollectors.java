package com.darzalgames.darzalcommon.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Similar to Java's {@link Collectors} class, members guarantee to collect streams into modifiable collections
 */
public class MutableCollectors {

	MutableCollectors() {}

	/**
	 * Returns a Collector that accumulates the input elements into a new {@code List}. <br>
	 * The {@code List} is guaranteed to be mutable.
	 * @param <T> The type of the input elements
	 * @return a {@code Collector} which collects all the input elements into a mutable {@code List}, in encounter order
	 */
	public static <T> Collector<T, ?, List<T>> toList(){
		return Collectors.toCollection(ArrayList::new);
	}

}
