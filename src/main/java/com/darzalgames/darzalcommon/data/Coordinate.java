package com.darzalgames.darzalcommon.data;

/**
 * A class representing an integer Cartesian Coordinate.
 * Instances of this class are immutable
 * @param i the i coordinate
 * @param j the j coordinate
 */
public record Coordinate(
		/** The i (or horizontal) coordinate */
		int i,
		/** The j (or vertical) coordinate */
		int j) {

	/**
	 * Creates a coordinate centered at the origin
	 */
	public Coordinate() {
		this(0,0);
	}

	/**
	 * Creates a coordinate from an integer tuple
	 * @param tuple a non-null tuple of non-null integers
	 */
	public Coordinate(Tuple<Integer, Integer> tuple) {
		this(tuple.e(), tuple.f());
	}

	/**
	 * Gets this Coordinate as an Integer Tuple
	 * @return A tuple of integers corresponding to this coordinate.
	 */
	public Tuple<Integer, Integer> toTuple(){
		return new Tuple<>(i, j);
	}

	/**
	 * Returns the taxi distance between this coordinate and another.
	 * The taxi distance is the shortest path distance between two coordinates, using only vertical and horizontal moves.
	 * @param coordinate The other coordinate
	 * @return The taxi distance, a non negative integer.
	 */
	public int taxiDistance(Coordinate coordinate) {
		return Math.abs(i - coordinate.i) + Math.abs(j - coordinate.j);
	}

	/**
	 * Returns the king distance between this coordinate and another.
	 * The king distance is the shortest path distance between two coordinates, where diagonal crossings are allowed.
	 * @param coordinate The other coordinate
	 * @return The king distance, a non negative integer.
	 */
	public int kingDistance(Coordinate coordinate) {
		return Math.max(Math.abs(i - coordinate.i), Math.abs(j - coordinate.j));
	}

}
