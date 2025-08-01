package com.darzalgames.darzalcommon.random;

import java.util.*;

import com.darzalgames.darzalcommon.functional.Do;


/**
 * An extension to the Java Random class with some convenience functions for GameDev.
 * Can give you angles and subsets and such
 */
public class RandomExtended extends Random{

	/**
	 * All internal state is capture by the parent class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a RandomExtended with a random seed
	 */
	public RandomExtended() {
		super();
	}

	/**
	 * Creates a RandomExtended with a the specified long seed
	 * @param seed the initial long seed
	 */
	public RandomExtended(long seed) {
		super(seed);
	}

	/**
	 * Returns a boolean simulating a coin flip
	 * @return a boolean with 50/50 odds of being true or false
	 */
	public boolean nextCoinFlip() {
		return nextBoolean();
	}

	/**
	 * Returns a random boolean, who's chance of being true is the fractional odds passed in,
	 * @param numerator the weight to attribute to the chance of returning true
	 * @param denominator the total weight of the odds
	 * @return Returns true or false randomly, based on the odds passed in
	 */
	public boolean nextChance(int numerator, int denominator) {
		if(numerator < 0) {
			throw new IllegalArgumentException("Numerator must not be negative:" + numerator);
		}
		if(denominator <= 0) {
			throw new IllegalArgumentException("Denominator must be positive:" + denominator);
		}
		int targetToMeetOrBeat = numerator-1;
		return targetToMeetOrBeat >= nextInt(denominator);
	}

	/**
	 * Gets a random unit-circle angle in radians
	 * @return a random double radian between 0.0(inclusive) and 2pi(exclusive)
	 */
	public double nextRadian() {
		return 2.0 * Math.PI * super.nextDouble();
	}

	/**
	 * Gets a random unit cirle angle in degrees
	 * @return a random double degree between 0.0(inclusive) and 360.0(exclusive)
	 */
	public double nextDegree() {
		return 360.0 * super.nextDouble();
	}

	/**
	 * Gets a random unit-circle angle in radians
	 * @return a random float radian between 0.0f(inclusive) and 2pi(exclusive)
	 */
	public float nextRadianF() {
		return 2.0f * (float)Math.PI * super.nextFloat();
	}

	/**
	 * Gets a random unit cirle angle in degrees
	 * @return a random float degree between 0.0f(inclusive) and 360.0f(exclusive)
	 */
	public float nextDegreeF() {
		return 360.0f * super.nextFloat();
	}

	/**
	 * Returns a random element from a collection
	 * @param <E> The generic type of the collection
	 * @param collection The input collection. It is not modified by this function call
	 * @return A random value taken from the input collection
	 * @throws IllegalArgumentException if the collection is empty
	 */
	public <E> E getRandomElement(Collection<E> collection) {
		if(collection.isEmpty()) {
			throw new IllegalArgumentException("Collection must not be empty");
		}

		List<E> collectionCopy = new ArrayList<>(collection);
		return collectionCopy.get(nextInt(collectionCopy.size()));
	}

	/**
	 * Returns a random subset
	 * @param <E> The generic type of the subset
	 * @param set The input set. It is not modified by this function call
	 * @param subsetSize The number of elements desired in the subset. Must be between 0 and the original size, inclusive.
	 * @return A subset, which is modifiable
	 */
	public <E> Set<E> getNextRandomSubset(final Set<E> set, final int subsetSize){
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
