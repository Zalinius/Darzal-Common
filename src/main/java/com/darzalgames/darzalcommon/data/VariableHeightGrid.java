package com.darzalgames.darzalcommon.data;

import java.util.*;
import java.util.stream.Stream;

/**
 * A rectangular grid data structure of a fixed width and variable height.
 * Can be added to and removed from like a list, and won't keep empty entries between others.
 * @param <E> The Generic type the grid contains
 */
public class VariableHeightGrid<E> implements Iterable<E>{

	private final List<E> inside;
	private final int width;

	/**
	 * Creates a grid of a fixed width and variable (calculated) height
	 * @param width The width of the grid
	 */
	public VariableHeightGrid(int width) {
		this(width, new ArrayList<>());
	}

	/**
	 * Creates a grid of a fixed width and variable (calculated) height, initialized with a collection of values
	 * @param width The width of the grid
	 * @param initialValues The values to place in the grid initially
	 */
	public VariableHeightGrid(int width, Collection<E> initialValues) {
		this.width = width;
		inside = new ArrayList<>();
		addAll(initialValues);
	}

	/**
	 * Gets the fixed width of the grid
	 * @return the fixed number of objects in a full row (i.e. the number of columns)
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the current height of the grid
	 * @return the number of rows, which can change as the entries change
	 */
	public int getHeight() {
		int rowCount = inside.size()/width;
		int remainder = inside.size()%width;
		if (remainder > 0) {
			rowCount++;
		}
		return rowCount;
	}

	public int size() {
		return inside.size();
	}

	/**
	 * Checks if a non-null entry is present at the given row and column
	 * This method will not throw if the row and column are invalid
	 * @param row the row to check
	 * @param column the column to check
	 * @return true if there is a non-null entry at the specified position, false otherwise
	 */
	public boolean hasEntryAt(int row, int column) {
		if (column < 0 || column >= width || row < 0) {
			return false;
		}
		int index = computeLinearPosition(row, column);
		return index < inside.size() && inside.get(index) != null;
	}

	/**
	 * Gets the value at the specified position <br>
	 * Call {@link VariableHeightGrid#hasEntryAt(int, int)} to validate that such an entry exist first
	 * @param row (vertical)
	 * @param column (horizontal)
	 * @return The E at the specified row and column
	 * @throws IndexOutOfBoundsException if the the row and column are outside the bounds of the grid
	 */
	public E get(int row, int column) {
		int position = computeLinearPosition(row, column);
		return inside.get(position);
	}

	/**
	 * Returns the coordinates of the first occurrence of the specified element <br>
	 * Call {@link VariableHeightGrid#hasEntryAt(int, int)} to validate that such an entry exist first
	 * @param e the object to check
	 * @return The coordinate of the provided object
	 * @throws IllegalArgumentException if the element is not present in the grid
	 */
	public Coordinate coordinatesOf(E e) {
		int linearPosition = inside.indexOf(e);
		if(linearPosition == -1) {
			throw new IllegalArgumentException(e + " is not in the grid!");
		}
		else {
			return computeCoordinatePosition(linearPosition);
		}
	}

	private int computeLinearPosition(int row, int column) {
		return row*width + column;
	}

	private Coordinate computeCoordinatePosition(int linearPosition) {
		int row = linearPosition / width;
		int column = linearPosition % width;
		return new Coordinate(row, column);
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < getHeight(); row++) {
			for (int column = 0; column < width; column++) {
				if (hasEntryAt(row, column)) {
					sb.append(get(row, column));
					sb.append("\t");
				}
			}
			sb.append("\n");
		}

		return sb.toString().trim();
	}

	/**
	 * Checks if the grid is empty
	 * @return true if the grid contains no elements
	 */
	public boolean isEmpty() {
		return inside.isEmpty();
	}

	/**
	 * Checks if the grid contains an element
	 * @param o the element to check
	 * @return true if the grid contains the element
	 */
	public boolean contains(Object o) {
		return inside.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return inside.iterator();
	}

	public boolean add(E e) {
		return inside.add(e);
	}

	public boolean remove(Object o) {
		return inside.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return inside.containsAll(c);
	}

	public boolean addAll(Collection<? extends E> c) {
		return inside.addAll(c);
	}

	public boolean removeAll(Collection<?> c) {
		return inside.removeAll(c);
	}

	public Stream<E> stream() {
		return inside.stream();
	}

	public void clear() {
		inside.clear();
	}

}