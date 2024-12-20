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
        "0,  0,	0", // s = -q-r
        "3, -3, 0",
        "-5, -2, 7",
        "4, 9, -13",
        "-8, 1, 7",
        "0, 5, -5",
        "-8, 0, 8"
    })
	void getS_isCalculatedCorrectly(int q, int r, int s) {
		Hexagon hexagon = new Hexagon(q, r);
		
		assertEquals(s, hexagon.getS());
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
	void equals_withNonHexagon_isFalse() {
		Hexagon hexagon1 = new Hexagon(3, 20);
		
		@SuppressWarnings("unlikely-arg-type")
		boolean stringComparisonResult = hexagon1.equals("hexagon");

		assertFalse(stringComparisonResult);
	}

	@Test
	void hashCode_sameHexagon_isEqual() {
		Hexagon hexagon1 = new Hexagon(3, -5);
		Hexagon hexagon2 = new Hexagon(3, -5);

		assertEquals(hexagon1.hashCode(), hexagon2.hashCode());
	}

	@Test
	void hashCode_sameCoordinates_isEqual() {
		Hexagon hexagon = new Hexagon(2, -8);

		assertEquals(hexagon.hashCode(), hexagon.hashCode());
	}
	
	@Test
	void hashCode_differentCoordinates_isNotEqual() {
		Hexagon hexagon1 = new Hexagon(3, 20);
		Hexagon hexagon2 = new Hexagon(3, -5);

		assertNotEquals(hexagon1.hashCode(), hexagon2.hashCode());
	}
}
