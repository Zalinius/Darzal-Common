package com.darzalgames.darzalcommon.hexagon;

import com.darzalgames.darzalcommon.data.Coordinate;

/**
 * A simple class representing a flat-top hexagon using axial coordinates
 * @author DarZal
 */
public class Hexagon {

	private final Coordinate coordinate;

	/**
	 * Creates a {@link Hexagon} at the given coordinates
	 * @param q Q-axis coordinate (axial system)
	 * @param r R-axis coordinate (axial system)
	 */
	public Hexagon(int q, int r) {
		this(new Coordinate(q, r));
	}
	/**
	 * Creates a {@link Hexagon} at the given coordinates
	 * @param coordinate An axial system coordinate (first value is the Q-axis and second value is the R-axis)
	 */
	public Hexagon(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * @return The axial Q coordinate of the {@link Hexagon} (logical coordinate)
	 */
	public int getQ() {
		return coordinate.i;
	}

	/**
	 * @return The axial R coordinate of the {@link Hexagon} (logical coordinate)
	 */
	public int getR() {
		return coordinate.j;
	}

	/**
	 * @return The axial S coordinate of the {@link Hexagon} (logical coordinate), calculated from Q and R 
	 */
	public int getS() {
		// https://www.redblobgames.com/grids/hexagons/#coordinates
		// Axial coordinates are defined as such: "Since we have a constraint q + r + s = 0, we can calculate s = -q-r when we need it."
		return -getQ() - getR();
	}

	@Override
	public boolean equals(Object obj) {
		// self check
		if (this == obj)
			return true;
		// null check
		if (obj == null)
			return false;
		// type check and cast
		if (!(obj instanceof Hexagon))
			return false;
		Hexagon otherHexagon = (Hexagon) obj;

		// field comparison
		return otherHexagon.coordinate.equals(this.coordinate);
	}

	@Override
	public int hashCode() {
		return coordinate.hashCode();
	}

}
