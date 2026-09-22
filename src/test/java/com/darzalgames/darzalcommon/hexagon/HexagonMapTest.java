package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.darzalgames.darzalcommon.hexagon.gridfactory.HexagonGridCircular;
import com.darzalgames.darzalcommon.hexagon.gridfactory.HexagonGridRectangular;

class HexagonMapTest {

	@Test
	void getMiddleHexagonValue_rectangularGrid_containsExpectedTag() {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(4, 3).forEach(hex -> hexagonMap.put(hex, ""));

		hexagonMap.put(Hexagon.ORIGIN, "middle!");

		assertEquals("middle!", hexagonMap.getMiddleHexagonValue());
	}

	@Test
	void getAllHexagons_returnsAllHexagons() {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(3, 3).forEach(hex -> hexagonMap.put(hex, ""));
		Set<Hexagon> expectedHexagons = Set.of(
				Hexagon.ORIGIN, new Hexagon(0, 1), new Hexagon(1, 0), new Hexagon(1, 1),
				new Hexagon(0, -1), new Hexagon(1, -1), new Hexagon(-1, 0), new Hexagon(-1, 1), new Hexagon(-1, 2)
		);

		Collection<Hexagon> all = hexagonMap.keySet();

		assertEquals(expectedHexagons.size(), all.size());
		assertTrue(all.containsAll(expectedHexagons));
	}

	@Test
	void getAllValues_returnsAllValues() {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(3, 3).forEach(hex -> hexagonMap.put(hex, hex.toString()));
		Set<String> expectedHexagons = Set.of(
				Hexagon.ORIGIN.toString(), new Hexagon(0, 1).toString(),
				new Hexagon(1, 0).toString(), new Hexagon(1, 1).toString(), new Hexagon(0, -1).toString(),
				new Hexagon(1, -1).toString(), new Hexagon(-1, 0).toString(), new Hexagon(-1, 1).toString(), new Hexagon(-1, 2).toString()
		);

		Collection<String> all = hexagonMap.values();

		assertEquals(expectedHexagons.size(), all.size());
		assertTrue(all.containsAll(expectedHexagons));
	}

	@Test
	void getValueNeighboursOf_variousValues_returnsAllValuesInHexagonDirectionOrder() {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(3, 3).forEach(hex -> hexagonMap.put(hex, hex.toString()));

		List<String> neighbours = hexagonMap.getValueNeighboursOf(Hexagon.ORIGIN);

		assertEquals(6, neighbours.size());
		assertEquals("Hexagon[q=-1, r=0]", neighbours.get(0));
		assertEquals("Hexagon[q=0, r=-1]", neighbours.get(1));
		assertEquals("Hexagon[q=1, r=-1]", neighbours.get(2));
		assertEquals("Hexagon[q=1, r=0]", neighbours.get(3));
		assertEquals("Hexagon[q=0, r=1]", neighbours.get(4));
		assertEquals("Hexagon[q=-1, r=1]", neighbours.get(5));
	}

	@Test
	void getPresentHexagonNeighboursOf_origin_returnsThemInDirectionOrdering() {
		HexagonMap<Void> hexagonMap = new HexagonMap<>();
		HexagonGridCircular.makeGrid(2).forEach(hex -> hexagonMap.put(hex, null));

		Iterator<Hexagon> neighbours = hexagonMap.getPresentHexagonNeighboursOf(Hexagon.ORIGIN).iterator();

		assertEquals(neighbours.next(), new Hexagon(-1, 0));
		assertEquals(neighbours.next(), new Hexagon(0, -1));
		assertEquals(neighbours.next(), new Hexagon(1, -1));
		assertEquals(neighbours.next(), new Hexagon(1, 0));
		assertEquals(neighbours.next(), new Hexagon(0, 1));
		assertEquals(neighbours.next(), new Hexagon(-1, 1));
		assertFalse(neighbours.hasNext());
	}

	@Test
	void getPresentHexagonNeighboursOf_originSurrounded_returnsTheSameSetsAsTheHexagonsNeighbours() {
		HexagonMap<Void> hexagonMap = new HexagonMap<>();
		HexagonGridCircular.makeGrid(2).forEach(hex -> hexagonMap.put(hex, null));

		Set<Hexagon> presentNeighbours = hexagonMap.getPresentHexagonNeighboursOf(Hexagon.ORIGIN);
		Set<Hexagon> allNeighbours = Hexagon.ORIGIN.getNeighbours();

		assertEquals(presentNeighbours, allNeighbours);
	}

	@ParameterizedTest
	@CsvSource({
			"0, 0,		6", // Surrounded hexagon
			"2, -2,		2", // Corner hexagon (top right)
			"2, 0,		3", // Corner hexagon (bottom right)
			"-1, 1,		4", // Edge hexagon
	})
	void getPresentHexagonNeighboursOf_variousHexagons_returnsTheCorrectNumberOfNonNullNeighbours(int hexagonQ, int hexagonR, int expectedNumberOfNeighbours) {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(4, 3).forEach(hex -> hexagonMap.put(hex, ""));

		Set<Hexagon> neighbours = hexagonMap.getPresentHexagonNeighboursOf(new Hexagon(hexagonQ, hexagonR));

		assertEquals(expectedNumberOfNeighbours, neighbours.size());
		neighbours.forEach(Assertions::assertNotNull);
	}

	@ParameterizedTest
	@CsvSource({
			"0, 0,		0", // Surrounded hexagon
			"2, -2,		4", // Corner hexagon (top right)
			"2, 0,		3", // Corner hexagon (bottom right)
			"-1, 1,		2", // Edge hexagon
	})
	void getAbsentHexagonNeighboursOf_variousHexagons_returnsTheCorrectNumberOfNeighboursNotInTheMap(int hexagonQ, int hexagonR, int expectedNumberOfAbsentNeighbours) {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(4, 3).forEach(hex -> hexagonMap.put(hex, ""));

		Set<Hexagon> neighbours = hexagonMap.getAbsentHexagonNeighboursOf(new Hexagon(hexagonQ, hexagonR));

		assertEquals(expectedNumberOfAbsentNeighbours, neighbours.size());
		neighbours.forEach(n -> assertFalse(hexagonMap.containsKey(n)));
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

	private static Stream<Arguments> hexagonAndNeighbourInDirectionCoordinates() {
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
	@MethodSource("hexagonAndNeighbourInDirectionCoordinates")
	void getValueNeighbourInDirection_variousHexagons_returnsTheCorrectNeighbours(int hexagonQ, int hexagonR, HexagonDirection testDirection, int expectedNeighbourHexagonQ, int expectedNeighbourHexagonR) {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(6, 6).forEach(hex -> hexagonMap.put(hex, hex.q() + " " + hex.r()));

		String neighbour = hexagonMap.getValueNeighbourInDirection(new Hexagon(hexagonQ, hexagonR), testDirection);

		assertEquals(expectedNeighbourHexagonQ + " " + expectedNeighbourHexagonR, neighbour);
	}

	@Test
	void remove_onHexagonWithValue_removesPairingFromMap() {
		HexagonMap<Integer> hexagonMap = new HexagonMap<>();
		hexagonMap.put(Hexagon.ORIGIN, 5);

		int removedValue = hexagonMap.remove(Hexagon.ORIGIN);

		assertEquals(5, removedValue);
		assertFalse(hexagonMap.containsKey(Hexagon.ORIGIN));
	}

}
