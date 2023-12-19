package com.darzalgames.darzalcommon.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A convenience map whose value type is always Integers. Useful for keeping counts of stuff.
 * Conveniently, it's functions never return null values, nor can they throw Exceptions
 * All keys are valid, and counts without a key can be gotten and modified
 * @param <K> The key type for the map
 */
public class CountMap<K> implements Iterable<K>{

	private Map<K, Integer> data;

	private static final int DEFAULT = 0;
	
	/**
	 * Creates a blank CountMap
	 */
	public CountMap() {
		this.data = new HashMap<>();
	}

	/**
	 * Creates a new Count Map with the same entries as the other
	 * @param other The map whose entries should be copied
	 */
	public CountMap(CountMap<K> other) {
		this.data = new HashMap<>(other.data);
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
	 * Resets the count associated with a key to 0
	 * @param key The key to reset
	 */
	public void reset(K key) {
		data.put(key, DEFAULT);
	}
	
	/**
	 * Resets the count associated with all keys to 0
	 */
	public void clear() {
		data.clear();
	}

	/**
	 * Increases the count associated with a key
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

}
