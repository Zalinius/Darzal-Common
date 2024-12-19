package com.darzalgames.darzalcommon.hexagon;

import java.util.*;
import java.util.stream.Collectors;

import com.darzalgames.darzalcommon.data.BiMap;
import com.darzalgames.darzalcommon.data.Coordinate;

public abstract class HexagonGrid {

	protected final BiMap<Coordinate, Hexagon> grid;

	protected HexagonGrid(BiMap<Coordinate, Hexagon> grid) {
		this.grid = grid;
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
				.map(direction -> getNeighborInDirection(hexagon, direction))
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	public Hexagon getNeighborInDirection(Hexagon hexagon, HexagonDirection direction) {
		Coordinate neighborCoordinate = HexagonDirection.getNeighborCoordinate(hexagon.getQ(), hexagon.getR(), direction);
		return getHexagonAt(neighborCoordinate);
	}

	public Hexagon getHexagonAt(Coordinate coordinate) {
		if (grid.containsFirstValue(coordinate)) {
			return grid.getSecondValue(coordinate);
		} else {
			return null;
		}
	}
}
