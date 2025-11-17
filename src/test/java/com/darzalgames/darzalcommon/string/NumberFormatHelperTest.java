package com.darzalgames.darzalcommon.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NumberFormatHelperTest {

	@ParameterizedTest
	@CsvSource({ "0.0f , 0%", "1.0f , 100%", "0.499f , 50%", "0.123f , 12%" })
	void formatAsIntegerPercentage(float percentage, String expectedResult) {
		String formattedResult = NumberFormatHelper.formatAsIntegerPercentage(percentage);

		assertEquals(expectedResult, formattedResult);
	}

}
