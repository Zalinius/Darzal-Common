package com.darzalgames.darzalcommon.hexagon;

import java.util.Comparator;
import java.util.Objects;

/**
 * A simple class representing a flat-top hexagon using axial coordinates
 * @author DarZal
 */
public class Hexagon {

	private final int q;
	private final int r;

	/**
	 * Creates a {@link Hexagon} at the given coordinates
	 * @param q Q-axis coordinate (axial system)
	 * @param r R-axis coordinate (axial system)
	 */
	public Hexagon(int q, int r) {
		this.q = q;
		this.r = r;
	}

	/**
	 * @return The axial Q coordinate of the {@link Hexagon} (logical coordinate)
	 */
	public int getQ() {
		return q;
	}

	/**
	 * @return The axial R coordinate of the {@link Hexagon} (logical coordinate)
	 */
	public int getR() {
		return r;
	}

	/**
	 * @return The axial S coordinate of the {@link Hexagon} (logical coordinate), calculated from Q and R
	 */
	public int getS() {
		// https://www.redblobgames.com/grids/hexagons/#coordinates
		// Axial coordinates are defined as such: "Since we have a constraint q + r + s = 0, we can calculate s = -q-r when we need it."
		return -getQ() - getR();
	}

	/**
	 * @return The offset column coordinate of the {@link Hexagon}
	 */
	public int getColumn() {
		return getQ();
	}

	/**
	 * @return The offset row coordinate of the {@link Hexagon}
	 */
	public int getRow() {
		return r + (q - (q&1)) / 2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(q, r);
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
		Hexagon other = (Hexagon) obj;
		return q == other.q && r == other.r;
	}

	@Override
	public String toString() {
		return "Hexagon [q=" + q + ", r=" + r + ", s=" + getS() + "]";
	}

	/**
	 * @param row offset coordinates row number
	 * @param column offset coordinates column number
	 * @return a @Hexagon built from the provided coordinates, compatible with all coordinate systems
	 */
	public static Hexagon makeHexagonFromOffsetCoordinates(int row, int column) {
		int q = column;
		int r = row - (column - (column&1)) / 2;
		return new Hexagon(q, r);
	}


	public static final Comparator<Hexagon> topToBottomLeftToRightComparator = (hexagon1, hexagon2) -> {
		int firstComparison = Float.compare(hexagon1.getColumn(), hexagon2.getColumn());
		if (firstComparison != 0) {
			return firstComparison;
		}
		return Float.compare(hexagon1.getRow(), hexagon2.getRow());
	};

}
