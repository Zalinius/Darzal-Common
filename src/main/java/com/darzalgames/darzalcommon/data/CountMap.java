package com.darzalgames.darzalcommon.data;

import java.util.*;

/**
 * A convenience map whose value type is always Integers. Useful for keeping counts of stuff.
 * Conveniently, the values are always returned as integer primitives, and cannot return null, nor can they throw Exceptions
 * All keys are valid (including null), and counts without a key can be gotten and modified safely
 * @param <K> The key type for the map. It should implement equals and hashcode like keys used in other maps. Null keys are valid
 */
public class CountMap<K> implements Collection<K>{

	private final Map<K, Integer> data;

	private static final int DEFAULT = 0;

	/**
	 * Creates a blank CountMap
	 */
	public CountMap() {
		data = new HashMap<>();
	}

	/**
	 * Creates a CountMap, initialized with all the values of the provided collection
	 * @param collection An existing non null collection
	 */
	public CountMap(Collection<K> collection) {
		this();
		addAll(collection);
	}

	/**
	 * Get the count associated with a key
	 * @param key The key associated with the count
	 * @return The count of the key, or 0 if the key is not present
	 */
	public int get(Object key) {
		return data.getOrDefault(key, DEFAULT);
	}

	/**
	 * Increments the count associated with a key
	 * @param key The key to increment
	 */
	@Override
	public boolean add(K key) {
		data.put(key, get(key)+1);
		return true;
	}

	/**
	 * Decrements the count associated with a key
	 * @param key The key to decrement
	 */
	@Override
	public boolean remove(Object key) {
		if(data.containsKey(key)) {
		}
		K castKey = (K) key;
		data.put(castKey, get(key)-1);
		return true;
	}

	/**
	 * Resets the count associated with a key to 0
	 * @param key The key to reset
	 */
	public void reset(K key) {
		data.put(key, DEFAULT);
	}

	/**
	 * Resets the count associated with all keys to 0
	 */
	@Override
	public void clear() {
		data.clear();
	}

	/**
	 * Increases the count associated with a key
	 * @param key The key to increase
	 * @param amount The amount to increase by
	 */
	public void addMany(K key, int amount) {
		data.put(key, get(key)+amount);
	}

	/**
	 * Decreases the count associated with a key
	 * @param key The key to decrease
	 * @param amount The amount to decrease by
	 */
	public void removeMany(K key, int amount) {
		data.put(key, get(key)-amount);
	}

	/**
	 * @return A set of all the keys in use in the map
	 */
	public Set<K> keySet(){
		return data.keySet();
	}

	@Override
	public Iterator<K> iterator() {
		return data.keySet().iterator();
	}

	/**
	 * @return A collection of all the tracked counts, in no particular order
	 */
	public Collection<Integer> values() {
		return data.values();
	}

	@Override
	public String toString() {
		return data.toString();
	}

	@Override
	public boolean addAll(Collection<? extends K> c) {
		c.forEach(this::add);
		return true;
	}

	/**
	 * Returns True if the key is present in the count map
	 */
	@Override
	public boolean contains(Object o) {
		return data.containsKey(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return c.stream().allMatch(data::containsKey);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		c.forEach(this::remove);
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return data.keySet().retainAll(c);
	}

	@Override
	public int size() {
		return data.size();
	}

	/**
	 * @return The various counts totaled together
	 */
	public int total() {
		return data.values().stream().reduce(0, Integer::sum);
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new UnsupportedOperationException();
	}

}
