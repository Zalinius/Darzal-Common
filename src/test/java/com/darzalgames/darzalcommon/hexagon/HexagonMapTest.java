package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.darzalgames.darzalcommon.hexagon.gridfactory.HexagonGridRectangular;

class HexagonMapTest {

	@ParameterizedTest
    @CsvSource({
        "1, 1,		6", //Surrounded hexagon
        "5, -2,		3", //Corner hexagon (top right)
        "0, 0,		2", //Corner hexagon (top left)
        "0, 1,		4", //Edge hexagon
    })
	void getNeighborsOf_variousHexagons_returnsTheCorrectNumberOfNonNullNeighbors(int hexagonQ, int hexagonR, int expectedNumberOfNeighbors) {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(6, 6).forEach(hex -> hexagonMap.add(hex, ""));
		
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
	void getValueAt_withVariousValidCoordinates_returnsExpectedValue(int expectedQ, int expectedR) {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(6, 6).forEach(hex -> hexagonMap.add(hex, ""));
		String expectedTag = "it's me!";
		hexagonMap.add(new Hexagon(expectedQ, expectedR), expectedTag);
		
		String result = hexagonMap.getValueAt(new Hexagon(expectedQ, expectedR));
		
		assertEquals(expectedTag, result);
	}

	@ParameterizedTest
    @CsvSource({
        "3, 5",
        "4, 4",
        "-4, 7",
    })
	void getValueAt_withOutOfBoundsCoordinates_throwsIllegalArgumentException(int expectedQ, int expectedR) {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(6, 6).forEach(hex -> hexagonMap.add(hex, ""));
		
		Hexagon hexagon = new Hexagon(expectedQ, expectedR);
		
		assertThrows(IllegalArgumentException.class, () -> hexagonMap.getValueAt(hexagon));
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
	void getNeighborInDirection_variousHexagons_returnsTheCorrectNeighbors(int hexagonQ, int hexagonR, HexagonDirection testDirection, int expectedNeighborHexagonQ, int expectedNeighborHexagonR) {
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridRectangular.makeGrid(6, 6).forEach(hex -> hexagonMap.add(hex, hex.getQ() + " " + hex.getR()));
		
		String neighbor = hexagonMap.getNeighborInDirection(new Hexagon(hexagonQ, hexagonR), testDirection);
		
		assertEquals(expectedNeighborHexagonQ + " " + expectedNeighborHexagonR, neighbor);
	}
	
}
