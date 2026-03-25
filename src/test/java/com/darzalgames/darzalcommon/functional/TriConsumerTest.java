package com.darzalgames.darzalcommon.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TriConsumerTest {

	@Test
	void apply_usesArguments() {
		TriConsumer<Boolean, Character, TestStringClass> concatenator = (b, c, s) -> {
			s.append(c, b);
		};
		TestStringClass testString = new TestStringClass();

		concatenator.accept(true, 'M', testString);

		assertEquals("testMtrue", testString.stringy);
	}

	@Test
	void andThen_sequentiallyExecutesFunctionExecution() {
		TriConsumer<Boolean, Character, TestStringClass> concatenator = (b, c, s) -> {
			s.append(c, b);
		};
		TriConsumer<Boolean, Character, TestStringClass> concatenator2 = (b, c, s) -> {
			s.append(c, b);
		};

		TriConsumer<Boolean, Character, TestStringClass> doubleConcatenator = concatenator.andThen(concatenator2);
		TestStringClass testString = new TestStringClass();

		doubleConcatenator.accept(true, 'M', testString);

		assertEquals("testMtrueMtrue", testString.stringy);
	}

	private class TestStringClass {
		private String stringy;

		public TestStringClass() {
			stringy = "test";
		}

		private void append(Character c, Boolean b) {
			stringy = stringy.concat(c.toString()).concat(b.toString());
		}

	}

}
