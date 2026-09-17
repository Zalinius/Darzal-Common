package com.darzalgames.darzalcommon.hexagon;

import java.util.*;
import java.util.stream.Collectors;

import com.darzalgames.darzalcommon.data.FixedSizeGrid;
import com.darzalgames.darzalcommon.data.InfiniteGrid;
import com.darzalgames.darzalcommon.math.SimpleMath;

/**
 * A java-style map, with hexagons as keys and various convenience functions
 * @param <E> The type for the values of the key-value pairs in the map
 */
public class HexagonMap<E> extends LinkedHashMap<Hexagon, E> {

	private static final long serialVersionUID = 5801182744649958152L;

	/**
	 * Constructs an empty HexagonMap
	 */
	public HexagonMap() {
		super();
	}

	/**
	 * Gets the value in the middle of the map
	 * @return gets the value at the origin hexagon (0,0)
	 */
	public E getMiddleHexagonValue() {
		return get(Hexagon.ORIGIN);
	}

	/**
	 * Gets the neighbour hexagons of the input hexagon in this map
	 * To be used for game logic where adjacencies are relevant
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A set of the neighboring {@link Hexagon Hexagons}, in {@link HexagonDirection} order
	 */
	public Set<Hexagon> getHexagonNeighborsOf(Hexagon hexagon) {
		return HexagonDirection.values().stream()
				.map(direction -> direction.getNeighborHexagon(hexagon))
				.filter(this::containsKey)
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}

	/**
	 * Gets the neighbour values of the input hexagon in this map
	 * To be used for game logic where adjacencies are relevant
	 * @param hexagon The {@link Hexagon} whose neighbors you want to find
	 * @return A list of the neighboring values, in {@link HexagonDirection} order
	 */
	public List<E> getValueNeighborsOf(Hexagon hexagon) {
		return getHexagonNeighborsOf(hexagon).stream().map(this::get).toList();
	}

	/**
	 * Gets the immediate neibhbor value in the specified direction
	 * @param hexagon   The starting point for searching for a neighbor
	 * @param direction the direction to search in
	 * @return The neighboring value in the given direction if it exists, otherwise throws an IllegalArgumentException
	 */
	public E getValueNeighborInDirection(Hexagon hexagon, HexagonDirection direction) {
		Hexagon neighbor = direction.getNeighborHexagon(hexagon);
		return get(neighbor);
	}

	@Override
	public E get(Object key) {
		if (!containsKey(key)) {
			throw new IllegalArgumentException("Hexagon not in map: " + key);
		}
		return super.get(key);
	}

	public String toStringPretty() {
		final String hexagonTemplateString = """
				      --------
				    --        --
				  --            --
				--  ssssssssssss  --
				  --  (qq)(rr)  --
				    --        --
				      --------      """;

		InfiniteGrid<String> asciiGrid = new InfiniteGrid<>(" ");

		Set<Hexagon> allHexagons = keySet();
		for (Iterator<Hexagon> it = allHexagons.iterator(); it.hasNext();) {
			Hexagon hexagon = it.next();
			String value = get(hexagon).toString();
			String pattern = "ssssssssssss";
			if (value.length() > pattern.length()) {
				value = value.substring(0, pattern.length());
			}
			while (value.length() < pattern.length()) {
				if (SimpleMath.isEven(value.length())) {
					value = " " + value;
				} else {
					value = value + " ";
				}
			}

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

			// Copy the fixed sized template into the infite grid, using the hex coords as an offset
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
		for (int j = asciiGrid.minY(); j <= asciiGrid.maxY(); j++) {
			for (int i = asciiGrid.minX(); i <= asciiGrid.maxX(); i++) {
				sb.append(asciiGrid.get(i, j));
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		HexagonMap<String> map = new HexagonMap<>();
		map.put(new Hexagon(0, 0), "origin");
		map.put(new Hexagon(0, 1), "down");
		map.put(new Hexagon(1, 0), "right");
		map.put(new Hexagon(1, 1), "downright");
		map.put(new Hexagon(0, -1), "up");
		map.put(new Hexagon(-1, 0), "left");
		map.put(new Hexagon(-1, -1), "upleft");
		System.out.print(map.toStringPretty());
	}
}
