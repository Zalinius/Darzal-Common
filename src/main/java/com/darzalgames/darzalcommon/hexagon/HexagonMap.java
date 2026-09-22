package com.darzalgames.darzalcommon.hexagon;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import com.darzalgames.darzalcommon.functional.MutableCollectors;

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
	 * Gets the neighbor hexagons of the input hexagon in this map
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A set of the neighboring {@link Hexagon Hexagons}, in {@link HexagonDirection} order
	 */
	public Set<Hexagon> getPresentHexagonNeighborsOf(Hexagon hexagon) {
		return hexagon
				.getNeighbors()
				.stream()
				.filter(this::containsKey)
				.collect(MutableCollectors.toSet());
	}

	/**
	 * Gets the neighbor hexagons of the input hexagon NOT PRESENT in this map
	 * @param hexagon The {@link Hexagon} whose absent neighbors you want to find
	 * @return A set of the neighboring {@link Hexagon Hexagons} which are NOT IN THIS MAP, in {@link HexagonDirection} order
	 */
	public Set<Hexagon> getAbsentHexagonNeighborsOf(Hexagon hexagon) {
		return hexagon
				.getNeighbors()
				.stream()
				.filter(hex -> !containsKey(hex))
				.collect(MutableCollectors.toSet());
	}

	/**
	 * Gets the neighbor values of the input hexagon in this map
	 * To be used for game logic where adjacencies are relevant
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A list of the neighboring values, in {@link HexagonDirection} order
	 */
	public List<E> getValueNeighborsOf(Hexagon hexagon) {
		return getPresentHexagonNeighborsOf(hexagon).stream().map(this::get).toList();
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
