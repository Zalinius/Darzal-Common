package com.darzalgames.darzalcommon.data;

import java.util.*;

/**
 * A rectangular grid data structure of a fixed width and variable height.
 * Can be added to and removed from like a list, and won't keep empty entries between others.
 * @param <E> The Generic type the grid contains
 */
public class VariableHeightGrid<E> implements Collection<E>{

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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		int remainder = inside.size()%width;
		int rowCount = inside.size()/width;
		if (remainder != 0) {
			rowCount++;
		}
		return rowCount;
	}

	@Override
	public int size() {
		return inside.size();
	}

	public boolean hasEntryAt(int row, int column) {
		if (column < 0 || column >= width || row < 0) {
			return false;
		}
		int index = computeLinearPosition(row, column);
		return index < inside.size() && inside.get(index) != null;
	}

	public E get(int row, int column) {
		int position = computeLinearPosition(row, column);
		return inside.get(position);
	}

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
		for (int j = 0; j < getHeight(); j++) {
			for (int i = 0; i < width; i++) {
				if (hasEntryAt(i, j)) {
					sb.append(get(i, j));
					sb.append(" ");
				}
			}
			sb.append("\n");
		}

		return sb.toString().trim();
	}


	@Override
	public boolean isEmpty() {
		return inside.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return inside.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return inside.iterator();
	}

	@Override
	public Object[] toArray() {
		return inside.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return inside.toArray(a);
	}

	@Override
	public boolean add(E e) {
		return inside.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return inside.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return inside.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return inside.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return inside.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return inside.retainAll(c);
	}

	@Override
	public void clear() {
		inside.clear();
	}

}