package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class HexagonGridCircularTest {

	@Test
	void constructor_0x0_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> new HexagonGridCircular(0));
	}

	@ParameterizedTest
    @CsvSource({
        "1",
        "2",
        "3",
        "4",
        "5",
        "7",
    })
	void getMiddleHexagon_withVariousSizes_returnsCenterCoordinates(int radius) {
		HexagonGridCircular hexagonGrid = new HexagonGridCircular(radius);
		
		Hexagon middle = hexagonGrid.getMiddleHexagon();
		
		assertEquals(0, middle.getQ());
		assertEquals(0, middle.getR());
	}
	

	@ParameterizedTest
    @CsvSource({
        "1, 1",
        "2, 7",
        "3, 19",
        "4, 37",
    })
	void getAllHexagons_variousSizes_returnsAll(int radius, int expectedNumberOfHexagons) {
		HexagonGridCircular hexagonGrid = new HexagonGridCircular(radius);
		
		Collection<Hexagon> all = hexagonGrid.getAllHexagons();
		
		assertEquals(expectedNumberOfHexagons, all.size());
	}
}
