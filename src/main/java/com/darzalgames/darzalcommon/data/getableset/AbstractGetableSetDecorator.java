package com.darzalgames.darzalcommon.data.getableset;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A decorator which can wrap around a set and map implementation to produce a GetableDecorator
 * @param <E> the type of elements maintained by this set
 */
abstract class AbstractGetableSetDecorator<E> extends AbstractSet<E> implements GetableSet<E> {

	private final Set<E> innerSet;
	private final Map<E, E> innerMap;

	protected AbstractGetableSetDecorator(Set<E> innerSet, Map<E, E> innerMap) {
		this.innerSet = innerSet;
		this.innerMap = innerMap;
	}

	@Override
	public E get(E e) {
		return innerMap.get(e);
	}

	@Override
	public boolean add(E e) {
		if (contains(e)) {
			return false;
		} else {
			innerMap.put(e, e);
			innerSet.add(e);
			return true;
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean setChanged = false;
		for (Iterator<? extends E> it = c.iterator(); it.hasNext();) {
			E element = it.next();
			boolean changed = add(element);
			if (changed) {
				setChanged = true;
			}
		}
		return setChanged;
	}

	@Override
	public void clear() {
		innerMap.clear();
		innerSet.clear();
	}

	@Override
	public boolean contains(Object o) {
		return innerSet.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return innerSet.containsAll(c);
	}

	@Override
	public Iterator<E> iterator() {
		return innerSet.iterator();
	}

	@Override
	public boolean remove(Object o) {
		innerMap.remove(o);
		return innerSet.remove(o);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		Set<E> keysToRemove = innerMap.keySet().stream().filter(key -> !c.contains(key)).collect(Collectors.toSet());
		keysToRemove.forEach(innerMap::remove);
		return innerSet.retainAll(c);
	}

	@Override
	public int size() {
		return innerSet.size();
	}

}
