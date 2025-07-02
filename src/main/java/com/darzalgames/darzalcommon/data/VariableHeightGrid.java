package com.darzalgames.darzalcommon.data;

import java.util.*;
import java.util.stream.Stream;

/**
 * A generic rectangular grid data structure of a fixed size
 * @param <E> The Generic type the grid contains
 */
public class VariableHeightGrid<E> implements Collection<E>{

	private List<E> inside;
	public final int width;

	/**
	 * Creates a grid of a fixed width and height, with values NOT initialized
	 * @param width The width of the grid
	 */
	public VariableHeightGrid(int width) {
		this(width, new ArrayList<>());
	}

	/**
	 * Creates a grid of a fixed width and height, with values initialized using a function
	 * @param width The width of the grid
	 * @param initialValues The values to place in the grid initially
	 */
	public VariableHeightGrid(int width, Collection<E> initialValues) {
		this.width = width;
		inside = new ArrayList<>();
		addAll(initialValues);
	}

	/**
	 * @param row The row coordinate, which runs along the height
	 * @param column The column coordinate, which runs along the width
	 * @return True if the coordinate pair is in the grid, false otherwise
	 */
	public boolean isInGrid(int row, int column) {
		return row >= 0 && row < getHeight()
				&& column >= 0 && column < width;
	}

	public boolean isInGrid(Coordinate coordinate) {
		return isInGrid(coordinate.i, coordinate.j);
	}

	public boolean hasEntryAt(int row, int column) {
		int index = computeLinearPosition(row, column);
		return isInGrid(row, column) && index < inside.size() && inside.get(index) != null;
	}

	public int getHeight() {
		int remainder = inside.size()%width;
		int rowCount = inside.size()/width;
		if (remainder != 0) {
			rowCount++;
		}
		return rowCount;
	}

	/**
	 * @return The total size of the grid
	 */
	@Override
	public int size() {
		return inside.size();
	}

	public E get(int row, int column) {
		if(!hasEntryAt(row, column)) {
			return null;
		}
		else {
			return getFromList(row, column);
		}
	}
	public E get(Coordinate coordinate) {
		return get(coordinate.i, coordinate.j);
	}

	public Coordinate coordinatesOf(E e) {
		int linearPosition = inside.indexOf(e);
		if(linearPosition == -1) {
			return null;
		}
		else {
			return computeCoordinatePosition(linearPosition);
		}
	}

	public List<Coordinate> getDirectlyAdjacentCoordinates(int row, int column){
		return streamCoordinates()
				.filter(coordinate -> coordinate.taxiDistance(new Coordinate(row, column)) == 1)
				.filter(this::isInGrid)
				.toList();
	}

	public List<Coordinate> getAdjacentCoordinates(int row, int column){
		return streamCoordinates()
				.filter(coordinate -> coordinate.kingDistance(new Coordinate(row, column)) == 1)
				.filter(this::isInGrid)
				.toList();
	}

	public List<E> getDirectlyAdjacentElements(int row, int column){
		List<Coordinate> coordinatesToGet = getDirectlyAdjacentCoordinates(row, column);
		return coordinatesToGet.stream().map(this::get).toList();

	}

	public List<E> getAdjacentElements(int row, int column){
		List<Coordinate> coordinatesToGet = getAdjacentCoordinates(row, column);
		return coordinatesToGet.stream().map(this::get).toList();

	}

	private E getFromList(int row, int column) {
		int position = computeLinearPosition(row, column);
		return inside.get(position);
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
				sb.append(get(i, j));
				sb.append(" ");
			}
			sb.append("\n");
		}

		return sb.toString().trim();
	}


	public Stream<Coordinate> streamCoordinates(){
		List<Coordinate> coordinates = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < getHeight(); j++) {
				coordinates.add(new Coordinate(i, j));
			}
		}

		return coordinates.stream();
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
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		inside.clear();
	}

}