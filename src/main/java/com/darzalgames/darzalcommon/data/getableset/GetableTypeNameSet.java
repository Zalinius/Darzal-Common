package com.darzalgames.darzalcommon.data.getableset;

import com.darzalgames.darzalcommon.data.TypeNameMap;
import com.darzalgames.darzalcommon.data.TypeNameSet;

/**
 * A GetableSet that stores at most one object of a given class type
 * @param <E> the type of elements maintained by this set
 */
public class GetableTypeNameSet<E> extends AbstractGetableSetDecorator<E> {

	/**
	 * Constructs an empty GetableTypeNameSet
	 */
	public GetableTypeNameSet() {
		super(new TypeNameSet<>(), new TypeNameMap<>());
	}

}
