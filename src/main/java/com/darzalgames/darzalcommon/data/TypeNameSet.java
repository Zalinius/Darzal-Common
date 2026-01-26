package com.darzalgames.darzalcommon.data;

import java.util.*;

public class TypeNameSet<E> extends AbstractSet<E> {

	private final Map<Class<?>, E> innerMap;

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
