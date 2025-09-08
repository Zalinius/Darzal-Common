package com.darzalgames.darzalcommon.hexagon;

import java.util.ArrayList;
import java.util.List;

/**
 * Directions for hexagon edges.
 * These are for flat-top hexagons.
 */
public class HexagonDirection extends Hexagon {

	private static final List<HexagonDirection> values = new ArrayList<>();

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
		super(q, r);
		values.add(this);
	}

	/**
	 * Calculates the neighboring {@link Hexagon} in a particular hexagonal direction
	 * @param q Q-axis Hexagon whose neighbor you want to find (axial system)
	 * @param r R-axis Hexagon whose neighbor you want to find (axial system)
	 * @param direction The direction for the Hexagon that you want to find
	 * @return The neighboring {@link Hexagon} in a particular direction
	 */
	public static Hexagon getNeighborHexagon(int q, int r, HexagonDirection direction) {
		int neighborQ = q + direction.getQ();
		int neighborR = r + direction.getR();
		return new Hexagon(neighborQ, neighborR);
	}

	/**
	 * Calculates the neighboring {@link Hexagon} in a particular hexagonal direction
	 * @param hexagon The hexagon whose neighbor you want to find (axial system)
	 * @param direction The direction for the Hexagon that you want to find
	 * @return The neighboring {@link Hexagon} in a particular direction
	 */
	public static Hexagon getNeighborHexagon(Hexagon hexagon, HexagonDirection direction) {
		return getNeighborHexagon(hexagon.getQ(), hexagon.getR(), direction);
	}

	/**
	 * Get all the flat-top hexagon directions;
	 * @return a new list of all directions
	 */
	public static List<HexagonDirection> values() {
		return new ArrayList<>(values);
	}

}
