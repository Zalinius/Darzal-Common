package com.darzalgames.darzalcommon.hexagon;

import com.darzalgames.darzalcommon.data.Coordinate;
import com.darzalgames.darzalcommon.data.Tuple;

public class HexagonMath {

	/** https://www.redblobgames.com/grids/hexagons/#conversions
	 * using flat-top odd-q
	 * @param hexagon The hexagon to convert
	 * @return New coordinates converted to the offset system
	 */
	public static Coordinate axialToOffsetCoordinates(Hexagon hexagon) {
		return axialToOffsetCoordinates(hexagon.getQ(), hexagon.getR());
	}
	
	/**
	 * @param q The q-axis coordinate
	 * @param r The r-axis coordinate
	 * @return New coordinates converted to the offset system
	 */
	public static Coordinate axialToOffsetCoordinates(int q, int r) {
		int col = q;
		int row = r + (q - (q&1)) / 2;
		return new Coordinate(col, row);
	}

	/**
	 * @param column The column coordinate
	 * @param row The row coordinate
	 * @return New coordinates converted to the axial system
	 */
	public static Coordinate offsetToAxialCoordinates(int column, int row) {
		int q = column;
		int r = row - (column - (column&1)) / 2;
		return new Coordinate(q, r);
	}


	/**
	 * Convert the flat-top hexagon's logical coordinates to pixel coordinates
	 * @param height The height component in the height:width ratio
	 * @param width The width component in the height:width ratio
	 * @param q The Q-axis coordinate of the hexagon
	 * @param r The R-axis coordinate of the hexagon
	 * @param hexagonWidth The width of the visual representation of the hexagon
	 * @param hexagonHeight The height of the visual representation of the hexagon
	 * @param stageHeight The logical height of the whole game stage
	 * @return A {@link Tuple} with the pixel coordinates for where this {@link Hexagon}'s visual representation should be drawn on the Stage 
	 * 	relative to other hexagon's in the visual representation of the {@link HexagonGrid})
	 */
	public static Tuple<Float, Float> getScreenPositionCoordinatesOnStage(float height, float width, int q, int r, float hexagonWidth, float hexagonHeight, float stageHeight) {
		Tuple<Float, Float> position = getScreenPositionCoordinates(height, width, q, r, hexagonWidth, hexagonHeight);
		float y = stageHeight - position.f - hexagonHeight; // This math works from a top-left system and actors work from bottom-left, so we subtract from the screen height
		return new Tuple<Float, Float>(position.e, y);
	}

	/**
	 * Convert the flat-top hexagon's logical coordinates to pixel coordinates
	 * @param height The height component in the height:width ratio
	 * @param width The width component in the height:width ratio
	 * @param q The Q-axis coordinate of the hexagon
	 * @param r The R-axis coordinate of the hexagon
	 * @param hexagonWidth The width of the visual representation of the hexagon
	 * @param hexagonHeight The height of the visual representation of the hexagon
	 * @return A {@link Tuple} with the screen position coordinates for where this {@link Hexagon}'s visual representation should be drawn
	 * 	relative to other hexagon's in the visual representation of the {@link HexagonGrid})
	 */
	public static Tuple<Float, Float> getScreenPositionCoordinates(float height, float width, int q, int r, float hexagonWidth, float hexagonHeight) {
		float size = hexagonWidth/2f;
		float x = size * (3f/2 * q);
		float y = size * ((height/width)*q + ((height*2)/width) * r);
		return new Tuple<Float, Float>(x, y);
	}
	
	private HexagonMath() {}
}
