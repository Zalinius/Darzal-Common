package com.darzalgames.darzalcommon.data;

import java.util.*;

/**
 * A convenience map whose value type is always Integers. Useful for keeping counts of stuff.
 * Conveniently, the values are always returned as integer primitives, and cannot return null, nor can they throw Exceptions
 * All keys are valid (including null), and counts without a key can be gotten and modified safely
 * @param <K> The key type for the map. It should implement equals and hashcode like keys used in other maps. Null keys are valid
 */
public class CountMap<K> implements Iterable<K>{

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
	 * @throws NullPointerException if collection is null
	 */
	public CountMap(Collection<K> collection) {
		this();
		Objects.requireNonNull(collection, "collection must not be null");
		incrementAll(collection);
	}

	/**
	 * Get the count associated with a key
	 * @param key The key associated with the count
	 * @return The count of the key, or 0 if the key is not present
	 */
	public int get(K key) {
		return data.getOrDefault(key, DEFAULT);
	}

	/**
	 * Increments the count associated with a key
	 * @param key The key to increment
	 */
	public void increment(K key) {
		data.put(key, get(key)+1);
	}

	/**
	 * Decrements the count associated with a key
	 * @param key The key to decrement
	 */
	public void decrement(K key) {
		data.put(key, get(key)-1);
	}

	/**
	 * Increments the counts associated with each key in a collection
	 * @param keys The collection of keys to increment. Duplicate keys will be incremented multiple times
	 */
	public void incrementAll(Collection<K> keys) {
		keys.forEach(this::increment);
	}

	/**
	 * Decrements the counts associated with each key in a collection
	 * @param keys The collection of keys to decrement. Duplicate keys will be decremented multiple times
	 */
	public void decrementAll(Collection<K> keys) {
		keys.forEach(this::decrement);
	}

	/**
	 * Increases the count associated with a key by an amount
	 * @param key The key to increase
	 * @param amount The amount to increase by
	 */
	public void increaseBy(K key, int amount) {
		data.put(key, get(key)+amount);
	}

	/**
	 * Decreases the count associated with a key
	 * @param key The key to decrease
	 * @param amount The amount to decrease by
	 */
	public void decreaseBy(K key, int amount) {
		data.put(key, get(key)-amount);
	}

	/**
	 * Checks if a key has a count associated with it in the count map
	 * @param key whose presence is to checked
	 * @return true if and only if this keys is being tracked in the count map
	 */
	public boolean containsKey(K key) {
		return data.containsKey(key);
	}

	/**
	 * Resets the count associated with a key to 0, and removes its key from the CountMap
	 * @param key The key to reset
	 */
	public void reset(K key) {
		data.remove(key);
	}

	/**
	 * Resets the count associated with all keys to 0, and removes them from the CountMap
	 * This operation is identical to creating a new CountMap
	 */
	public void clear() {
		data.clear();
	}

	/**
	 * Gets the number of key-count pairs
	 * @return The number of keys-count pairs being tracked in the count map
	 */
	public int size() {
		return data.size();
	}

	/**
	 * Returns true if there are no key-count pairs in the count map
	 * @return True if there are no key-count pairs in the count map, false otherwise
	 */
	public boolean isEmpty() {
		return data.isEmpty();
	}

	/**
	 * Returns the sum of all the counts in the count map
	 * @return The various counts totaled together
	 */
	public int total() {
		return data.values().stream().reduce(0, Integer::sum);
	}

	/**
	 * Returns the count map's keyset
	 * @return A set of all the keys in with counts tracked in the count map
	 */
	public Set<K> keySet(){
		return data.keySet();
	}


	/**
	 * Returns an iterator for all the keys in use in the map
	 * @return an iterator of the keys that have counts associated with them
	 */
	@Override
	public Iterator<K> iterator() {
		return data.keySet().iterator();
	}

	/**
	 * Returns all the counts in the map
	 * @return A collection of all the tracked counts, in no particular order
	 */
	public Collection<Integer> values() {
		return data.values();
	}

	@Override
	public String toString() {
		return data.toString();
	}

}
