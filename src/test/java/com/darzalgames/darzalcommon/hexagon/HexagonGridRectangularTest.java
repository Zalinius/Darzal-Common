package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.darzalgames.darzalcommon.hexagon.gridfactory.HexagonGridRectangular;

class HexagonGridRectangularTest {

	@Test
	void makeGrid_0x0_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> HexagonGridRectangular.makeGrid(0, 0));
		assertThrows(IllegalArgumentException.class, () -> HexagonGridRectangular.makeGrid(0, 2));
		assertThrows(IllegalArgumentException.class, () -> HexagonGridRectangular.makeGrid(2, 0));
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
		"1, 1,		1",
	})
	void makeGrid_variousSizes_returnsAll(int gridWidth, int gridHeight, int expectedNumberOfHexagons) {
		List<Hexagon> hexagons = HexagonGridRectangular.makeGrid(gridWidth, gridHeight);

		assertEquals(expectedNumberOfHexagons, hexagons.size());
	}


	@Test
	void makeGrid_with1x1_containsCorrectHexagons() throws Exception {
		Set<Hexagon> expectedHexagons = Set.of(new Hexagon(0, 0));

		List<Hexagon> hexagons = HexagonGridRectangular.makeGrid(1, 1);

		assertEquals(expectedHexagons.size(), hexagons.size());
		assertTrue(expectedHexagons.containsAll(hexagons));
	}

	@Test
	void makeGrid_with2x2_containsCorrectHexagons() throws Exception {
		Set<Hexagon> expectedHexagons = Set.of(new Hexagon(0, 0), new Hexagon(0, 1), new Hexagon(1, 0), new Hexagon(1, 1));

		List<Hexagon> hexagons = HexagonGridRectangular.makeGrid(2, 2);

		assertEquals(expectedHexagons.size(), hexagons.size());
		assertTrue(expectedHexagons.containsAll(hexagons));
	}

	@Test
	void makeGrid_with3x3_containsCorrectHexagons() throws Exception {
		Set<Hexagon> expectedHexagons = Set.of(new Hexagon(0, 0), new Hexagon(0, 1), new Hexagon(1, 0), new Hexagon(1, 1),
				new Hexagon(0, -1), new Hexagon(1, -1), new Hexagon(-1, 0), new Hexagon(-1, 1), new Hexagon(-1, 2));

		List<Hexagon> hexagons = HexagonGridRectangular.makeGrid(3, 3);

		assertEquals(expectedHexagons.size(), hexagons.size());
		assertTrue(expectedHexagons.containsAll(hexagons));
	}

	@Test
	void makeGrid_with2x3_containsCorrectHexagons() throws Exception {
		Set<Hexagon> expectedHexagons = Set.of(new Hexagon(0, 0), new Hexagon(0, 1), new Hexagon(1, 0), new Hexagon(1, 1),
				new Hexagon(0, -1), new Hexagon(1, -1));

		List<Hexagon> hexagons = HexagonGridRectangular.makeGrid(2, 3);

		assertEquals(expectedHexagons.size(), hexagons.size());
		assertTrue(expectedHexagons.containsAll(hexagons));
	}

	@Test
	void makeGrid_with3x2_containsCorrectHexagons() throws Exception {
		Set<Hexagon> expectedHexagons = Set.of(new Hexagon(0, 0), new Hexagon(0, 1), new Hexagon(1, 0), new Hexagon(1, 1),
				new Hexagon(-1, 1), new Hexagon(-1, 2));

		List<Hexagon> hexagons = HexagonGridRectangular.makeGrid(3, 2);

		assertEquals(expectedHexagons.size(), hexagons.size());
		assertTrue(expectedHexagons.containsAll(hexagons));
	}

}
