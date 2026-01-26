package com.darzalgames.darzalcommon.data;

import java.util.*;

/**
 * This class implements the Map Interface using typename equality in place of object-equality when comparing keys <br>
 * In other words, in a TypeNameMap, two keys k1 and k2 are considered equal if and only they have the same runtime class <br>
 * <b>This class is not a general-purpose Map implementation!
 * While this class implements the Map interface, it intentionally violates Map's general contract,
 * which mandates the use of the equals method when comparing objects.
 * This class is designed for use only in the rare cases wherein type-equality semantics are required. </b>
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class TypeNameMap<K, V> implements Map<K, V> {

	private final Map<Class<?>, V> innerMap;
	private final Map<Class<?>, K> keyMap;

	/**
	 * Constructs an empty TypeNameMap, backed by HashMaps
	 */
	public TypeNameMap() {
		innerMap = new HashMap<>();
		keyMap = new HashMap<>();
	}

	private Class<?> computeKey(Object key) {
		return key.getClass();
	}

	@Override
	public void clear() {
		innerMap.clear();
		keyMap.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		Class<?> typeKey = computeKey(key);
		return innerMap.containsKey(typeKey);
	}

	@Override
	public boolean containsValue(Object value) {
		return innerMap.containsValue(value);
	}

	@Override
	public V get(Object key) {
		Class<?> typeKey = computeKey(key);
		return innerMap.get(typeKey);
	}

	@Override
	public boolean isEmpty() {
		return innerMap.isEmpty();
	}

	@Override
	public V put(K key, V value) {
		Class<?> typeKey = computeKey(key);
		keyMap.put(typeKey, key);
		return innerMap.put(typeKey, value);
	}

	@Override
	public V remove(Object key) {
		Class<?> typeKey = computeKey(key);
		keyMap.remove(typeKey);
		return innerMap.remove(typeKey);
	}

	@Override
	public int size() {
		return innerMap.size();
	}

	@Override
	public Collection<V> values() {
		return innerMap.values();
	}

	@Override
	public String toString() {
		return innerMap.toString();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		Map<K, V> tempMap = new HashMap<>();
		innerMap.keySet().forEach(typeKey -> tempMap.put(keyMap.get(typeKey), innerMap.get(typeKey)));
		return tempMap.entrySet();
	}

	@Override
	public Set<K> keySet() {
		return new HashSet<>(keyMap.values());
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		m.entrySet().forEach(entry -> put(entry.getKey(), entry.getValue()));
	}

}
