package com.darzalgames.darzalcommon.random;

import java.util.Iterator;

public interface IntegerSequence extends Iterator<Integer> {
	@Override
	Integer next();
	
	/**
	 * @param upperBound An exclusive upper bound for what can be returned
	 * @return a random integer between 0 and upperBound (exclusive)
	 */
	Integer next(int upperBound);

	/**
	 * @param lowerBound An inclusive lower bound for what can be returned
	 * @param upperBound An exclusive upper bound for what can be returned
	 * @return a random integer between lowerBound(inclusive) and upperBound(exclusive)
	 */
	Integer next(int lowerBound, int upperBound);
	
	@Override
	boolean hasNext();
}
