package com.darzalgames.darzalcommon.data;

import java.util.*;

/**
 * A variation on CountMaps, with the following features:
 * <ul>
 * <li>Negative values are forbidden, and an IllegalStateException will be thrown if ever such a value is stored</li>
 * <li>Keys whose values reach 0 are automatically removed from the map, which is to say any key in the map is associated with a strictly positive value</li>
 * </ul>
 * @param <K> The key type for the map. It should implement equals and hashcode like keys used in other maps. Null keys are valid
 */
public class PositiveCountMap<K> extends CountMap<K> {

	/**
	 * Creates a blank CountMap
	 */
	public PositiveCountMap() {
		this(new HashMap<>());
	}

	/**
	 * Creates a blank CountMap using the provided map as backing
	 * @param innerMap a map that will be used as the backing of this count map
	 */
	public PositiveCountMap(Map<K, Integer> innerMap) {
		this(innerMap, List.of());
	}

	/**
	 * Creates a CountMap, initialized with all the values of the provided collection
	 * @param initialIncrements An existing non null collection to increment the count map with
	 * @throws NullPointerException if the initialIncrements is null
	 */
	public PositiveCountMap(Collection<K> initialIncrements) {
		this(new HashMap<>(), initialIncrements);
	}

	/**
	 * Creates a CountMap, using the provided map as backing, initialized with all the values of the provided collection
	 * @param innerMap          a map that will be used as the backing of this count map
	 * @param initialIncrements An existing non null collection to increment the count map with
	 * @throws NullPointerException if the initialIncrements collection is null
	 */
	public PositiveCountMap(Map<K, Integer> innerMap, Collection<K> initialIncrements) {
		super(innerMap, initialIncrements);
	}

	@Override
	protected void changeValue(K key, int newValue) {
		if (newValue < 0) {
			throw new IllegalStateException("newValue for key: '" + key + "' must be non-negative: " + newValue);
		} else if (newValue == 0) {
			super.reset(key);
		} else {
			super.changeValue(key, newValue);
		}
	}

}
