package com.darzalgames.darzalcommon.hexagon;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A java-style map, with hexagons as keys and various convenience functions
 * @param <E> The type for the values of the key-value pairs in the map
 */
public class HexagonMap<E> {

	private final Map<Hexagon, E> innerMap;

	/**
	 * Constructs an empty HexagonMap
	 */
	public HexagonMap() {
		innerMap = new HashMap<>();
	}

	/**
	 * Gets the value in the middle of the map
	 * @return gets the value at the origin hexagon (0,0)
	 */
	public E getMiddleHexagonValue() {
		return innerMap.get(new Hexagon(0, 0));
	}

	/**
	 * Puts a value at the specified hexagon coordinate
	 * @param hexagon the location for the new value
	 * @param value the value to insert
	 * @return the previous value at the hexagon coordinate, or null if there was none
	 */
	public E put(Hexagon hexagon, E value) {
		return innerMap.put(hexagon, value);
	}

	/**
	 * Returns the set of all hexagon keys in this map
	 * @return all {@link Hexagon} objects stored by this map
	 */
	public Set<Hexagon> getAllHexagons() {
		return innerMap.keySet();
	}

	/**
	 * Gets all the values stored in this map
	 * @return all objects stored by this map
	 */
	public Collection<E> getAllValues() {
		return innerMap.values();
	}

	/**
	 * To be used for game logic where adjacencies are relevant
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A list of the neighboring {@link Hexagon Hexagons}
	 */
	public Set<Hexagon> getHexagonNeighborsOf(Hexagon hexagon) {
		return HexagonDirection.values().stream()
				.map(direction -> HexagonDirection.getNeighborHexagon(hexagon, direction))
				.filter(innerMap::containsKey)
				.collect(Collectors.toSet());
	}
	/**
	 * To be used for game logic where adjacencies are relevant
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A list of the neighboring values
	 */
	public Collection<E> getValueNeighborsOf(Hexagon hexagon) {
		return getHexagonNeighborsOf(hexagon).stream().map(innerMap::get).toList();
	}

	/**
	 * Gets the immediate neibhbor value in the specified direction
	 * @param hexagon The starting point for searching for a neighbor
	 * @param direction the direction to search in
	 * @return The neighboring value in the given direction if it exists, otherwise throws an IllegalArgumentException
	 */
	public E getValueNeighborInDirection(Hexagon hexagon, HexagonDirection direction) {
		Hexagon neighbor = HexagonDirection.getNeighborHexagon(hexagon, direction);
		return get(neighbor);
	}

	/**
	 * Get the value at the specified hexagon
	 * @param hexagon the hexagon address of the desired value
	 * @return The value at the given hexagon coordinates if it exists, otherwise throws an IllegalArgumentException
	 */
	public E get(Hexagon hexagon) {
		if (innerMap.containsKey(hexagon)) {
			return innerMap.get(hexagon);
		} else {
			throw new IllegalArgumentException("Hexagon not in map: " + hexagon);
		}
	}
}
