package com.zalinius.darzalcommon.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CountMap<K> implements Iterable<K>{

	private Map<K, Integer> data;

	private static final int DEFAULT = 0;
	
	public CountMap() {
		this.data = new HashMap<>();
	}

	public CountMap(CountMap<K> other) {
		this.data = new HashMap<>(other.data);
	}

	public int get(K key) {
		return data.getOrDefault(key, DEFAULT);
	}
	
	public void increment(K key) {
		data.put(key, get(key)+1);
	}

	public void decrement(K key) {
		data.put(key, get(key)-1);
	}

	public void reset(K key) {
		data.put(key, DEFAULT);
	}
	
	public void clear() {
		data.clear();
	}

	public void increaseBy(K key, int amount) {
		data.put(key, get(key)+amount);
	}
	
	public void decreaseBy(K key, int amount) {
		data.put(key, get(key)-amount);
	}

	public Set<K> keySet(){
		return data.keySet();
	}
	
	@Override
	public Iterator<K> iterator() {
		return data.keySet().iterator();
	}
	
	public Collection<Integer> values() {
		return data.values();
	}
	
	@Override
	public String toString() {
		return data.toString();
	}
	

}
