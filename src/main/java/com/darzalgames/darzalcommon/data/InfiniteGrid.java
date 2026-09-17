package com.darzalgames.darzalcommon.data;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * A generic rectangular grid data structure of infinite size<br>
 * This Class is much like a rectangular array, but can potentially expand in any direction infinitely
 * At any given point, it is canonically a rectangle whose width and heights are defined by the most extreme elements
 * It's a bit like a spreadsheet
 * @param <E> The Generic type the grid contains
 */
public class InfiniteGrid<E> implements Iterable<E> {

	private final Map<Coordinate, E> inside;
	private final E defaultValue;

	/**
	 * Creates an infinite grid with a default value
	 * @param defaultValue The default value for grid elements
	 */
	public InfiniteGrid(E defaultValue) {
		inside = new TreeMap<>();
		this.defaultValue = defaultValue;
	}

	private InfiniteGrid(InfiniteGrid<E> other) {
		inside = new TreeMap<>(other.inside);
		defaultValue = other.defaultValue;
	}

	public E put(int i, int j, E value) {
		return inside.put(new Coordinate(i, j), value);
	}

	public E get(int i, int j) {
		return inside.getOrDefault(new Coordinate(i, j), defaultValue);
	}

	public int minX() {
		return inside
				.keySet()
				.stream()
				.map(Coordinate::i)
				.reduce(Integer::min)
				.orElseThrow(() -> new IllegalStateException("Empty grid has no minimum!"));
	}

	public int maxX() {
		return inside
				.keySet()
				.stream()
				.map(Coordinate::i)
				.reduce(Integer::max)
				.orElseThrow(() -> new IllegalStateException("Empty grid has no maximum!"));
	}

	public int minY() {
		return inside
				.keySet()
				.stream()
				.map(Coordinate::j)
				.reduce(Integer::min)
				.orElseThrow(() -> new IllegalStateException("Empty grid has no minimum!"));
	}

	public int maxY() {
		return inside
				.keySet()
				.stream()
				.map(Coordinate::j)
				.reduce(Integer::max)
				.orElseThrow(() -> new IllegalStateException("Empty grid has no maximum!"));
	}

	public int width() {
		// TODO return 0 if grid empty?
		return maxX() - minX() + 1;
	}

	public int height() {
		// TODO return 0 if grid empty?
		return maxY() - minY() + 1;
	}

	public int size() {
		return inside.size();
	}

	public void fillInBlanks() {
		for (int i = minX(); i <= maxX(); i++) {
			for (int j = minY(); j <= maxY(); j++) {
				Coordinate coordinate = new Coordinate(i, j);
				if (!inside.containsKey(coordinate)) {
					put(i, j, defaultValue);
				}
			}
		}
	}

	@Override
	public Iterator<E> iterator() {
		InfiniteGrid<E> copy = new InfiniteGrid<>(this);
		copy.fillInBlanks();
		return copy.inside.values().iterator();
	}

}
