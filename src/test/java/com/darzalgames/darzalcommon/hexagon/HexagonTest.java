package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.darzalgames.darzalcommon.data.Coordinate;

class HexagonTest {
	
	@Test
	void bothConstructors_sameCoordinates_makesEqualHexagons() {
		Hexagon hexagon1 = new Hexagon(3, 20);
		Hexagon hexagon2 = new Hexagon(new Coordinate(3, 20));

		assertEquals(hexagon1, hexagon2);
	}
	
	@ParameterizedTest
    @CsvSource({
        "0,  0",
        "3, -3",
        "-5, -2",
        "4, 9",
        "-8, 1",
        "0, 5",
        "-8, 0"
    })
	void getS_isCalculatedCorrectly(int q, int r) {
		Hexagon hexagon = new Hexagon(q, r);

		// s = -q-r
		assertEquals(-q-r, hexagon.getS());
	}

	@Test
	void equals_sameCoordinates_isTrue() {
		Hexagon hexagon1 = new Hexagon(3, -5);
		Hexagon hexagon2 = new Hexagon(3, -5);

		assertEquals(hexagon1, hexagon2);
	}
	
	@Test
	void equals_differentCoordinates_isFalse() {
		Hexagon hexagon1 = new Hexagon(3, 20);
		Hexagon hexagon2 = new Hexagon(3, -5);

		assertNotEquals(hexagon1, hexagon2);
	}

	@Test
	void equals_sameHexagon_isTrue() {
		Hexagon hexagon1 = new Hexagon(3, -5);

		assertEquals(hexagon1, hexagon1);
	}
	
	@Test
	void equals_withNull_isFalse() {
		Hexagon hexagon1 = new Hexagon(3, 20);
		
		boolean nullComparisonResult = hexagon1.equals(null);

		assertFalse(nullComparisonResult);
	}

	@Test
	void hashCode_sameCoordinates_isEqual() {
		Hexagon hexagon1 = new Hexagon(3, -5);
		Hexagon hexagon2 = new Hexagon(3, -5);

		assertEquals(hexagon1.hashCode(), hexagon2.hashCode());
	}
	
	@Test
	void hashCode_differentCoordinates_isNotEqual() {
		Hexagon hexagon1 = new Hexagon(3, 20);
		Hexagon hexagon2 = new Hexagon(3, -5);

		assertNotEquals(hexagon1.hashCode(), hexagon2.hashCode());
	}
}
