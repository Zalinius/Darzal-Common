package com.darzalgames.darzalcommon.strings;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringUtilsTest {
	
	@ParameterizedTest
	@ValueSource(strings = {"", " ", "\t", "\r", "\n", "  \t\n\r "})
	void isBlank_onBlankStrings_areBlank(String input) throws Exception {
		boolean isBlank = StringUtils.isBlank(input);
		
		assertTrue(isBlank);
	}

	@ParameterizedTest
	@ValueSource(strings = {"a", " a", "\tb", "\rc", "\nd", "  \t\nf\r ", "\\n"})
	void isBlank_onNonBlankStrings_areNotBlank(String input) throws Exception {
		boolean isBlank = StringUtils.isBlank(input);
		
		assertFalse(isBlank);
	}

}
