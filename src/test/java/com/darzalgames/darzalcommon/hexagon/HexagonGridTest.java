package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

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
	void getHexagonAt_withVariousValidCoordinates_returnsExpectedHexagon(int expectedQ, int expectedR) {
		HexagonGridRectangular hexagonGrid = new HexagonGridRectangular(6, 6);
		Hexagon expected = new Hexagon(expectedQ, expectedR);
		
		Hexagon resultHexagon = hexagonGrid.getHexagonAt(new Coordinate(expectedQ, expectedR));
		
		assertEquals(expected.getQ(), resultHexagon.getQ());
		assertEquals(expected.getR(), resultHexagon.getR());
		assertEquals(expected, resultHexagon);
	}

	@ParameterizedTest
    @CsvSource({
        "3, 5",
        "4, 4",
        "-4, 7",
    })
	void getHexagonAt_withOutOfBoundsCoordinates_throwsIllegalArgumentException(int expectedQ, int expectedR) {
		HexagonGridRectangular hexagonGrid = new HexagonGridRectangular(6, 6);
		
		Coordinate coordinate = new Coordinate(expectedQ, expectedR);
		
		assertThrows(IllegalArgumentException.class, () -> hexagonGrid.getHexagonAt(coordinate));
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
		HexagonGridRectangular hexagonGrid = new HexagonGridRectangular(6, 6);
		
		Hexagon neighbor = hexagonGrid.getNeighborInDirection(new Hexagon(new Coordinate(hexagonQ, hexagonR)), testDirection);
		
		assertEquals(expectedNeighborHexagonQ, neighbor.getQ());
		assertEquals(expectedNeighborHexagonR, neighbor.getR());
	}
	
}
