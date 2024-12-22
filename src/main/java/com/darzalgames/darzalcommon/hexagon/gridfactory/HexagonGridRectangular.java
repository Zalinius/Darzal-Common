package com.darzalgames.darzalcommon.hexagon.gridfactory;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.darzalcommon.hexagon.Hexagon;

/**
 * The Board is what builds the actual grid and makes the {@link Hexagon} objects, and allows some interactions
 * (e.g. finding the neighbors of a particular Hexagon)
 * @author DarZal
 */
public class HexagonGridRectangular {
	

	// TODO make 0,0 centered on the grid and adapt the test with this in mind

	/**
	 * @param gridWidth the number of hexagons along the x-axis
	 * @param gridHeight the number of hexagons along the y-axis
	 * @return A list of flat-top {@link Hexagon Hexagons}, with the axial coordinate (0,0) in the bottom-left corner
	 */
	public static List<Hexagon> makeGrid(int gridWidth, int gridHeight) {
		List<Hexagon> grid = new ArrayList<>();

		int left = 0;
		int right = gridWidth - 1;
		int top = 0;
		int bottom = gridHeight - 1;
		// adapted from https://www.redblobgames.com/grids/hexagons/implementation.html#map-shapes
		for (int q = left; q <= right; q++) { // flat top
			int qOffset = (int) Math.floor(q/2.0);
			for (int r = top - qOffset; r <= bottom - qOffset; r++) {
				grid.add(new Hexagon(q, r));
			}
		}
		if (grid.isEmpty()) {
			throw new IllegalArgumentException("Grid must not be empty.");
		}
		
		return grid;
	}

}
