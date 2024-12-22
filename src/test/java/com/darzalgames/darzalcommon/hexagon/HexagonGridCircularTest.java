package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.darzalgames.darzalcommon.hexagon.gridfactory.HexagonGridCircular;

public class HexagonGridCircularTest {

	@Test
	void makeGrid_0Radius_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> HexagonGridCircular.makeGrid(0));
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
		HexagonMap<String> hexagonMap = new HexagonMap<>();
		HexagonGridCircular.makeGrid(radius).forEach(hex -> hexagonMap.add(hex, ""));
		String middleTag = "middle!";
		hexagonMap.add(new Hexagon(0,0), middleTag);
		
		String middle = hexagonMap.getMiddleHexagonValue();
		
		assertEquals(middleTag, middle);
	}
	

	@ParameterizedTest
    @CsvSource({
        "1, 1",
        "2, 7",
        "3, 19",
        "4, 37",
    })
	void getAllHexagons_variousSizes_returnsAll(int radius, int expectedNumberOfHexagons) {
		List<Hexagon> hexagons = HexagonGridCircular.makeGrid(radius);
		
		assertEquals(expectedNumberOfHexagons, hexagons.size());
	}
}
