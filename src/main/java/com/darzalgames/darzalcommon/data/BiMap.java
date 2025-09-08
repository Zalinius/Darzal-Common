package com.darzalgames.darzalcommon.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A bidirectional map with a 1-to-1 mapping. When a pair is inserted, the values will act both as keys and values.
 * @param <F> The *left* parameter of the map. The type must be suitable for use as a key (equals, uniqueness) and for hashing
 * @param <S> The *right* parameter of the map. The type must be suitable for use as a key (equals, uniqueness) and for hashing
 */
public class BiMap <F, S> {

	private final Map<F, S> mapFirstToSecond;
	private final Map<S, F> mapSecondToFirst;

	/**
	 * Creates an empty Bidirectional Map
	 */
	public BiMap() {
		mapFirstToSecond = new HashMap<>();
		mapSecondToFirst = new HashMap<>();
	}

	/**
	 * Adds a two way pair to the bimap
	 * @param first A key and value to insert
	 * @param second A key and value to insert
	 * @return the previous two values potentially replaced by this insertion, or an empty tuple if nothing was replaced
	 */
	public Tuple<F, S> addPair(F first, S second) {
		S oldSecondValue = mapFirstToSecond.put(first, second);
		F oldFirstValue = mapSecondToFirst.put(second, first);

		return new Tuple<>(oldFirstValue, oldSecondValue);
	}

	/**
	 * Gets the value associate with the Second type key
	 * @param second the key
	 * @return The value, or null if the key is not present in the map
	 */
	public F getFirstValue(S second) {
		return mapSecondToFirst.get(second);
	}

	/**
	 * Gets the value associate with the First type key
	 * @param first the key
	 * @return The value, or null if the key is not present in the map
	 */
	public S getSecondValue(F first) {
		return mapFirstToSecond.get(first);
	}

	/**
	 * Get the key set for the first type
	 * @return The entire First keyset
	 */
	public Set<F> getFirstKeySet() {
		return mapFirstToSecond.keySet();
	}

	/**
	 * Get the key set for the second type
	 * @return The entire second keyset
	 */
	public Set<S> getSecondKeyset() {
		return mapSecondToFirst.keySet();
	}

	/**
	 * Checks if the given key of the first type is present in the bimap
	 * @param first The key to check
	 * @return true if the bimap contains the key
	 */
	public boolean containsFirstKey(F first) {
		return mapFirstToSecond.containsKey(first);
	}

	/**
	 * Checks if the given key of the second type is present in the bimap
	 * @param second The key to check
	 * @return true if the bimap contains the key
	 */
	public boolean containsSecondKey(S second) {
		return mapSecondToFirst.containsKey(second);
	}

	/**
	 * Removes a key-key pair if both keys are present and linked to eachother
	 * @param first The first key
	 * @param second The second key
	 * @return The removed pair as a tuple, or null if the pair wasn't in the bimap
	 */
	public Tuple<F, S> removePair(F first, S second) {
		if (mapFirstToSecond.containsKey(first) && mapSecondToFirst.containsKey(second) && getSecondValue(first).equals(second) && getFirstValue(second).equals(first))	{
			S sValue = mapFirstToSecond.remove(first);
			F fValue = mapSecondToFirst.remove(second);
			return new Tuple<>(fValue, sValue);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes the key pair with the specified F key
	 * @param first the key to be used
	 * @return True if a pair is removed, false otherwise
	 */
	public Tuple<F, S> removeByFirstType(F first)	{
		if (mapFirstToSecond.containsKey(first)) {
			return removePair(first, mapFirstToSecond.get(first));
		}
		else {
			return null;
		}
	}

	/**
	 * Removes the key pair with the specified S key
	 * @param second the key to be used
	 * @return True if a pair is removed, false otherwise
	 */
	public Tuple<F, S> removeBySecondType(S second) {
		if (mapSecondToFirst.containsKey(second)) {
			return removePair(mapSecondToFirst.get(second), second);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the number of key-key mappings in this bi-map.
	 * @return the number of pairs in this bimap
	 */
	public int size() {
		return mapFirstToSecond.size();
	}

	/**
	 * Returns true if this map contains no pairs.
	 * @return true if this map contains no pairs, false otherwise
	 */
	public boolean isEmpty() {
		return mapFirstToSecond.isEmpty();
	}

}
