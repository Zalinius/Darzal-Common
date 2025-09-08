package com.darzalgames.darzalcommon.hexagon.gridfactory;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.darzalcommon.hexagon.Hexagon;

/**
 * Factory method for rectangular hexagon tiling
 */
public class HexagonGridRectangular {

	/**
	 * Creates a List of hexagons, representing a rectangular layout
	 * @param gridWidth the number of hexagons along the x-axis
	 * @param gridHeight the number of hexagons along the y-axis
	 * @return A list of flat-top {@link Hexagon Hexagons}, with the axial coordinate (0,0) at the center (or near the center if width or height are even)
	 */
	public static List<Hexagon> makeGrid(int gridWidth, int gridHeight) {
		if(gridHeight <= 0 || gridWidth <= 0) {
			throw new IllegalArgumentException("grid width: " + gridWidth + " and grid height: " + gridHeight + " must be positive");
		}

		List<Hexagon> grid = new ArrayList<>();

		int left = Math.floorDiv(-gridWidth, 2);
		int right = Math.floorDiv(gridWidth, 2);
		int top =  Math.floorDiv(-gridHeight, 2);
		int bottom = Math.floorDiv(gridHeight, 2);
		// adapted from https://www.redblobgames.com/grids/hexagons/implementation.html#map-shapes
		for (int q = left + 1 ; q <= right; q++) { // flat top
			int qOffset = (int) Math.floor(q/2.0);
			for (int r = top + 1 - qOffset; r <= bottom - qOffset; r++) {
				grid.add(new Hexagon(q, r));
			}
		}

		return grid;
	}

	private HexagonGridRectangular() {}
}
