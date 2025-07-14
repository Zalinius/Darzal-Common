package com.darzalgames.darzalcommon.data;

import java.util.Objects;

/**
 * A class representing an integer Cartesian Coordinate.
 * Instances of this class are immutable
 */
public class Coordinate {

	/**
	 * The i (or horizontal) coordinate
	 */
	public final int i;
	/**
	 * The j (or vertical) coordinate
	 */
	public final int j;

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
		this(tuple.e, tuple.f);
	}

	/**
	 * Creates a coordinate centered at i,j
	 * @param i The i (or horizontal) coordinate
	 * @param j The j (or vertical) coordinate
	 */
	public Coordinate(int i, int j) {
		this.i = i;
		this.j = j;
	}

	/**
	 * @return The i (or horizontal) coordinate
	 */
	public int getI() {
		return i;
	}

	/**
	 * @return The j (or vertical) coordinate
	 */
	public int getJ() {
		return j;
	}

	/**
	 * @return A tuple of integers corresponding to this coordinate.
	 */
	public Tuple<Integer, Integer> toTuple(){
		return new Tuple<>(i, j);
	}

	@Override
	public String toString() {
		return "Coordinate [i=" + i + ", j=" + j + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(i, j);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		return i == other.i && j == other.j;
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
