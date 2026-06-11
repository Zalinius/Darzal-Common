package com.darzalgames.darzalcommon.data.getableset;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/**
 * A GetableSet that uses hashing similarly to a HashSet
 * @param <E> the type of elements maintained by this set
 */
public class GetableLinkedHashSet<E> extends AbstractGetableSetDecorator<E> {

	/**
	 * Constructs an empty GetableHashSet
	 */
	public GetableLinkedHashSet() {
		super(new LinkedHashSet<>(), new LinkedHashMap<>());
	}

}
