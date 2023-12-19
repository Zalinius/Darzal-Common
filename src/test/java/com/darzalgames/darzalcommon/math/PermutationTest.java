package com.darzalgames.darzalcommon.math;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class PermutationTest {
	@Test
	void listOfThree_permuted_hasCorrectValues() {
		List<Integer> test = new ArrayList<>();
		test.add(1);
		test.add(2);
		test.add(3);

		List<List<Integer>> permutations = Permutation.generatePerm(test);

		assertEquals(6, permutations.size());
		assertEquals((long)permutations.size(), permutations.stream().distinct().count());

		assertTrue(permutations.contains(List.of(1, 2, 3)));
		assertTrue(permutations.contains(List.of(1, 3, 2)));
		assertTrue(permutations.contains(List.of(2, 1, 3)));
		assertTrue(permutations.contains(List.of(2, 3, 1)));
		assertTrue(permutations.contains(List.of(3, 2, 1)));
		assertTrue(permutations.contains(List.of(3, 1, 2)));
		
	}
	@Test
	void listOfThreeWithDuplicate_permuted_hasCorrectValues() {
		List<Integer> test = new ArrayList<>();
		test.add(1);
		test.add(3);
		test.add(3);

		List<List<Integer>> permutations = Permutation.generatePerm(test);

		assertEquals(6, permutations.size());

		assertTrue(permutations.contains(List.of(1, 3, 3)));
		assertTrue(permutations.contains(List.of(1, 3, 3)));
		assertTrue(permutations.contains(List.of(3, 1, 3)));
		assertTrue(permutations.contains(List.of(3, 3, 1)));
		assertTrue(permutations.contains(List.of(3, 3, 1)));
		assertTrue(permutations.contains(List.of(3, 1, 3)));
		
	}
}
