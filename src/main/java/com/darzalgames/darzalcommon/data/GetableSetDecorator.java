package com.darzalgames.darzalcommon.data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A decorator whick can wrap around a set and map implementation to produce a GetableDecorator
 * @param <E> the type of elements maintained by this set
 */
public class GetableSetDecorator<E> extends AbstractSet<E> implements GetableSet<E> {

	private final Set<E> innerSet;
	private final Map<E, E> innerMap;

	private GetableSetDecorator(Set<E> innerSet, Map<E, E> innerMap) {
		this.innerSet = innerSet;
		this.innerMap = innerMap;
	}

	/**
	 * Builds a GetableSet using a HashSet and HashMap internally.
	 * @param <E> the type of elements maintained by this set
	 * @return A GetableSet backed by hashing, which follows the regular Set contract
	 */
	public static final <E> GetableSetDecorator<E> ofHashingType() {
		return new GetableSetDecorator<>(new HashSet<>(), new HashMap<>());
	}

	/**
	 * Builds a GetableSet using a TypeNameMaps internally.
	 * @param <E> the type of elements maintained by this set
	 * @return A GetableSet backed by hashing, which follows the TypeNameMap contract
	 */
	public static final <E> GetableSetDecorator<E> ofTypeNameType() {
		return new GetableSetDecorator<>(new TypeNameSet<>(), new TypeNameMap<>());
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
		c.forEach(e -> innerMap.put(e, e));
		return innerSet.addAll(c);
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
