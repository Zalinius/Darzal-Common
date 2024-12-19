package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.darzalgames.darzalcommon.data.Coordinate;

class HexagonMathTest {
	

	@ParameterizedTest
    @CsvSource({
        "0, 0,		0, 0",
        "1, 1,		1, 1",
        "2, -1,		2, 0",
        "-1, 1,		-1, 0",
    })
	void axialToOffsetCoordinates_variousCoordinates_returnsTheCorrectlyConvertedCoordinates(int hexagonQ, int hexagonR, int expectedColumn, int expectedRow) {
	
		Coordinate result = HexagonMath.axialToOffsetCoordinates(hexagonQ, hexagonR);
		
		assertEquals(expectedColumn, result.i);
		assertEquals(expectedRow, result.j);
	}
	
	@ParameterizedTest
    @CsvSource({
        "0, 0,		0, 0",
        "1, 1,		1, 1",
        "2, 0,		2, -1",
        "-1, 0,		-1, 1",
    })
	void offsetToAxialCoordinates_variousCoordinates_returnsTheCorrectlyConvertedCoordinates(int column, int row, int expectedQ, int expectedR) {
	
		Coordinate result = HexagonMath.offsetToAxialCoordinates(column, row);
		
		assertEquals(expectedQ, result.i);
		assertEquals(expectedR, result.j);
	}

	@ParameterizedTest
    @CsvSource({
        "0, 0,		0, 0",
        "1, 1,		1, 1",
        "2, -1,		2, 0",
        "-1, 1,		-1, 0",
    })
	void axialToOffsetCoordinates_withAHexagon_returnsTheCorrectlyConvertedCoordinates(int hexagonQ, int hexagonR, int expectedColumn, int expectedRow) {
		Hexagon hexagon = new Hexagon(hexagonQ, hexagonR);
		
		Coordinate result = HexagonMath.axialToOffsetCoordinates(hexagon);
		
		assertEquals(expectedColumn, result.i);
		assertEquals(expectedRow, result.j);
	}

}
