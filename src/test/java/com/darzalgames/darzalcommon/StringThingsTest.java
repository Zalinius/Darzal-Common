package com.darzalgames.darzalcommon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.darzalgames.darzalcommon.string.StringThings;

class StringThingsTest {

	@ParameterizedTest
	@CsvSource(value = {
			"How Now Brown Cow,How Now Brown Cow",
			"HOW NOW BROWN COW,How Now Brown Cow",
			"how now brown cow,How Now Brown Cow",
			"HoW nOw BrOwN cOw,How Now Brown Cow",
			"CURRICULUM VITÆ,Curriculum Vitæ",
			"élève GARÇON TÊTU,Élève Garçon Têtu",
			"ジェネレーションＹ,ジェネレーションｙ" // This is a wide Y
	})
	void toTitleCase_returnsStringInTitleCase(String input, String expected) {
		String titleCase = StringThings.toTitleCase(input);

		assertEquals(expected, titleCase);
	}

	@Test
	void shuffleCharacters_shufflesCharactersOfAString() {
		String shuffledString = StringThings.shuffleCharacters("abc123", new Random(0));

		assertNotEquals("abc123", shuffledString);
		assertEquals(6, shuffledString.length());
		assertTrue(shuffledString.contains("a"));
		assertTrue(shuffledString.contains("a"));
		assertTrue(shuffledString.contains("a"));
		assertTrue(shuffledString.contains("1"));
		assertTrue(shuffledString.contains("2"));
		assertTrue(shuffledString.contains("3"));
	}

}
