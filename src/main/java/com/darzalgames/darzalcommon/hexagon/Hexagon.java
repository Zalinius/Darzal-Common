package com.darzalgames.darzalcommon.hexagon;

import java.util.Comparator;

/**
 * A simple class representing a flat-top hexagon using axial coordinates
 * Instances of this class are immutable
 * @param q Q-axis coordinate (axial system)
 * @param r R-axis coordinate (axial system)
 */
public record Hexagon(
		/** The Q-axis coordinate (axial system) */
		int q, /** The R-axis coordinate (axial system) */
		int r) {

	public static final Hexagon ORIGIN = new Hexagon(0, 0);

	/**
	 * Get the S coordinate
	 * @return The axial S coordinate of the {@link Hexagon} (logical coordinate), calculated from Q and R
	 */
	public int s() {
		return -q() - r();
	}

	/**
	 * Get the column
	 * @return The offset column coordinate of the {@link Hexagon}
	 */
	public int getColumn() {
		return q();
	}

	/**
	 * Get the row
	 * @return The offset row coordinate of the {@link Hexagon}
	 */
	public int getRow() {
		return r() + (q() - (q() & 1)) / 2;
	}

	/**
	 * Creates a hexagon from a row and column
	 * @param row    offset coordinates row number
	 * @param column offset coordinates column number
	 * @return a {@link Hexagon} built from the provided coordinates, compatible with all coordinate systems
	 */
	public static Hexagon makeHexagonFromOffsetCoordinates(int row, int column) {
		int q = column;
		int r = row - (column - (column & 1)) / 2;
		return new Hexagon(q, r);
	}

	/**
	 * A comparator that orders hexagons top to bottom, and then left to right
	 */
	public static final Comparator<Hexagon> topToBottomLeftToRightComparator = (hexagon1, hexagon2) -> {
		int firstComparison = Float.compare(hexagon1.getColumn(), hexagon2.getColumn());
		if (firstComparison != 0) {
			return firstComparison;
		}
		return Float.compare(hexagon1.getRow(), hexagon2.getRow());
	};

}
