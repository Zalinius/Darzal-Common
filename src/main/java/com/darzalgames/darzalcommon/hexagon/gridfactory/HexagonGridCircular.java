package com.darzalgames.darzalcommon.hexagon.gridfactory;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.darzalcommon.hexagon.Hexagon;
import com.darzalgames.darzalcommon.math.SimpleMath;

/**
 * factory methods for constructing circular(hexagonal) hexagon-grids
 */
public class HexagonGridCircular {

	private HexagonGridCircular() {}

	/**
	 * Creates a list of hexagons in a hexagonal pattern, with a given radius
	 * @param radius the number of hexagons along the circle's radius
	 * @return A list of flat-top {@link Hexagon Hexagons}, with the axial coordinate (0,0) in the center
	 */
	public static List<Hexagon> makeGrid(int radius) {
		if (radius <= 0) {
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

	/**
	 * Computes the number of hexagon tiles in a circular grid of a specific radius
	 * @param radius the radius of the circular grid
	 * @return the number of hexagon tiles
	 */
	public static int computeSize(int radius) {
		if (radius < 0) {
			throw new IllegalArgumentException("radius must be non-negative: " + radius);
		}
		if (radius == 0) {
			return 0;
		}
		return 1 + 6 * SimpleMath.sumOfNaturalNumbers(radius - 1);
	}

	/**
	 * Computes the minimum circular grid radius required to fit the specified number of tiles"
	 * @param gridSize the number of tiles
	 * @return the minimum radius
	 */
	public static int computeMinimumRadius(int gridSize) {
		if (gridSize < 0) {
			throw new IllegalArgumentException("gridSize must be non-negative: " + gridSize);
		}
		int i = 0;
		while (computeSize(i) < gridSize) {
			i++;
		}
		return i;
	}

}
