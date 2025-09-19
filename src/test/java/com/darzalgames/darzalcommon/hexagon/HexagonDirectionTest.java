package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HexagonDirectionTest {

	@Test
	void getNeighborHexagon_topLeft_hasCorrectHexagons() {
		int startQ = 5;
		int startR = -3;

		Hexagon hexagon = HexagonDirection.TOP_LEFT.getNeighborHexagon(startQ, startR);

		assertEquals(4, hexagon.q());
		assertEquals(startR, hexagon.r());
	}

	@Test
	void getNeighborHexagon_top_hasCorrectHexagons() {
		int startQ = 12;
		int startR = 4;

		Hexagon hexagon = HexagonDirection.TOP.getNeighborHexagon(startQ, startR);

		assertEquals(startQ, hexagon.q());
		assertEquals(3, hexagon.r());
	}

	@Test
	void getNeighborHexagon_topRight_hasCorrectHexagons() {
		int startQ = 0;
		int startR = -8;

		Hexagon hexagon = HexagonDirection.TOP_RIGHT.getNeighborHexagon(startQ, startR);

		assertEquals(1, hexagon.q());
		assertEquals(-9, hexagon.r());
	}

	@Test
	void getNeighborHexagon_bottomRight_hasCorrectHexagons() {
		int startQ = -18;
		int startR = 6;

		Hexagon hexagon = HexagonDirection.BOTTOM_RIGHT.getNeighborHexagon(startQ, startR);

		assertEquals(-17, hexagon.q());
		assertEquals(startR, hexagon.r());
	}

	@Test
	void getNeighborHexagon_bottom_hasCorrectHexagons() {
		int startQ = -6;
		int startR = 0;

		Hexagon hexagon = HexagonDirection.BOTTOM.getNeighborHexagon(startQ, startR);

		assertEquals(startQ, hexagon.q());
		assertEquals(1, hexagon.r());
	}

	@Test
	void getNeighborHexagon_bottomLeft_hasCorrectHexagons() {
		int startQ = 0;
		int startR = -8;

		Hexagon hexagon = HexagonDirection.BOTTOM_LEFT.getNeighborHexagon(startQ, startR);

		assertEquals(-1, hexagon.q());
		assertEquals(-7, hexagon.r());
	}
}
