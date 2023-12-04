package com.darzalgames.darzalcommon.data;

import java.util.Objects;

public class Coord {
	
	/**
	 * The i (or horizontal) coordinate
	 */
	public final int i;
	/**
	 * The j (or vertical) coordinate
	 */
	public final int j;
	
	/**
	 * Creates a coord centered at the origin
	 */
	public Coord() {
		this(0,0);
	}

	/**
	 * Creates a coord from an integer tuple
	 * @param tuple a non-null tuple of non-null integers
	 */
	public Coord(Tuple<Integer, Integer> tuple) {
		this(tuple.e, tuple.f);
	}

	/**
	 * Creates a coord centered at i,j
	 * @param i The i (or horizontal) coordinate
	 * @param j The j (or vertical) coordinate
	 */
	public Coord(int i, int j) {
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
	 * @return A tuple of integers corresponding to this coord.
	 */
	public Tuple<Integer, Integer> toTuple(){
		return new Tuple<>(i, j);
	}

	@Override
	public String toString() {
		return "Coord [i=" + i + ", j=" + j + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(i, j);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		return i == other.i && j == other.j;
	}
	
	/**
	 * Returns the taxi distance between this coord and another.
	 * The taxi distance is the shortest path distance between two coords, using only vertical and horizontal moves.
	 * @param coord The other coordinate
	 * @return The taxi distance, a non negative integer. 
	 */
	public int taxiDistance(Coord coord) {
		return Math.abs(i - coord.i) + Math.abs(j - coord.j);
	}
	
	/**
	 * Returns the king distance between this coord and another.
	 * The king distance is the shortest path distance between two coords, where diagonal crossings are allowed.
	 * @param coord The other coordinate
	 * @return The king distance, a non negative integer. 
	 */
	public int kingDistance(Coord coord) {
		return Math.max(Math.abs(i - coord.i), Math.abs(j - coord.j));
	}
	

}
