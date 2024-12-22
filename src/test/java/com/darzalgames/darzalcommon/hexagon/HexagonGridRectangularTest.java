package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.darzalgames.darzalcommon.hexagon.gridfactory.HexagonGridRectangular;

public class HexagonGridRectangularTest {

	@Test
	void makeGrid_0x0_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> HexagonGridRectangular.makeGrid(0, 0));
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
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(gridWidth, gridHeight).forEach(hex -> hexagonMap.add(hex, ""));
		String middleTag = "middle!";
		hexagonMap.add(new Hexagon(0,0), middleTag);

		String middle = hexagonMap.getMiddleHexagonValue();
		
		assertEquals(middleTag, middle);
	}

	@ParameterizedTest
    @CsvSource({
        "3, 3,		9",
        "5, 2,		10",
        "3, 5,		15",
        "2, 4,		8",
        "2, 2,		4",
        "7, 4,		28",
        "4, 3,		12",
        "4, 4,		16",
    })
	void getAllHexagons_variousSizes_returnsAll(int gridWidth, int gridHeight, int expectedNumberOfHexagons) {
		List<Hexagon> hexagons = HexagonGridRectangular.makeGrid(gridWidth, gridHeight);
		
		assertEquals(expectedNumberOfHexagons, hexagons.size());
	}
}
