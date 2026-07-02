package com.darzalgames.darzalcommon.math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * a class with Combinatorics-related funtions
 */
public class Combinatorics {

	private Combinatorics() {}

	/**
	 * Computes n permute k, which is the number of unique ordered k-sized subsets made from a set of n elements
	 * @param n The total number of elements
	 * @param k The number of elements in a subset
	 * @return the number of unique ordered subsets
	 * @throws ArithmeticException if the result would cause an integer overflow
	 */
	public static int numberOfPermutations(int n, int k) {
		if (n < 0) {
			throw new IllegalArgumentException("n must be non-negative: " + n);
		} else if (k < 0) {
			throw new IllegalArgumentException("k must be non-negative: " + k);
		} else if (n < k) {
			throw new IllegalArgumentException("n must be greater than or equal to k: n=" + n + ", k=" + k);
		} else {
			int result = 1;
			for (int multiplier = n; multiplier > (n - k); multiplier--) {
				result = Math.multiplyExact(result, multiplier);
			}
			return result;
		}
	}

	/**
	 * Computes n choose k, which is the number of unique unordered k-sized subsets made from a set of n elements
	 * @param n The total number of elements
	 * @param k The number of elements in a subset
	 * @return the number of unique subsets
	 * @throws ArithmeticException if the result would cause an integer overflow
	 */
	public static int numberOfCombinations(int n, int k) {
		if (n < 0) {
			throw new IllegalArgumentException("n must be non-negative: " + n);
		} else if (k < 0) {
			throw new IllegalArgumentException("k must be non-negative: " + k);
		} else if (n < k) {
			throw new IllegalArgumentException("n must be greater than or equal to k: n=" + n + ", k=" + k);
		} else {
			int result = 1;
			int factorialDivisor1 = k;
			int factorialDivisor2 = n - k;
			if (factorialDivisor2 > factorialDivisor1) {
				int temp = factorialDivisor1;
				factorialDivisor1 = factorialDivisor2;
				factorialDivisor2 = temp;
			}

			for (int multiplier = n; multiplier > factorialDivisor1; multiplier--) {
				result = Math.multiplyExact(result, multiplier);
			}
			for (int divisor = 1; divisor <= factorialDivisor2; divisor++) {
				result = Math.divideExact(result, divisor);
			}
			return result;
		}
	}

	/**
	 * Generate all the possible ordered permutations of a collection recursively <br>
	 * @param <E>      The generic type of the list
	 * @param elements the elements to permute
	 * @return A list of lists, containing all possible permutations
	 */
	// thanks https://stackoverflow.com/questions/10305153/generating-all-possible-permutations-of-a-list-recursively
	public static <E> List<List<E>> getAllPermutations(Collection<E> elements) {
		List<E> copy = new ArrayList<>(elements);
		return getAllPermutationsRecursiveCall(copy);
	}

	private static <E> List<List<E>> getAllPermutationsRecursiveCall(List<E> elements) {
		if (elements.isEmpty()) {
			List<List<E>> result = new ArrayList<>();
			result.add(new ArrayList<>());
			return result;
		}
		E firstElement = elements.remove(0);
		List<List<E>> returnValue = new ArrayList<>();
		List<List<E>> permutations = getAllPermutationsRecursiveCall(elements);
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
