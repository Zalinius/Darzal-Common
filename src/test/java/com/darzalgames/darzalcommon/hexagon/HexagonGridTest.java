package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.darzalgames.darzalcommon.data.Coordinate;

class HexagonGridTest {

	@ParameterizedTest
    @CsvSource({
        "1, 1,		6", //Surrounded hexagon
        "5, -2,		3", //Corner hexagon (top right)
        "0, 0,		2", //Corner hexagon (top left)
        "0, 1,		4", //Edge hexagon
    })
	void getNeighborsOf_variousHexagons_returnsTheCorrectNumberOfNonNullNeighbors(int hexagonQ, int hexagonR, int expectedNumberOfNeighbors) {
		HexagonGridRectangular hexagonGrid = new HexagonGridRectangular(6, 6);
		
		List<Hexagon> neighbors = hexagonGrid.getNeighborsOf(hexagonGrid.getHexagonAt(new Coordinate(hexagonQ, hexagonR)));
		
		assertEquals(expectedNumberOfNeighbors, neighbors.size());
		neighbors.forEach(Assertions::assertNotNull);
	}

	@Test
	void getAllHexagons_size2x2_returnsAll() {
		HexagonGridRectangular hexagonGrid = new HexagonGridRectangular(2, 2);
		
		Collection<Hexagon> all = hexagonGrid.getAllHexagons();
		
		assertEquals(4, all.size());
		assertTrue(all.contains(new Hexagon(0, 0)));
		assertTrue(all.contains(new Hexagon(0, 1)));
		assertTrue(all.contains(new Hexagon(1, 0)));
		assertTrue(all.contains(new Hexagon(1, 1)));
	}

	@ParameterizedTest
    @CsvSource({
        "3, 3,		1, 1",
        "5, 2,		2, 0",
        "1, 1,		0, 0",
        "3, 5,		1, 2",
        "2, 4,		0, 2",
        "2, 2,		0, 1",
        "7, 4,		3, 0",
        "4, 3,		1, 1",
        "4, 4,		1, 1",
    })
	void getMiddleHexagon_withVariousSizes_returnsExpectedCoordinates(int gridWidth, int gridHeight, int expectedQ, int expectedR) {
		HexagonGridRectangular hexagonGrid = new HexagonGridRectangular(gridWidth, gridHeight);
		
		Hexagon middle = hexagonGrid.getMiddleHexagon();
		
		assertEquals(expectedQ, middle.getQ());
		assertEquals(expectedR, middle.getR());
	}
	
}
