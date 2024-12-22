package com.darzalgames.darzalcommon.hexagon.gridfactory;

import java.util.List;

import com.darzalgames.darzalcommon.hexagon.Hexagon;

public class HexagonGridRoundedTopRectangular extends HexagonGridRectangular {

	/**
	 * @param gridWidth the number of hexagons along the x-axis
	 * @param gridHeight the number of hexagons along the y-axis
	 * @return A list of flat-top {@link Hexagon Hexagons}, with the "pointy" corners removed
	 */
	public static List<Hexagon> makeGrid(int gridWidth, int gridHeight) {
		List<Hexagon> grid = HexagonGridRectangular.makeGrid(gridWidth, gridHeight);
		grid.remove(new Hexagon(0, 0));
		// TODO also remove the bottom right corner?
		
		return grid;
	}

}
