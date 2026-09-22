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
	 * Gets the neighbour hexagons of the input hexagon in this map
	 * @param hexagon The {@link Hexagon} whose neighbours you want to find
	 * @return A set of the neighbouring {@link Hexagon Hexagons}, in {@link HexagonDirection} order
	 */
	public Set<Hexagon> getPresentHexagonNeighboursOf(Hexagon hexagon) {
		return hexagon
				.getNeighbours()
				.stream()
				.filter(this::containsKey)
				.collect(MutableCollectors.toSet());
	}

	/**
	 * Gets the neighbour hexagons of the input hexagon NOT PRESENT in this map
	 * @param hexagon The {@link Hexagon} whose absent neighbours you want to find
	 * @return A set of the neighbouring {@link Hexagon Hexagons} which are NOT IN THIS MAP, in {@link HexagonDirection} order
	 */
	public Set<Hexagon> getAbsentHexagonNeighboursOf(Hexagon hexagon) {
		return hexagon
				.getNeighbours()
				.stream()
				.filter(hex -> !containsKey(hex))
				.collect(MutableCollectors.toSet());
	}

	/**
	 * Gets the neighbour values of the input hexagon in this map
	 * To be used for game logic where adjacencies are relevant
	 * @param hexagon The {@link Hexagon} whose neighbours you want to find
	 * @return A list of the neighbouring values, in {@link HexagonDirection} order
	 */
	public List<E> getValueNeighboursOf(Hexagon hexagon) {
		return getPresentHexagonNeighboursOf(hexagon).stream().map(this::get).toList();
	}

	/**
	 * Gets the immediate neibhbor value in the specified direction
	 * @param hexagon   The starting point for searching for a neighbour
	 * @param direction the direction to search in
	 * @return The neighbouring value in the given direction if it exists, otherwise throws an IllegalArgumentException
	 */
	public E getValueNeighbourInDirection(Hexagon hexagon, HexagonDirection direction) {
		Hexagon neighbour = direction.getNeighbourHexagon(hexagon);
		return get(neighbour);
	}

	@Override
	public E get(Object key) {
		if (!containsKey(key)) {
			throw new IllegalArgumentException("Hexagon not in map: " + key);
		}
		return super.get(key);
	}

}
