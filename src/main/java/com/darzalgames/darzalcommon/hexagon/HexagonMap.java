package com.darzalgames.darzalcommon.hexagon;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A java-style map, with hexagons as keys and various convenience functions
 * @param <E> The type for the values of the key-value pairs in the map
 */
public class HexagonMap<E> extends HashMap<Hexagon, E> {

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
	 * To be used for game logic where adjacencies are relevant
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A list of the neighboring {@link Hexagon Hexagons}
	 */
	public Set<Hexagon> getHexagonNeighborsOf(Hexagon hexagon) {
		return HexagonDirection.values().stream()
				.map(direction -> direction.getNeighborHexagon(hexagon))
				.filter(this::containsKey)
				.collect(Collectors.toSet());
	}

	/**
	 * To be used for game logic where adjacencies are relevant
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A list of the neighboring values
	 */
	public Collection<E> getValueNeighborsOf(Hexagon hexagon) {
		return getHexagonNeighborsOf(hexagon).stream().map(this::get).toList();
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
