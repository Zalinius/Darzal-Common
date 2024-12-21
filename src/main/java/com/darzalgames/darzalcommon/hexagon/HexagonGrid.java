package com.darzalgames.darzalcommon.hexagon;

import java.util.*;
import java.util.stream.Collectors;

import com.darzalgames.darzalcommon.data.BiMap;
import com.darzalgames.darzalcommon.data.Coordinate;

public abstract class HexagonGrid {

	protected final BiMap<Coordinate, Hexagon> grid;

	protected HexagonGrid(BiMap<Coordinate, Hexagon> grid) {
		this.grid = grid;
		if (getAllHexagons().isEmpty()) {
			throw new IllegalArgumentException("Grid must not be empty.");
		}
	}

	public abstract Hexagon getMiddleHexagon();

	/**
	 * @return all {@link Hexagon} objects stored by this grid
	 */
	public Collection<Hexagon> getAllHexagons() {
		return grid.getSecondKeyset();
	}

	/**
	 * To be used for game logic where adjacencies are relevant
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A list of the neighboring {@link Hexagon Hexagons}
	 */
	public List<Hexagon> getNeighborsOf(Hexagon hexagon) {
		return Arrays.asList(HexagonDirection.values()).stream()
				.map(direction -> getNeighborInDirectionIfItExists(hexagon, direction))
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	// TODO this is used by the above method, and so the bottom two methods throw exceptions and null never escapes this class. Is this good??
	// (I kind of feel like this method should be public, and the below method not exist lol)
	private Hexagon getNeighborInDirectionIfItExists(Hexagon hexagon, HexagonDirection direction) {
		Coordinate neighborCoordinate = HexagonDirection.getNeighborCoordinate(hexagon.getQ(), hexagon.getR(), direction);
		if (grid.containsFirstValue(neighborCoordinate)) {
			return getNeighborInDirection(hexagon, direction);
		} else {
			return null;
		}
	}

	/**
	 * @param hexagon
	 * @param direction
	 * @return The neighboring Hexagon in the given direction if it exists, otherwise throws an IllegalArgumentException 
	 */
	public Hexagon getNeighborInDirection(Hexagon hexagon, HexagonDirection direction) {
		Coordinate neighborCoordinate = HexagonDirection.getNeighborCoordinate(hexagon.getQ(), hexagon.getR(), direction);
		return getHexagonAt(neighborCoordinate);
	}

	/**
	 * @param coordinate
	 * @return The Hexagon at the given coordinates if it exists, otherwise throws an IllegalArgumentException 
	 */
	public Hexagon getHexagonAt(Coordinate coordinate) {
		if (grid.containsFirstValue(coordinate)) {
			return grid.getSecondValue(coordinate);
		} else {
			throw new IllegalArgumentException("Cannot get hexagon outside of the grid at: " + coordinate);
		}
	}
}
