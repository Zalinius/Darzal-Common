package com.darzalgames.darzalcommon.hexagon;

import com.darzalgames.darzalcommon.data.Coordinate;

public enum HexagonDirection {
	TOP_LEFT(new Coordinate(-1, 0)),
	TOP(new Coordinate(0, -1)),
	TOP_RIGHT(new Coordinate(1, -1)),
	BOTTOM_RIGHT(new Coordinate(1, 0)),
	BOTTOM(new Coordinate(0, 1)),
	BOTTOM_LEFT(new Coordinate(-1, 1));
	
	private final Coordinate coordinate;

	private HexagonDirection(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	/**
	 * Calculates the neighboring {@link Coordinate} in a particular hexagonal direction
	 * @param q Q-axis coordinate whose neighbor you want to find (axial system)
	 * @param r R-axis coordinate whose neighbor you want to find (axial system)
	 * @param direction The direction for the coordinate that you want to find
	 * @return The neighboring {@link Coordinate} in a particular direction
	 */
	public static Coordinate getNeighborCoordinate(int q, int r, HexagonDirection direction) {
		int neighborQ = q + direction.coordinate.i;
		int neighborR = r + direction.coordinate.j;
		return new Coordinate(neighborQ, neighborR);
	}
	
}
