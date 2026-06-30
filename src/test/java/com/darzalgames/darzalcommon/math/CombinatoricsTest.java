package com.darzalgames.darzalcommon.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CombinatoricsTest {

	@Test
	void numberOfPermutations_withInvalidInputs_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> Combinatorics.numberOfPermutations(5, -1));
		assertThrows(IllegalArgumentException.class, () -> Combinatorics.numberOfPermutations(1, 3));
		assertThrows(IllegalArgumentException.class, () -> Combinatorics.numberOfPermutations(3, 5));
	}

	@Test
	void numberOfPermutations_with0InAnyInput_returns1() {
		assertEquals(1, Combinatorics.numberOfPermutations(0, 0));
		assertEquals(1, Combinatorics.numberOfPermutations(5, 0));
	}

	@ParameterizedTest
	@CsvSource({ "3,1,3", "3,2,6", "4,4,24", "10,5,30240", "200,4,1552438800" })
	void numberOfPermutations_withVariousInputs_computesNumberOfPermutations(int n, int k, int expectedPermutations) {
		int result = Combinatorics.numberOfPermutations(n, k);
		assertEquals(expectedPermutations, result);
	}

	@Test
	void numberOfPermutations_withOverflowCausingInput_throwsArithmeticException() {
		assertThrows(ArithmeticException.class, () -> Combinatorics.numberOfPermutations(200, 5));
	}

	@Test
	void numberOfCombinations_withInvalidInputs_throwsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> Combinatorics.numberOfCombinations(7, -2));
		assertThrows(IllegalArgumentException.class, () -> Combinatorics.numberOfCombinations(-3, 3));
		assertThrows(IllegalArgumentException.class, () -> Combinatorics.numberOfCombinations(4, 6));
	}

	@Test
	void numberOfCombinations_with0InAnyInput_returns1() {
		assertEquals(1, Combinatorics.numberOfCombinations(0, 0));
		assertEquals(1, Combinatorics.numberOfCombinations(5, 0));
	}

	@ParameterizedTest
	@CsvSource({ "3,1,3", "3,2,3", "4,4,1", "10,5,252", "200,4,64684950", "200,196,64684950" })
	void numberOfCombinations_withVariousInputs_computesNumberOfCombinations(int n, int k, int expectedCombinations) {
		int result = Combinatorics.numberOfCombinations(n, k);
		assertEquals(expectedCombinations, result);
	}

	@Test
	void numberOfCombinations_withOverflowCausingInput_throwsArithmeticException() {
		assertThrows(ArithmeticException.class, () -> Combinatorics.numberOfCombinations(200, 5));
	}

	@Test
	void getAllPermutations_onListOfThree_createsAll6PossiblePermutations() {
		List<Integer> test = new ArrayList<>();
		test.add(1);
		test.add(2);
		test.add(3);

		List<List<Integer>> permutations = Combinatorics.getAllPermutations(test);

		assertEquals(6, permutations.size());
		assertEquals(permutations.size(), permutations.stream().distinct().count());

		assertTrue(permutations.contains(List.of(1, 2, 3)));
		assertTrue(permutations.contains(List.of(1, 3, 2)));
		assertTrue(permutations.contains(List.of(2, 1, 3)));
		assertTrue(permutations.contains(List.of(2, 3, 1)));
		assertTrue(permutations.contains(List.of(3, 2, 1)));
		assertTrue(permutations.contains(List.of(3, 1, 2)));
	}

	@Test
	void getAllPermutations_onListOfThreeWithDuplicate_hasCorrectValues() {
		List<Integer> test = new ArrayList<>();
		test.add(1);
		test.add(3);
		test.add(3);

		List<List<Integer>> permutations = Combinatorics.getAllPermutations(test);

		assertEquals(6, permutations.size());

		assertTrue(permutations.contains(List.of(1, 3, 3)));
		assertTrue(permutations.contains(List.of(1, 3, 3)));
		assertTrue(permutations.contains(List.of(3, 1, 3)));
		assertTrue(permutations.contains(List.of(3, 3, 1)));
		assertTrue(permutations.contains(List.of(3, 3, 1)));
		assertTrue(permutations.contains(List.of(3, 1, 3)));

	}

}
