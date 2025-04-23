package com.darzalgames.darzalcommon.random;

public class LinearNumberSequence implements IntegerSequence {
	
	private int number;
	
	public LinearNumberSequence() {
		this(0);
	}
	public LinearNumberSequence(int startNumber) {
		this.number = startNumber;
	}

	@Override
	public Integer next() {
		return nextInt();
	}

	@Override
	public Integer next(int upperBound) {
		return next();
	}

	@Override
	public Integer next(int lowerBound, int upperBound) {
		return next();
	}

	@Override
	public int nextInt() {
		int toReturn = number;
		number++;
		return toReturn;
	}

	@Override
	public int nextInt(int upperBound) {
		return nextInt();
	}

	@Override
	public int nextInt(int lowerBound, int upperBound) {
		return nextInt();
	}

	@Override
	public boolean hasNext() {
		return true;
	}
	
}
