package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.darzalgames.darzalcommon.hexagon.gridfactory.HexagonGridRectangular;

class HexagonMapTest {

	@Test
	void getMiddleHexagonValue_rectangularGrid_containsExpectedTag() {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(4, 3).forEach(hex -> hexagonMap.put(hex, ""));

		hexagonMap.put(new Hexagon(0, 0), "middle!");

		assertEquals("middle!", hexagonMap.getMiddleHexagonValue());
	}

	@Test
	void getAllHexagons_returnsAllHexagons() {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(3, 3).forEach(hex -> hexagonMap.put(hex, ""));
		Set<Hexagon> expectedHexagons = Set.of(
				new Hexagon(0, 0), new Hexagon(0, 1), new Hexagon(1, 0), new Hexagon(1, 1),
				new Hexagon(0, -1), new Hexagon(1, -1), new Hexagon(-1, 0), new Hexagon(-1, 1), new Hexagon(-1, 2)
		);

		Collection<Hexagon> all = hexagonMap.getAllHexagons();

		assertEquals(expectedHexagons.size(), all.size());
		assertTrue(all.containsAll(expectedHexagons));
	}

	@Test
	void getAllValues_returnsAllValues() {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(3, 3).forEach(hex -> hexagonMap.put(hex, hex.toString()));
		Set<String> expectedHexagons = Set.of(
				new Hexagon(0, 0).toString(), new Hexagon(0, 1).toString(),
				new Hexagon(1, 0).toString(), new Hexagon(1, 1).toString(), new Hexagon(0, -1).toString(),
				new Hexagon(1, -1).toString(), new Hexagon(-1, 0).toString(), new Hexagon(-1, 1).toString(), new Hexagon(-1, 2).toString()
		);

		Collection<String> all = hexagonMap.getAllValues();

		assertEquals(expectedHexagons.size(), all.size());
		assertTrue(all.containsAll(expectedHexagons));
	}

	@Test
	void getValueNeighborsOf_variousValues_returnsAllValues() {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(3, 3).forEach(hex -> hexagonMap.put(hex, hex.toString()));
		Set<String> expectedHexagons = Set.of(
				new Hexagon(0, 1).toString(), new Hexagon(1, 0).toString(), new Hexagon(0, -1).toString(),
				new Hexagon(1, -1).toString(), new Hexagon(-1, 0).toString(), new Hexagon(-1, 1).toString()
		);

		Collection<String> neighbors = hexagonMap.getValueNeighborsOf(new Hexagon(0, 0));

		assertEquals(6, neighbors.size());
		assertTrue(neighbors.containsAll(expectedHexagons));
	}

	@ParameterizedTest
	@CsvSource({
			"0, 0,		6", // Surrounded hexagon
			"2, -2,		2", // Corner hexagon (top right)
			"2, 0,		3", // Corner hexagon (bottom right)
			"-1, 1,		4", // Edge hexagon
	})
	void getHexagonNeighborsOf_variousHexagons_returnsTheCorrectNumberOfNonNullNeighbors(int hexagonQ, int hexagonR, int expectedNumberOfNeighbors) {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(4, 3).forEach(hex -> hexagonMap.put(hex, ""));

		Set<Hexagon> neighbors = hexagonMap.getHexagonNeighborsOf(new Hexagon(hexagonQ, hexagonR));

		assertEquals(expectedNumberOfNeighbors, neighbors.size());
		neighbors.forEach(Assertions::assertNotNull);
	}

	@ParameterizedTest
	@CsvSource({
			"3, 3",
			"5, 2",
			"1, 1",
			"2, -1",
			"0, 0",
			"2, 4",
			"2, 2",
			"2, 4",
			"4, -1",
	})
	void get_withVariousValidCoordinates_returnsExpectedValue(int expectedQ, int expectedR) {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(6, 6).forEach(hex -> hexagonMap.put(hex, ""));
		String expectedTag = "it's me!";
		hexagonMap.put(new Hexagon(expectedQ, expectedR), expectedTag);

		String result = hexagonMap.get(new Hexagon(expectedQ, expectedR));

		assertEquals(expectedTag, result);
	}

	@ParameterizedTest
	@CsvSource({
			"3, 5",
			"4, 4",
			"-4, 7",
	})
	void get_withOutOfBoundsCoordinates_throwsIllegalArgumentException(int expectedQ, int expectedR) {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(6, 6).forEach(hex -> hexagonMap.put(hex, ""));

		Hexagon hexagon = new Hexagon(expectedQ, expectedR);

		assertThrows(IllegalArgumentException.class, () -> hexagonMap.get(hexagon));
	}

	private static Stream<Arguments> hexagonAndNeighborInDirectionCoordinates() {
		return Stream.of(
				Arguments.of(2, 0, HexagonDirection.TOP, 2, -1),
				Arguments.of(1, 2, HexagonDirection.TOP_RIGHT, 2, 1),
				Arguments.of(0, 2, HexagonDirection.BOTTOM_RIGHT, 1, 2),
				Arguments.of(1, 0, HexagonDirection.BOTTOM, 1, 1),
				Arguments.of(3, 0, HexagonDirection.BOTTOM_LEFT, 2, 1),
				Arguments.of(1, 1, HexagonDirection.TOP_LEFT, 0, 1)
		);
	}

	@ParameterizedTest
	@MethodSource("hexagonAndNeighborInDirectionCoordinates")
	void getValueNeighborInDirection_variousHexagons_returnsTheCorrectNeighbors(int hexagonQ, int hexagonR, HexagonDirection testDirection, int expectedNeighborHexagonQ, int expectedNeighborHexagonR) {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(6, 6).forEach(hex -> hexagonMap.put(hex, hex.getQ() + " " + hex.getR()));

		String neighbor = hexagonMap.getValueNeighborInDirection(new Hexagon(hexagonQ, hexagonR), testDirection);

		assertEquals(expectedNeighborHexagonQ + " " + expectedNeighborHexagonR, neighbor);
	}

}
