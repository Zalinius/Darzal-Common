package com.darzalgames.darzalcommon.hexagon;

import java.util.*;
import java.util.stream.Collectors;

public class HexagonMap<E> {

	protected final Map<Hexagon, E> grid;

	public HexagonMap() {
		grid = new HashMap<>();
	}

	public E getMiddleHexagonValue() {
		return grid.get(new Hexagon(0, 0));
	}
	
	public void put(Hexagon hexagon, E value) {
		grid.put(hexagon, value);
	}

	/**
	 * @return all {@link Hexagon} objects stored by this grid
	 */
	public Set<Hexagon> getAllHexagons() {
		return grid.keySet();
	}

	/**
	 * @return all objects stored by this grid
	 */
	public Collection<E> getAllValues() {
		return grid.values();
	}
	
	/**
	 * To be used for game logic where adjacencies are relevant
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A list of the neighboring {@link Hexagon Hexagons}
	 */
	public Set<Hexagon> getHexagonNeighborsOf(Hexagon hexagon) {
		return HexagonDirection.values().stream()
				.map(direction -> HexagonDirection.getNeighborHexagon(hexagon, direction))
				.filter(potentialNeighbor -> grid.containsKey(potentialNeighbor))
				.collect(Collectors.toSet());
	}
	/**
	 * To be used for game logic where adjacencies are relevant
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A list of the neighboring values
	 */
	public Collection<E> getValueNeighborsOf(Hexagon hexagon) {
		return getHexagonNeighborsOf(hexagon).stream().map(neighbor -> grid.get(neighbor)).collect(Collectors.toList());
	}

	/**
	 * @param hexagon
	 * @param direction
	 * @return The neighboring value in the given direction if it exists, otherwise throws an IllegalArgumentException 
	 */
	public E getNeighborInDirection(Hexagon hexagon, HexagonDirection direction) {
		Hexagon neighbor = HexagonDirection.getNeighborHexagon(hexagon, direction);
		return getValueAt(neighbor);
	}

	/**
	 * @param hexagon
	 * @return The value at the given hexagon coordinates if it exists, otherwise throws an IllegalArgumentException 
	 */
	public E getValueAt(Hexagon hexagon) {
		if (grid.containsKey(hexagon)) {
			return grid.get(hexagon);
		} else {
			throw new IllegalArgumentException("Cannot get hexagon outside of the grid at: " + hexagon);
		}
	}
}
