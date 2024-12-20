package com.darzalgames.darzalcommon.hexagon;

import com.darzalgames.darzalcommon.data.BiMap;
import com.darzalgames.darzalcommon.data.Coordinate;
import com.darzalgames.darzalcommon.math.SimpleMath;

/**
 * The Board is what builds the actual grid and makes the {@link Hexagon} objects, and allows some interactions
 * (e.g. finding the neighbors of a particular Hexagon)
 * @author DarZal
 */
public class HexagonGridRectangular extends HexagonGrid {

	private final int gridWidth;
	private final int gridHeight;


	/**
	 * A HexagonGrid is a rectangular grid that self-populates with flat-top {@link Hexagon Hexagons}, with the axial coordinate (0,0) in the bottom-left corner
	 * @param gridWidth the number of hexagons along the x-axis
	 * @param gridHeight the number of hexagons along the y-axis
	 */
	public HexagonGridRectangular(int gridWidth, int gridHeight) {
		super(makeGrid(gridWidth, gridHeight));
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
	}
	
	private static BiMap<Coordinate, Hexagon> makeGrid(int gridWidth, int gridHeight) {
		BiMap<Coordinate, Hexagon> grid = new BiMap<>();

		int left = 0;
		int right = gridWidth - 1;
		int top = 0;
		int bottom = gridHeight - 1;
		// adapted from https://www.redblobgames.com/grids/hexagons/implementation.html#map-shapes
		for (int q = left; q <= right; q++) { // flat top
			int qOffset = (int) Math.floor(q/2.0);
			for (int r = top - qOffset; r <= bottom - qOffset; r++) {
				Coordinate coordinate = new Coordinate(q, r);
				grid.addPair(coordinate, new Hexagon(coordinate));
			}
		}
		
		return grid;
	}

	@Override
	public Hexagon getMiddleHexagon() {
		int x = (int) Math.floor(gridWidth/2f);
		if (SimpleMath.isEven(gridWidth)) {
			x -= 1;
		}
		int y = (int) Math.floor(gridHeight/2f);
		if (SimpleMath.isEven(gridHeight) && SimpleMath.isOdd(x)) {
			y -= 1;
		}
		return getHexagonAt(HexagonMath.offsetToAxialCoordinates(x, y));
	}

}
