package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HexagonDirectionTest {

	@Test
	void getNeighborHexagon_topLeft_hasCorrectHexagons() {
		int startQ = 5;
		int startR = -3;

		Hexagon hexagon = HexagonDirection.getNeighborHexagon(startQ, startR, HexagonDirection.TOP_LEFT);

		assertEquals(4, hexagon.getQ());
		assertEquals(startR, hexagon.getR());
	}

	@Test
	void getNeighborHexagon_top_hasCorrectHexagons() {
		int startQ = 12;
		int startR = 4;

		Hexagon hexagon = HexagonDirection.getNeighborHexagon(startQ, startR, HexagonDirection.TOP);

		assertEquals(startQ, hexagon.getQ());
		assertEquals(3, hexagon.getR());
	}

	@Test
	void getNeighborHexagon_topRight_hasCorrectHexagons() {
		int startQ = 0;
		int startR = -8;

		Hexagon hexagon = HexagonDirection.getNeighborHexagon(startQ, startR, HexagonDirection.TOP_RIGHT);

		assertEquals(1, hexagon.getQ());
		assertEquals(-9, hexagon.getR());
	}

	@Test
	void getNeighborHexagon_bottomRight_hasCorrectHexagons() {
		int startQ = -18;
		int startR = 6;

		Hexagon hexagon = HexagonDirection.getNeighborHexagon(startQ, startR, HexagonDirection.BOTTOM_RIGHT);

		assertEquals(-17, hexagon.getQ());
		assertEquals(startR, hexagon.getR());
	}

	@Test
	void getNeighborHexagon_bottom_hasCorrectHexagons() {
		int startQ = -6;
		int startR = 0;

		Hexagon hexagon = HexagonDirection.getNeighborHexagon(startQ, startR, HexagonDirection.BOTTOM);

		assertEquals(startQ, hexagon.getQ());
		assertEquals(1, hexagon.getR());
	}

	@Test
	void getNeighborHexagon_bottomLeft_hasCorrectHexagons() {
		int startQ = 0;
		int startR = -8;

		Hexagon hexagon = HexagonDirection.getNeighborHexagon(startQ, startR, HexagonDirection.BOTTOM_LEFT);

		assertEquals(-1, hexagon.getQ());
		assertEquals(-7, hexagon.getR());
	}
}
