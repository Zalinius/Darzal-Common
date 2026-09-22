package com.darzalgames.darzalcommon.hexagon;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A java-style map, with hexagons as keys and various convenience functions
 * @param <E> The type for the values of the key-value pairs in the map
 */
public class HexagonMap<E> extends LinkedHashMap<Hexagon, E> {

	private static final long serialVersionUID = 5801182744649958152L;

	/**
	 * Constructs an empty HexagonMap
	 */
	public HexagonMap() {
		super();
	}

	/**
	 * Gets the value in the middle of the map
	 * @return gets the value at the origin hexagon (0,0)
	 */
	public E getMiddleHexagonValue() {
		return get(Hexagon.ORIGIN);
	}

	/**
	 * Gets the neighbour hexagons of the input hexagon in this map
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A set of the neighboring {@link Hexagon Hexagons}, in {@link HexagonDirection} order
	 */
	public Set<Hexagon> getPresentHexagonNeighboursOf(Hexagon hexagon) {
		return getAllHexagonNeighborsOf(hexagon).stream()
				.filter(this::containsKey)
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}

	/**
	 * Gets the neighbour hexagons of the input hexagon NOT PRESENT in this map
	 * @param hexagon The {@link Hexagon} whose absent neighbors you want to find
	 * @return A set of the neighboring {@link Hexagon Hexagons} which are NOT IN THIS MAP, in {@link HexagonDirection} order
	 */
	public Set<Hexagon> getAbsentHexagonNeighborsOf(Hexagon hexagon) {
		return getAllHexagonNeighborsOf(hexagon).stream()
				.filter(hex -> !containsKey(hex))
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}

	/**
	 * Gets all the neighbour hexagons of the input hexagon
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A set of the neighboring {@link Hexagon Hexagons}, in {@link HexagonDirection} order. They may or may not be present in this map!
	 */
	public Set<Hexagon> getAllHexagonNeighborsOf(Hexagon hexagon) {
		return HexagonDirection.values().stream()
				.map(direction -> direction.getNeighborHexagon(hexagon))
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}

	/**
	 * Gets the neighbour values of the input hexagon in this map
	 * To be used for game logic where adjacencies are relevant
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A list of the neighboring values, in {@link HexagonDirection} order
	 */
	public List<E> getValueNeighborsOf(Hexagon hexagon) {
		return getPresentHexagonNeighboursOf(hexagon).stream().map(this::get).toList();
	}

	/**
	 * Gets the immediate neibhbor value in the specified direction
	 * @param hexagon   The starting point for searching for a neighbor
	 * @param direction the direction to search in
	 * @return The neighboring value in the given direction if it exists, otherwise throws an IllegalArgumentException
	 */
	public E getValueNeighborInDirection(Hexagon hexagon, HexagonDirection direction) {
		Hexagon neighbor = direction.getNeighborHexagon(hexagon);
		return get(neighbor);
	}

	@Override
	public E get(Object key) {
		if (!containsKey(key)) {
			throw new IllegalArgumentException("Hexagon not in map: " + key);
		}
		return super.get(key);
	}

}
