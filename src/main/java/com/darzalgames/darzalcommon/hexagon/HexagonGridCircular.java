package com.darzalgames.darzalcommon.hexagon;

import com.darzalgames.darzalcommon.data.BiMap;
import com.darzalgames.darzalcommon.data.Coordinate;

public class HexagonGridCircular extends HexagonGrid {
	
	private final int radius;

	public HexagonGridCircular(int radius) {
		super(makeGrid(radius - 1));
		this.radius = radius;
	}
	
	private static BiMap<Coordinate, Hexagon> makeGrid(int radius) {
		BiMap<Coordinate, Hexagon> grid = new BiMap<>();

		// adapted from https://www.redblobgames.com/grids/hexagons/implementation.html#map-shapes
		for (int q = -radius; q <= radius; q++) {
		    int r1 = Math.max(-radius, -q - radius);
		    int r2 = Math.min(radius, -q + radius);
		    for (int r = r1; r <= r2; r++) {
				Coordinate coordinate = new Coordinate(q, r);
				grid.addPair(coordinate, new Hexagon(coordinate));
		    }
		}
		
		return grid;
	}

	@Override
	public Hexagon getMiddleHexagon() {
		return grid.getSecondValue(new Coordinate(radius, 0));
	}

}
