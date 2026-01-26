package com.darzalgames.darzalcommon.data;

import java.util.*;

/**
 * This class implements the Set Interface using typename equality in place of object-equality when comparing elements <br>
 * In other words, in a TypeNameSet, two elements e1 and e2 are considered equal if and only they have the same runtime class <br>
 * <b>This class is not a general-purpose Set implementation!
 * While this class implements the Set interface, it intentionally violates Set's general contract,
 * which mandates the use of the equals method when comparing objects.
 * This class is designed for use only in the rare cases wherein type-equality semantics are required. </b>
 * @param <E> the type of elements maintained in this set
 */
public class TypeNameSet<E> extends AbstractSet<E> {

	private final Map<Class<?>, E> innerMap;

	/**
	 * Constructs an empty TypeNameSet
	 */
	public TypeNameSet() {
		innerMap = new HashMap<>();
	}

	@Override
	public boolean add(E e) {
		Class<?> key = e.getClass();
		if (innerMap.containsKey(key)) {
			return false;
		} else {
			innerMap.put(key, e);
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		Class<?> key = o.getClass();
		if (innerMap.containsKey(key)) {
			innerMap.remove(key);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(Object o) {
		return innerMap.containsKey(o.getClass());
	}

	@Override
	public Iterator<E> iterator() {
		return innerMap.values().iterator();
	}

	@Override
	public int size() {
		return innerMap.size();
	}

}
