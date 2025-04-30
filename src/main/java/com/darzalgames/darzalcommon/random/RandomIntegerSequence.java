package com.darzalgames.darzalcommon.random;

import java.util.Random;

/**
 * An infinite integer sequence which returns random integers.
 */
public class RandomIntegerSequence implements IntegerSequence {

	private final Random random;

	public RandomIntegerSequence() {
		random = new Random();
	}

	public RandomIntegerSequence(long seed) {
		random = new Random(seed);
	}

	@Override
	public Integer next() {
		return random.nextInt();
	}

	@Override
	public Integer next(int upperBound) {
		return random.nextInt(upperBound);
	}

	@Override
	public Integer next(int lowerBound, int upperBound) {
		if(lowerBound > upperBound) {
			throw new ArithmeticException("lowerBound must be less or equal than upper bound!");
		}
		return random.nextInt(upperBound - lowerBound) + lowerBound;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

}
