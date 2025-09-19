package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.darzalgames.darzalcommon.hexagon.gridfactory.HexagonGridCircular;

class HexagonGridCircularTest {

	@Test
	void makeGrid_0Radius_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> HexagonGridCircular.makeGrid(0));
		assertThrows(IllegalArgumentException.class, () -> HexagonGridCircular.makeGrid(-1));
		assertDoesNotThrow(() -> HexagonGridCircular.makeGrid(1));
	}

	@Test
	void makeGrid_withRadius1_containsCorrectHexagons() {
		Set<Hexagon> expectedHexagons = Set.of(Hexagon.ORIGIN);

		List<Hexagon> hexagons = HexagonGridCircular.makeGrid(1);

		assertEquals(expectedHexagons.size(), hexagons.size());
		assertTrue(expectedHexagons.containsAll(hexagons));
	}

	@Test
	void makeGrid_withRadius2_containsCorrectHexagons() {
		Set<Hexagon> expectedHexagons = Set.of(Hexagon.ORIGIN, new Hexagon(-1, 0), new Hexagon(-1, 1), new Hexagon(0, -1), new Hexagon(0, 1), new Hexagon(1, -1), new Hexagon(1, 0));

		List<Hexagon> hexagons = HexagonGridCircular.makeGrid(2);

		assertEquals(expectedHexagons.size(), hexagons.size());
		assertTrue(expectedHexagons.containsAll(hexagons));
	}

	@Test
	void makeGrid_withRadius3_containsCorrectHexagons() {
		Set<Hexagon> expectedHexagons = Set.of(
				Hexagon.ORIGIN, new Hexagon(-1, 0), new Hexagon(-1, 1), new Hexagon(0, -1), new Hexagon(0, 1), new Hexagon(1, -1), new Hexagon(1, 0),
				new Hexagon(0, -2), new Hexagon(1, -2), new Hexagon(2, -2), new Hexagon(-1, -1), new Hexagon(2, -1), new Hexagon(-2, 0), new Hexagon(2, 0), new Hexagon(-2, 1),
				new Hexagon(1, 1), new Hexagon(-2, 2), new Hexagon(-1, 2), new Hexagon(0, 2)
		);

		List<Hexagon> hexagons = HexagonGridCircular.makeGrid(3);

		assertEquals(expectedHexagons.size(), hexagons.size());
		assertTrue(expectedHexagons.containsAll(hexagons));
	}

	@ParameterizedTest
	@CsvSource({
			"1, 1",
			"2, 7",
			"3, 19",
			"4, 37",
	})
	void getAllHexagons_variousSizes_returnsAll(int radius, int expectedNumberOfHexagons) {
		List<Hexagon> hexagons = HexagonGridCircular.makeGrid(radius);

		assertEquals(expectedNumberOfHexagons, hexagons.size());
	}
}
