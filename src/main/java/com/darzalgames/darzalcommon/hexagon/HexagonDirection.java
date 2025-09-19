package com.darzalgames.darzalcommon.hexagon;

import java.util.ArrayList;
import java.util.List;

/**
 * Directions for hexagon edges.
 * These are for flat-top hexagons.
 */
public class HexagonDirection {

	private static final List<HexagonDirection> values = new ArrayList<>();

	private final Hexagon direction;

	/** The top-left edge of a flat-top hexagon */
	public static final HexagonDirection TOP_LEFT = new HexagonDirection(-1, 0);
	/** The top edge of a flat-top hexagon */
	public static final HexagonDirection TOP = new HexagonDirection(0, -1);
	/** The top-right edge of a flat-top hexagon */
	public static final HexagonDirection TOP_RIGHT = new HexagonDirection(1, -1);
	/** The bottom-right edge of a flat-top hexagon */
	public static final HexagonDirection BOTTOM_RIGHT = new HexagonDirection(1, 0);
	/** The bottom edge of a flat-top hexagon */
	public static final HexagonDirection BOTTOM = new HexagonDirection(0, 1);
	/** The bottom-left edge of a flat-top hexagon */
	public static final HexagonDirection BOTTOM_LEFT = new HexagonDirection(-1, 1);

	private HexagonDirection(int q, int r) {
		direction = new Hexagon(q, r);
		values.add(this);
	}

	/**
	 * Calculates the neighboring {@link Hexagon} in a particular hexagonal direction
	 * @param q Q-axis Hexagon whose neighbor you want to find (axial system)
	 * @param r R-axis Hexagon whose neighbor you want to find (axial system)
	 * @return The neighboring {@link Hexagon} in a particular direction
	 */
	public Hexagon getNeighborHexagon(int q, int r) {
		int neighborQ = q + direction.q();
		int neighborR = r + direction.r();
		return new Hexagon(neighborQ, neighborR);
	}

	/**
	 * Calculates the neighboring {@link Hexagon} in a particular hexagonal direction
	 * @param hexagon The hexagon whose neighbor you want to find (axial system)
	 * @return The neighboring {@link Hexagon} in a particular direction
	 */
	public Hexagon getNeighborHexagon(Hexagon hexagon) {
		return getNeighborHexagon(hexagon.q(), hexagon.r());
	}

	/**
	 * Get all the flat-top hexagon directions;
	 * @return a new list of all directions
	 */
	public static List<HexagonDirection> values() {
		return new ArrayList<>(values);
	}

}
