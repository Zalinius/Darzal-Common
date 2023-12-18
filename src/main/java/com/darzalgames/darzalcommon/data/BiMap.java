package com.darzalgames.darzalcommon.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A two way map with 1-to-1 mapping. When a pair is inserted
 * Both values will act both as keys and values.
 * @param <F> The *left* parameter of the map. The type must be suitable for use as a key (equals, uniqueness) and for hashing
 * @param <S> The *right* parameter of the map. The type must be suitable for use as a key (equals, uniqueness) and for hashing
 */
public class BiMap <F, S> {

	private Map<F, S> mapFirstToSecond;
	private Map<S, F> mapSecondToFirst;
	
	public BiMap() {
		mapFirstToSecond = new HashMap<>();
		mapSecondToFirst = new HashMap<>();
	}
	
	/**
	 * @param first A key and value to insert
	 * @param second A key and value to insert
     * @return the previous two values potentially replaced by this insertion, or null if nothing was replaced
	 */
	public Tuple<F, S> addPair(F first, S second) {
		S oldSecondValue = mapFirstToSecond.put(first, second);
		F oldFirstValue = mapSecondToFirst.put(second, first);
		
		return new Tuple<>(oldFirstValue, oldSecondValue);
	}
	
	public F getFirstValue(S second) {
		return mapSecondToFirst.get(second);
	}
	
	public S getSecondValue(F objectT) {
		return mapFirstToSecond.get(objectT);
	}
	
	public Set<F> getFirstKeySet() {
		return mapFirstToSecond.keySet();
	}
	
	public Set<S> getSecondKeyset() {
		return mapSecondToFirst.keySet();
	}
	
	public boolean containsFirstValue(F first) {
		return mapFirstToSecond.containsKey(first);
	}
	
	public boolean containsSecondValue(S second) {
		return mapSecondToFirst.containsKey(second);
	}
	
	public boolean removePair(F first, S second) {
		if (mapFirstToSecond.containsKey(first) && mapSecondToFirst.containsKey(second))
		{
			mapFirstToSecond.remove(first);
			mapSecondToFirst.remove(second);
			return true;
		}
		return false;
	}
	
	public boolean removeByFirstType(F first)	{
		if (mapFirstToSecond.containsKey(first))
		{
			return removePair(first, mapFirstToSecond.get(first));
		}
		return false;
	}
	
	public boolean removeBySecondType(S second) {
		if (mapSecondToFirst.containsKey(second))
		{
			return removePair(mapSecondToFirst.get(second), second);
		}
		return false;
	}
	
}
