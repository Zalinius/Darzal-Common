package com.darzalgames.darzalcommon.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A generic rectangular grid data structure of a fixed size
 * @param <E> The Generic type the grid contains
 */
public class Grid<E> implements Collection<E>{

	private List<E> inside;
	public final int width;
	public final int height;
	public final E   defaultValue;
	
	/**
	 * Creates a grid of a fixed width and height, with values initialized to null
	 * @param width The width of the grid
	 * @param height The height of the grid
	 */
	public Grid(int width, int height) {
		this(width, height, (i, j) -> null, null);
	}

	/**
	 * Creates a grid of a fixed width and height, with values initialized to a default value
	 * @param width The width of the grid
	 * @param height The height of the grid
	 * @param defaultValue The value returned for coordinates outside the grid
	 */
	public Grid(int width, int height, E defaultValue) {
		this(width, height, (i, j) -> null, defaultValue);
	}

	/**
	 * Creates a grid of a fixed width and height, with values initialized using a function
	 * @param width The width of the grid
	 * @param height The height of the grid
	 * @param initializer The initialization function, which uses the i and j coordinates as input
	 */
	public Grid(int width, int height, BiFunction<Integer, Integer, E> initializer) {
		this(width, height, initializer, null);
	}


	/**
	 * Creates a grid of a fixed width and height, with values initialized using a function
	 * @param width The width of the grid
	 * @param height The height of the grid
	 * @param initializer The initialization function, which uses the i and j coordinates as input
	 * @param defaultValue The value returned for coordinates outside the grid
	 */
	public Grid(int width, int height, BiFunction<Integer, Integer, E> initializer, E defaultValue) {
		inside = new ArrayList<>(width*height);
		for (int i = 0; i < width*height; i++) {
			inside.add(null);
		}
		this.width = width;
		this.height = height;
		this.defaultValue = defaultValue;
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				set(i, j, initializer.apply(i, j));
			}
		}

	}


	/**
	 * @param i The i coordinate, which runs along the width 
	 * @param j The j coordinate, which runs along the height 
	 * @return True if the coordinate pair is in the grid, false otherwise
	 */
	public boolean isInGrid(int i, int j) {
		return i >= 0 && i < width
				&& j >= 0 && j < height;
	}
	public boolean isInGrid(Coordinate coordinate) {
		return isInGrid(coordinate.i, coordinate.j);
	}

	/**
	 * @return The total size of the grid
	 */
	public int size() {
		return width*height;
	}
	
	/**
	 * @return The number of non null entries
	 */
	public int sizeNonNull() {
		return (int) inside.stream().filter(Objects::nonNull).count();
	}

	public void set(int i, int j, E value) {
		if(!isInGrid(i, j)) {
			throw new IllegalArgumentException(i + ", " + j + " are not within the domain of the grid!");
		}

		setToList(i, j, value);
	}
	public void set(Coordinate coordinate, E value) {
		set(coordinate.i, coordinate.j, value);
	}

	public E get(int i, int j) {
		if(!isInGrid(i, j)) {
			return defaultValue;
		}
		else {
			return getFromList(i, j);
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
	
	public List<Coordinate> getDirectlyAdjacentCoordinates(int i, int j){
		return streamCoordinates()
			  .filter(coordinate -> coordinate.taxiDistance(new Coordinate(i, j)) == 1)
			  .filter(this::isInGrid)
			  .collect(Collectors.toList());
	}

	public List<Coordinate> getAdjacentCoordinates(int i, int j){
		return streamCoordinates()
			  .filter(coordinate -> coordinate.kingDistance(new Coordinate(i, j)) == 1)
			  .filter(this::isInGrid)
			  .collect(Collectors.toList());
	}

	public List<E> getDirectlyAdjacentElements(int i, int j){
		List<Coordinate> coordinatesToGet = getDirectlyAdjacentCoordinates(i, j);		
		return coordinatesToGet.stream().map(this::get).collect(Collectors.toList());

	}

	public List<E> getAdjacentElements(int i, int j){
		List<Coordinate> coordinatesToGet = getAdjacentCoordinates(i, j);		
		return coordinatesToGet.stream().map(this::get).collect(Collectors.toList());

	}	

	private E getFromList(int i, int j) {
		int position = computeLinearPosition(i, j);
		return inside.get(position);
	}
	private void setToList(int i, int j, E value) {
		int position = computeLinearPosition(i, j);		
		inside.set(position, value);
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
	
	
	public Stream<Coordinate> streamCoordinates(){
		List<Coordinate> coordinates = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
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
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
	}
	
}








