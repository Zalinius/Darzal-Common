package com.zalinius.darzalcommon.random;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.zalinius.darzalcommon.functional.Do;

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
	
	/**
	 * Returns a random boolean, who's chance of being true is the fractional odds passed in, 
	 * @param numerator
	 * @param denominator
	 * @return Returns true or false randomly, based on the odds passed in
	 */
	public boolean nextChance(int numerator, int denominator) {
		if(numerator < 0) {
			throw new IllegalArgumentException("Numerator must not be negative:" + numerator);
		}
		if(denominator < 0) {
			throw new IllegalArgumentException("Denominator must not be negative:" + denominator);
		}
		
		if(numerator >= denominator) {
			return true;
		}
		else if(numerator <= 0) {
			return false;
		}
		else {
			int targetToMeetOrBeat = numerator-1;
			return targetToMeetOrBeat >= nextInt(denominator);
		}
	}
	
	public int nextInt(int lowerBound, int upperBound) {
		if (lowerBound >= upperBound) {
			throw new IllegalArgumentException("Lower bound must be strictly lower than upper bound: " + lowerBound +", "+ upperBound);
		}
		int range = upperBound - lowerBound;
		return super.nextInt(range) + lowerBound;
	}
	
	public double nextRadian() {
		return 2.0 * Math.PI * super.nextDouble();
	}

	public double nextDegree() {
		return 360.0 * super.nextDouble();
	}
	
	public float nextRadianF() {
		return 2.0f * (float)Math.PI * super.nextFloat();
	}

	public float nextDegreeF() {
		return 360.0f * super.nextFloat();
	}
	
	public <E> Set<E> getRandomSubset(final Set<E> set, final int subsetSize){
		if(subsetSize < 0) {
			throw new IllegalArgumentException("Subset size must not be negative: " + subsetSize);
		}
		if(subsetSize > set.size()) {
			throw new IllegalArgumentException("Subset size(" + subsetSize + ") must not be greater than original set size(" + set.size() + ")");
		}

		Set<E> setToPickFrom = new HashSet<>(set);
		Set<E> randomSubset = new HashSet<>();

		Do.xTimes(subsetSize, () -> {
			int targetToPick = nextInt(setToPickFrom.size());
			E toRemove = null;
			int i = 0;
			for (E e : setToPickFrom) {
				if(i == targetToPick) {
					toRemove = e;
				}
				++i;
			}
			
			randomSubset.add(toRemove);
			setToPickFrom.remove(toRemove);
		});
		
		return randomSubset;		
	}
	
}
