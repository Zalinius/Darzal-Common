package com.darzalgames.darzalcommon.hexagon.gridfactory;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.darzalcommon.hexagon.Hexagon;

public class HexagonGridCircular {
	
	/**
	 * @param radius the number of hexagons along the circle's radius
	 * @return A list of flat-top {@link Hexagon Hexagons}, with the axial coordinate (0,0) in the center
	 */
	public static List<Hexagon> makeGrid(int radius) {
		if(radius <= 0) {
			throw new IllegalArgumentException("radius must be positive: " + radius);
		}
		
		radius -= 1;
		List<Hexagon> grid = new ArrayList<>();

		// adapted from https://www.redblobgames.com/grids/hexagons/implementation.html#map-shapes
		for (int q = -radius; q <= radius; q++) {
		    int r1 = Math.max(-radius, -q - radius);
		    int r2 = Math.min(radius, -q + radius);
		    for (int r = r1; r <= r2; r++) {
				grid.add(new Hexagon(q, r));
		    }
		}
		
		return grid;
	}

	private HexagonGridCircular() {}
	
}
