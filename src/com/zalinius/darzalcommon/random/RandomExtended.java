package com.zalinius.darzalcommon.random;

import java.util.Random;

public class RandomExtended extends Random{
	
	/**
	 * All internal state is capture by the parent class
	 */
	private static final long serialVersionUID = 1L;

	public RandomExtended() {
		super();
	}
	
	public RandomExtended(long seed) {
		super(seed);
	}
	
	public int nextInt(int lowerBound, int upperBound) {
		if (lowerBound >= upperBound) {
			throw new IllegalArgumentException("Lower bound must be strictly lower than upper bound: " + lowerBound +", "+ upperBound);
		}
		int range = upperBound - lowerBound;
		return super.nextInt(range) + lowerBound;
	}
	
}
