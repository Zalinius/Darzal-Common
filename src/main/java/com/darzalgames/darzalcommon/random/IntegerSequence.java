package com.darzalgames.darzalcommon.random;

import java.util.Iterator;

public interface IntegerSequence extends Iterator<Integer> {
	@Override
	Integer next();
	/**
	 * Returns a pseudorandom int between 0 and upperBound(exclusive)
	 */
	Integer next(int upperBound);
	/**
	 * Returns a pseudorandom int between lowerBound(inclusive) and upperBound(exclusive)
	 */
	Integer next(int lowerBound, int upperBound);
	
	int nextInt();
	int nextInt(int upperBound);
	/**
	 * 
	 * Returns a pseudorandom int between lowerBound(inclusive) and upperBound(exclusive)
	 */
	/**
	 * 
	 * @param lowerBound
	 * @param upperBound
	 * @return a pseudorandom int between lowerBound(inclusive) and upperBound(exclusive)
	 */
	int nextInt(int lowerBound, int upperBound);
	
	
	@Override
	boolean hasNext();
}
