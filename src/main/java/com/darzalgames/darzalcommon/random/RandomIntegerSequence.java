package com.darzalgames.darzalcommon.random;

import java.util.Random;

public class RandomIntegerSequence implements IntegerSequence {

	private Random random;
	
	public RandomIntegerSequence() {
		this.random = new Random();
	}
	
	public RandomIntegerSequence(long seed) {
		this.random = new Random(seed);
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
		double sample = random.nextDouble();
		return (int) (lowerBound + (upperBound - lowerBound) * sample);
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	@Override
	public int nextInt() {
		return next();
	}

	@Override
	public int nextInt(int upperBound) {
		return next(upperBound);
	}

	@Override
	public int nextInt(int lowerBound, int upperBound) {
		return next(lowerBound, upperBound);
	}

	@Override
	public boolean flipCoin() {
		return random.nextInt(2) == 0;
	}
}
