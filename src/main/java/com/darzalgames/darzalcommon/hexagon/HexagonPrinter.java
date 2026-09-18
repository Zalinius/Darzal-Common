package com.darzalgames.darzalcommon.hexagon;

import java.util.*;

import com.darzalgames.darzalcommon.data.FixedSizeGrid;
import com.darzalgames.darzalcommon.data.InfiniteGrid;
import com.darzalgames.darzalcommon.math.SimpleMath;

/**
 * A convenience class for graphically representing a hexagon map using characters.
 * Can be used for printing the map to a console for example
 */
public class HexagonPrinter {

	private HexagonPrinter() {}

	/**
	 * Returns a graphical ascii representation of a hexagon map, showing the values of the map and their q-r coordinates
	 * Values in the map will be represented using their toString() method
	 * @param <E>        The type the map contains
	 * @param hexagonMap the hexagon map to represent
	 * @return A multiline string graphically representing the map
	 */
	public static <E> String toPretty(HexagonMap<E> hexagonMap) {
		final String hexagonTemplateString = """
				      --------
				    --        --
				  --            --
				--  ssssssssssss  --
				  --  (qq)(rr)  --
				    --        --
				      --------      """;

		InfiniteGrid<String> asciiGrid = new InfiniteGrid<>(" ");

		Set<Hexagon> allHexagons = hexagonMap.keySet();
		for (Iterator<Hexagon> it = allHexagons.iterator(); it.hasNext();) {
			Hexagon hexagon = it.next();
			String value = hexagonMap.get(hexagon).toString();
			String pattern = "ssssssssssss";
			value = setStringLength(value, pattern.length());

			String filledHexagonStringTemplate = hexagonTemplateString.replace(pattern, value);
			String qString = Integer.toString(hexagon.q());
			if (hexagon.q() >= 0) {
				qString = " " + qString;
			}
			String rString = Integer.toString(hexagon.r());
			if (hexagon.r() >= 0) {
				rString = " " + rString;
			}
			filledHexagonStringTemplate = filledHexagonStringTemplate.replace("qq", qString);
			filledHexagonStringTemplate = filledHexagonStringTemplate.replace("rr", rString);
			// Put the template into a fixed size grid
			final List<String> hexagonTemplateLines = Arrays.asList(filledHexagonStringTemplate.split("\n"));
			final int hexHeight = hexagonTemplateLines.size();
			final int hexWidth = hexagonTemplateLines.stream().map(String::length).reduce(0, Integer::max);

			FixedSizeGrid<String> templateGrid = new FixedSizeGrid<>(hexWidth, hexHeight, " ");
			for (int line = 0; line != hexagonTemplateLines.size(); line++) {
				String lineEntry = hexagonTemplateLines.get(line);
				for (int character = 0; character != lineEntry.length(); character++) {
					templateGrid.set(character, line, Character.toString(lineEntry.charAt(character)));
				}
			}

			// Copy the fixed sized template into the infinite grid, using the hex coords as an offset
			int iOffset = 12 * hexagon.q();
			int jOffset = 3 * hexagon.q() + 6 * hexagon.r();
			templateGrid.streamCoordinates().forEach(coord -> {
				int i = coord.i() + iOffset;
				int j = coord.j() + jOffset;
				if (" ".equals(asciiGrid.get(i, j))) {
					asciiGrid.put(i, j, templateGrid.get(coord));
				}
			});
		}

		StringBuilder sb = new StringBuilder();
		for (int j = asciiGrid.minJ(); j <= asciiGrid.maxJ(); j++) {
			for (int i = asciiGrid.minI(); i <= asciiGrid.maxI(); i++) {
				sb.append(asciiGrid.get(i, j));
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	private static String setStringLength(String original, int length) {
		if (original.length() > length) {
			original = original.substring(0, length);
		}
		while (original.length() < length) {
			if (SimpleMath.isEven(original.length())) {
				original = " " + original;
			} else {
				original = original + " ";
			}
		}

		return original;
	}

}
