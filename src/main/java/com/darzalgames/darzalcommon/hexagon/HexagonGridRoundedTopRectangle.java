package com.darzalgames.darzalcommon.hexagon;

import com.darzalgames.darzalcommon.data.Coordinate;

public class HexagonGridRoundedTopRectangle extends HexagonGridRectangular {

	public HexagonGridRoundedTopRectangle(int gridWidth, int gridHeight) {
		super(gridWidth, gridHeight);
		grid.removeByFirstType(new Coordinate(0, 0));
		grid.removeByFirstType(new Coordinate(gridWidth-1, -gridWidth/2));
	}

}
