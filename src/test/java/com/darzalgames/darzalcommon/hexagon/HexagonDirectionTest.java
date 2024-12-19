package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.data.Coordinate;

class HexagonDirectionTest {

	@Test
	void getNeighborCoordinate_topLeft_hasCorrectCoordinates() {
		int startQ = 5;
		int startR = -3;

		Coordinate coordinate = HexagonDirection.getNeighborCoordinate(startQ, startR, HexagonDirection.TOP_LEFT);

		int expectedQ = startQ - 1;
		int expectedR = startR;

		assertEquals(expectedQ, coordinate.i);
		assertEquals(expectedR, coordinate.j);
	}

	@Test
	void getNeighborCoordinate_top_hasCorrectCoordinates() {
		int startQ = 12;
		int startR = 4;

		Coordinate coordinate = HexagonDirection.getNeighborCoordinate(startQ, startR, HexagonDirection.TOP);

		int expectedQ = startQ;
		int expectedR = startR - 1;

		assertEquals(expectedQ, coordinate.i);
		assertEquals(expectedR, coordinate.j);
	}

	@Test
	void getNeighborCoordinate_topRight_hasCorrectCoordinates() {
		int startQ = 0;
		int startR = -8;

		Coordinate coordinate = HexagonDirection.getNeighborCoordinate(startQ, startR, HexagonDirection.TOP_RIGHT);

		int expectedQ = startQ + 1;
		int expectedR = startR - 1;

		assertEquals(expectedQ, coordinate.i);
		assertEquals(expectedR, coordinate.j);
	}

	@Test
	void getNeighborCoordinate_bottomRight_hasCorrectCoordinates() {
		int startQ = -18;
		int startR = 6;

		Coordinate coordinate = HexagonDirection.getNeighborCoordinate(startQ, startR, HexagonDirection.BOTTOM_RIGHT);

		int expectedQ = startQ + 1;
		int expectedR = startR;

		assertEquals(expectedQ, coordinate.i);
		assertEquals(expectedR, coordinate.j);
	}

	@Test
	void getNeighborCoordinate_bottom_hasCorrectCoordinates() {
		int startQ = -6;
		int startR = 0;

		Coordinate coordinate = HexagonDirection.getNeighborCoordinate(startQ, startR, HexagonDirection.BOTTOM);

		int expectedQ = startQ;
		int expectedR = startR + 1;

		assertEquals(expectedQ, coordinate.i);
		assertEquals(expectedR, coordinate.j);
	}

	@Test
	void getNeighborCoordinate_bottomLeft_hasCorrectCoordinates() {
		int startQ = 0;
		int startR = -8;

		Coordinate coordinate = HexagonDirection.getNeighborCoordinate(startQ, startR, HexagonDirection.BOTTOM_LEFT);

		int expectedQ = startQ - 1;
		int expectedR = startR + 1;

		assertEquals(expectedQ, coordinate.i);
		assertEquals(expectedR, coordinate.j);
	}
}
