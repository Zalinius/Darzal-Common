package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.darzalgames.darzalcommon.data.Tuple;

class HexagonMathTest {
	
	@ParameterizedTest
    @CsvSource({
        "0, 0,		0, 0",
        "0, 1,		0, 7",
        "1, 0,		6, 3.5",
        "1, 1,		6, 10.5",
    })
	void getScreenPosition_variousCoordinatesAndPixelRatio_returnsExpectedPosition(int hexagonQ, int hexagonR, float expectedX, float expectedY) {
		
		Tuple<Float, Float> position = HexagonMath.getScreenPosition(8, 7, hexagonQ, hexagonR, 8, 7);
		
		assertEquals(expectedX, position.e);
		assertEquals(expectedY, position.f);
	}

	@ParameterizedTest
    @CsvSource({
        "0, 0,		0, 0",
        "0, 1,		0, 0.8660254",
        "1, 0,		1.125, 0.4330127",
        "1, 1,		1.125, 1.2990381",
    })
	void getScreenPosition_variousCoordinatesAndPerfectRatio_returnsExpectedPosition(int hexagonQ, int hexagonR, float expectedX, float expectedY) {
		float width = 3f/2;
		float height = (float) (Math.sqrt(3)/2f);
		
		Tuple<Float, Float> position = HexagonMath.getScreenPosition(width, height, hexagonQ, hexagonR, width, height);
		
		assertEquals(expectedX, position.e);
		assertEquals(expectedY, position.f);
	}
	
	
	@ParameterizedTest
    @CsvSource({
        "0, 0,		0, 193,		200",
        "0, 1,		0, 386,		400",
        "1, 0,		6, 489.5, 	500",
        "1, 1,		6, 732.5, 	750",
    })
	void getScreenPositionOnStage_variousCoordinatesAndPixelRatio_returnsExpectedPosition(int hexagonQ, int hexagonR, float expectedX, float expectedY, int stageHeight) {
		
		Tuple<Float, Float> position = HexagonMath.getScreenPositionOnStage(8, 7, hexagonQ, hexagonR, 8, 7, stageHeight);
		
		assertEquals(expectedX, position.e);
		assertEquals(expectedY, position.f);
	}

}
