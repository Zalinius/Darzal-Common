package com.darzalgames.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

class TriFunctionTest {

	@Test
	void apply_usesArgumentsAndExecutesFunction() {
		TriFunction<Boolean, Character, Integer, String> concatenator = (b, c, i) -> b + "" + c + "" + i;

		assertEquals("trueM10", concatenator.apply(true, 'M', 10));
	}

	@Test
	void andThen_sequentiallyExecutesFunctionExecution() {
		TriFunction<Boolean, Character, Integer, String> concatenator = (b, c, i) -> b + "" + c + "" + i;
		Function<String, Integer> lengthGetter = String::length;

		TriFunction<Boolean, Character, Integer, Integer> concatenatorLengthGetter = concatenator.andThen(lengthGetter);

		assertEquals(7, concatenatorLengthGetter.apply(true, 'M', 10));
	}

}
