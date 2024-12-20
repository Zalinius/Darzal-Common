package com.darzalgames.darzalcommon.hexagon;

import com.darzalgames.darzalcommon.data.Coordinate;

public class HexagonGridRoundedTopRectangular extends HexagonGridRectangular {

	public HexagonGridRoundedTopRectangular(int gridWidth, int gridHeight) {
		super(gridWidth, gridHeight);
		grid.removeByFirstType(new Coordinate(0, 0));
		// TODO also remove the bottom right corner?
	}

}
