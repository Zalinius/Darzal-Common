package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class HexagonGridRoundedTopRectangularTest {

	@Test
	void constructor_0x0_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new HexagonGridRoundedTopRectangular(0, 0));
	}

	@ParameterizedTest
    @CsvSource({
        "3, 3,		1, 1",
        "5, 2,		2, 0",
        "3, 5,		1, 2",
        "2, 4,		0, 2",
        "2, 2,		0, 1",
        "7, 4,		3, 0",
        "4, 3,		1, 1",
        "4, 4,		1, 1",
    })
	void getMiddleHexagon_withVariousSizes_returnsExpectedCoordinates(int gridWidth, int gridHeight, int expectedQ, int expectedR) {
		HexagonGridRectangular hexagonGrid = new HexagonGridRoundedTopRectangular(gridWidth, gridHeight);
		
		Hexagon middle = hexagonGrid.getMiddleHexagon();
		
		assertEquals(expectedQ, middle.getQ());
		assertEquals(expectedR, middle.getR());
	}

	@ParameterizedTest
    @CsvSource({
        "3, 3,		8",
        "5, 2,		9",
        "3, 5,		14",
        "2, 4,		7",
        "2, 2,		3",
        "7, 4,		27",
        "4, 3,		11",
        "4, 4,		15",
    })
	void getAllHexagons_variousSizes_returnsAll(int gridWidth, int gridHeight, int expectedNumberOfHexagons) {
		HexagonGridRectangular hexagonGrid = new HexagonGridRoundedTopRectangular(gridWidth, gridHeight);
		
		Collection<Hexagon> all = hexagonGrid.getAllHexagons();
		
		assertEquals(expectedNumberOfHexagons, all.size());
	}
}
