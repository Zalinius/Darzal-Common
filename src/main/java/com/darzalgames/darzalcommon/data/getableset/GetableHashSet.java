package com.darzalgames.darzalcommon.data.getableset;

import java.util.HashMap;
import java.util.HashSet;

/**
 * A GetableSet that uses hashing similarly to a HashSet
 * @param <E> the type of elements maintained by this set
 */
public class GetableHashSet<E> extends AbstractGetableSetDecorator<E> {

	/**
	 * Constructs an empty GetableHashSet
	 */
	public GetableHashSet() {
		super(new HashSet<>(), new HashMap<>());
	}

}
