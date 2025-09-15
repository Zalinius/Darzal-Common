package com.darzalgames.darzalcommon.math;

import java.util.ArrayList;
import java.util.List;

// thanks https://stackoverflow.com/questions/10305153/generating-all-possible-permutations-of-a-list-recursively
/**
 * a class with permutation-related funtions
 */
public class Permutation {

	private Permutation() {}

	/**
	 * Generate all the possible permutations of a list recursively <br>
	 * This function will empty the original list
	 * @param <E>      The generic type of the list
	 * @param original the original list
	 * @return A list of lists, containing all possible permutations
	 */
	public static <E> List<List<E>> generatePerm(List<E> original) {
		if (original.isEmpty()) {
			List<List<E>> result = new ArrayList<>();
			result.add(new ArrayList<>());
			return result;
		}
		E firstElement = original.remove(0);
		List<List<E>> returnValue = new ArrayList<>();
		List<List<E>> permutations = generatePerm(original);
		for (List<E> smallerPermutated : permutations) {
			for (int index = 0; index <= smallerPermutated.size(); index++) {
				List<E> temp = new ArrayList<>(smallerPermutated);
				temp.add(index, firstElement);
				returnValue.add(temp);
			}
		}
		return returnValue;
	}
}
