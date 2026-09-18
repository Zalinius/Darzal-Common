package com.darzalgames.darzalcommon.data;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * A generic rectangular grid data structure of infinite size<br>
 * This Class is much like a Grid shaped Map, and can expand in any direction infinitely
 * At any given point, it is canonically a rectangular grid whose width and height are defined by its extreme elements
 * It's a bit like a spreadsheet
 * @param <E> The Generic type the grid contains
 */
public class InfiniteGrid<E> extends TreeMap<Coordinate, E> implements Iterable<E> {

	/**
	 * The default value used when no value is present in the grid
	 */
	private final E defaultValue;

	/**
	 * Creates an infinite grid with a default value
	 * @param defaultValue The default value for unspecified grid elements
	 */
	public InfiniteGrid(E defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Puts a value at the specified coordinates
	 * @param i     the i-coordinate at which to insert
	 * @param j     the j-coordinate at which to insert
	 * @param value the value to insert
	 * @return the previous value at the coordinates, or null if there was no value.
	 */
	public E put(int i, int j, E value) {
		return put(new Coordinate(i, j), value);
	}

	/**
	 * Gets the value at the coordinates in the infinite grid
	 * @param i the i-coordinate at which to get
	 * @param j the j-coordinate at which to get
	 * @return the value at the specified coordinates, or the defaultValue if absent
	 */
	public E get(int i, int j) {
		return get(new Coordinate(i, j));
	}

	@Override
	public E get(Object key) {
		if (containsKey(key)) {
			return super.get(key);
		} else {
			return defaultValue;
		}
	}

	/**
	 * Checks whether the coordinates have an explicitely inserted value (not just the default value)
	 * @param i the i-coordinate to check
	 * @param j the j-coordinate to check
	 * @return whether a value was explicitely inserted at the specified coordinates
	 */
	public boolean containsKey(int i, int j) {
		return containsKey(new Coordinate(i, j));
	}

	/**
	 * The decreasing i bound of the grid
	 * @return The minimum i value in the grid
	 */
	public int minI() {
		return keySet()
				.stream()
				.map(Coordinate::i)
				.reduce(Integer::min)
				.orElseThrow(() -> new IllegalStateException("Empty grid has no minimum!"));
	}

	/**
	 * The increasing i bound of the grid
	 * @return The maximum i value in the grid
	 */
	public int maxI() {
		return keySet()
				.stream()
				.map(Coordinate::i)
				.reduce(Integer::max)
				.orElseThrow(() -> new IllegalStateException("Empty grid has no maximum!"));
	}

	/**
	 * The decreasing j bound of the grid
	 * @return The minimum j value in the grid
	 */
	public int minJ() {
		return keySet()
				.stream()
				.map(Coordinate::j)
				.reduce(Integer::min)
				.orElseThrow(() -> new IllegalStateException("Empty grid has no minimum!"));
	}

	/**
	 * The increasing j bound of the grid
	 * @return The maximum j value in the grid
	 */
	public int maxJ() {
		return keySet()
				.stream()
				.map(Coordinate::j)
				.reduce(Integer::max)
				.orElseThrow(() -> new IllegalStateException("Empty grid has no maximum!"));
	}

	/**
	 * the current width of the grid
	 * @return the width of the rectangle which encompasses all the grid's elements
	 */
	public int width() {
		if (isEmpty()) {
			return 0;
		} else {
			return maxI() - minI() + 1;
		}
	}

	/**
	 * the current height of the grid
	 * @return the height of the rectangle which encompasses all the grid's elements
	 */
	public int height() {
		if (isEmpty()) {
			return 0;
		} else {
			return maxJ() - minJ() + 1;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new InfiniteGridIterator();
	}

	private class InfiniteGridIterator implements Iterator<E> {
		private int i;
		private int j;

		public InfiniteGridIterator() {
			i = minI();
			j = minJ();
		}

		@Override
		public boolean hasNext() {
			return i <= maxI() && j <= maxJ();
		}

		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			E result;

			if (!containsKey(i, j)) {
				result = defaultValue;
			} else {
				result = get(i, j);
			}

			if (i == maxI()) {
				i = minI();
				j++;
			} else {
				i++;
			}

			return result;
		}
	}
}
