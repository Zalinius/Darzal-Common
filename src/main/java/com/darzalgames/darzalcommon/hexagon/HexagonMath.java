package com.darzalgames.darzalcommon.hexagon;

import com.darzalgames.darzalcommon.data.Tuple;

public class HexagonMath {

	/**
	 * Convert the flat-top hexagon's logical coordinates to pixel coordinates
	 * @param height        The height component in the height:width ratio
	 * @param width         The width component in the height:width ratio
	 * @param q             The Q-axis coordinate of the hexagon
	 * @param r             The R-axis coordinate of the hexagon
	 * @param hexagonWidth  The width of the visual representation of the hexagon
	 * @param hexagonHeight The height of the visual representation of the hexagon
	 * @param stageHeight   The logical height of the whole game stage
	 * @return A {@link Tuple} with the pixel coordinates for where this {@link Hexagon}'s visual representation should be drawn on the Stage
	 */
	public static Tuple<Float, Float> getScreenPositionOnStage(float height, float width, int q, int r, float hexagonWidth, float hexagonHeight, float stageHeight) {
		Tuple<Float, Float> position = getScreenPosition(height, width, q, r, hexagonWidth, hexagonHeight);
		float y = stageHeight - position.f - hexagonHeight; // This math works from a top-left system and actors work from bottom-left, so we subtract from the screen height
		return new Tuple<>(position.e, y);
	}

	/**
	 * Convert the flat-top hexagon's logical coordinates to pixel coordinates. The hex at coordinates (0, 0) is always located at position (0, 0)
	 * @param height        The height component in the height:width ratio
	 * @param width         The width component in the height:width ratio
	 * @param q             The Q-axis coordinate of the hexagon
	 * @param r             The R-axis coordinate of the hexagon
	 * @param hexagonWidth  The width of the visual representation of the hexagon
	 * @param hexagonHeight The height of the visual representation of the hexagon
	 * @return A {@link Tuple} with the screen position coordinates for where this {@link Hexagon}'s visual representation should be drawn
	 */
	public static Tuple<Float, Float> getScreenPosition(float width, float height, int q, int r, float hexagonWidth, float hexagonHeight) {
		float size = hexagonWidth / 2f;
		float x = size * (3f / 2 * q);
		float y = size * ((height / width) * q + ((height * 2) / width) * r);
		return new Tuple<>(x, y);
	}

	private HexagonMath() {}
}
