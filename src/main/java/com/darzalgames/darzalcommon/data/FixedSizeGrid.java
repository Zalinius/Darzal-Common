package com.darzalgames.darzalcommon.data;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * A generic rectangular grid data structure of a fixed size<br>
 * This Class is much like a rectangular array, with many convenience methods
 * @param <E> The Generic type the grid contains
 */
public class FixedSizeGrid<E> implements Iterable<E> {

	private final List<E> inside;
	private final int width;
	private final int height;

	/**
	 * Creates a grid of a fixed width and height, with values initialized to null
	 * @param width The width of the grid
	 * @param height The height of the grid
	 */
	public FixedSizeGrid(int width, int height) {
		this(width, height, (i, j) -> null);
	}

	/**
	 * Creates a grid of a fixed width and height, with values initialized to a default value
	 * @param width The width of the grid
	 * @param height The height of the grid
	 * @param defaultValue The value to initialize each grid entry to
	 */
	public FixedSizeGrid(int width, int height, E defaultValue) {
		this(width, height, (i, j) -> defaultValue);
	}

	/**
	 * Creates a grid of a fixed width and height, with values initialized using a function
	 * @param width The width of the grid
	 * @param height The height of the grid
	 * @param initializer The initialization function, which uses the i and j coordinates as input
	 */
	public FixedSizeGrid(int width, int height, BiFunction<Integer, Integer, E> initializer) {
		inside = new ArrayList<>(width*height);
		for (int i = 0; i < width*height; i++) {
			inside.add(null);
		}
		this.width = width;
		this.height = height;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				set(i, j, initializer.apply(i, j));
			}
		}
	}

	/**
	 * Checks if the index coordinates are in the grid
	 * @param i The i coordinate, which runs along the width
	 * @param j The j coordinate, which runs along the height
	 * @return True if the coordinate pair is in the grid, false otherwise
	 */
	public boolean isInGrid(int i, int j) {
		return i >= 0 && i < width
				&& j >= 0 && j < height;
	}

	/**
	 * Checks if the coordinate is in the grid
	 * @param coordinate The i,j coordinate
	 * @return True if the coordinate is in the grid, false otherwise
	 */
	public boolean isInGrid(Coordinate coordinate) {
		return isInGrid(coordinate.i(), coordinate.j());
	}

	/**
	 * Gets the width of the grid
	 * @return the width of the fixed sized grid
	 */
	public int width() {
		return width;
	}

	/**
	 * Gets the height of the grid
	 * @return the height of the fixed sized grid
	 */
	public int height() {
		return height;
	}

	/**
	 * Get the area of the grid
	 * @return The total size and capacity of the grid
	 */
	public int size() {
		return width*height;
	}

	/**
	 * Get the number of non null entries in the grid
	 * @return The number of non null entries
	 */
	public int sizeNonNull() {
		return (int) inside.stream().filter(Objects::nonNull).count();
	}

	/**
	 * Sets the value at a given coordinate
	 * @param i The i coordinate, which runs along the width
	 * @param j The j coordinate, which runs along the height
	 * @param value The value to insert
	 * @return the value previously at that coordinate, or null otherwise
	 */
	public E set(int i, int j, E value) {
		if(!isInGrid(i, j)) {
			throw new IllegalArgumentException(i + ", " + j + " are not within the domain of the grid!");
		}

		return setToList(i, j, value);
	}

	/**
	 * Sets the value at a given coordinate
	 * @param coordinate The coordinate in the grid
	 * @param value
	 * @return the value previously at that coordinate, or null otherwise
	 */
	public E set(Coordinate coordinate, E value) {
		return set(coordinate.i(), coordinate.j(), value);
	}

	/**
	 * Gets the value at a given coordinate
	 * @param i The i coordinate, which runs along the width
	 * @param j The j coordinate, which runs along the height
	 * @return the value at the coordinate
	 */
	public E get(int i, int j) {
		if(!isInGrid(i, j)) {
			throw new IndexOutOfBoundsException(i +", " + j);
		}
		else {
			return getFromList(i, j);
		}
	}

	/**
	 * Gets the value at a given coordinate
	 * @param coordinate The coordinate in the grid
	 * @return the value at the coordinate
	 */
	public E get(Coordinate coordinate) {
		return get(coordinate.i(), coordinate.j());
	}

	/**
	 * Finds the coordinates of the first instance of a value
	 * @param e the value to check
	 * @return The coordinates of the first value found, or null if absent
	 */
	public Coordinate coordinatesOf(E e) {
		int linearPosition = inside.indexOf(e);
		if(linearPosition == -1) {
			return null;
		}
		else {
			return computeCoordinatePosition(linearPosition);
		}
	}

	/**
	 * Gets all the coordinates directly adjacent to this one, which are in the grid
	 * Directly adjacent coordinates will be directly above, below, to the left or to the right of this one
	 * @param i The i coordinate, which runs along the width
	 * @param j The j coordinate, which runs along the height
	 * @return a list of directly adjacent coordinates
	 */
	public List<Coordinate> getDirectlyAdjacentCoordinates(int i, int j){
		return streamCoordinates()
				.filter(coordinate -> coordinate.taxiDistance(new Coordinate(i, j)) == 1)
				.filter(this::isInGrid)
				.toList();
	}

	/**
	 * Gets all the coordinates adjacent to this one, which are in the grid
	 * Adjacent coordinates are adjacent or diagonal from this one
	 * @param i The i coordinate, which runs along the width
	 * @param j The j coordinate, which runs along the height
	 * @return a list of adjacent coordinates
	 */
	public List<Coordinate> getAdjacentCoordinates(int i, int j){
		return streamCoordinates()
				.filter(coordinate -> coordinate.kingDistance(new Coordinate(i, j)) == 1)
				.filter(this::isInGrid)
				.toList();
	}

	/**
	 * Gets all the elements directly adjacent to this one, which are in the grid
	 * Directly adjacent elements will be directly above, below, to the left or to the right of this one
	 * @param i The i coordinate, which runs along the width
	 * @param j The j coordinate, which runs along the height
	 * @return a list of directly adjacent elements
	 */
	public List<E> getDirectlyAdjacentElements(int i, int j){
		List<Coordinate> coordinatesToGet = getDirectlyAdjacentCoordinates(i, j);
		return coordinatesToGet.stream().map(this::get).toList();

	}

	/**
	 * Gets all the elements adjacent to this one, which are in the grid
	 * Adjacent elements, which are adjacent or diagonal from this one
	 * @param i The i coordinate, which runs along the width
	 * @param j The j coordinate, which runs along the height
	 * @return a list of adjacent elements
	 */
	public List<E> getAdjacentElements(int i, int j){
		List<Coordinate> coordinatesToGet = getAdjacentCoordinates(i, j);
		return coordinatesToGet.stream().map(this::get).toList();

	}

	private E getFromList(int i, int j) {
		int position = computeLinearPosition(i, j);
		return inside.get(position);
	}
	private E setToList(int i, int j, E value) {
		int position = computeLinearPosition(i, j);
		return inside.set(position, value);
	}
	private int computeLinearPosition(int i, int j) {
		return i + j*width;
	}
	private Coordinate computeCoordinatePosition(int linearPosition) {
		int i = linearPosition % width;
		int j = linearPosition / width;
		return new Coordinate(i, j);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				sb.append(get(i, j));
				sb.append(" ");
			}
			sb.append("\n");
		}

		return sb.toString().trim();
	}

	/**
	 * Get all the coordinates of this grid
	 * @return a list of the coordinates in the grid
	 */
	public List<Coordinate> getCoordinates() {
		List<Coordinate> coordinates = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				coordinates.add(new Coordinate(i, j));
			}
		}

		return coordinates;
	}

	/**
	 * Stream all the coordinates of this grid
	 * @return a stream of the coordinates in the grid
	 */
	public Stream<Coordinate> streamCoordinates() {
		return getCoordinates().stream();
	}

	/**
	 * Stream all the coordinates of this grid
	 * @return a stream of the coordinates in the grid
	 */
	public Stream<E> streamElements() {
		return inside.stream();
	}

	@Override
	public Iterator<E> iterator() {
		return inside.iterator();
	}

}
