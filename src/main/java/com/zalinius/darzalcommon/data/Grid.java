package com.zalinius.darzalcommon.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
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
	
	public Grid(int width, int height) {
		this(width, height, (i, j) -> null, null);
	}

	public Grid(int width, int height, E defaultValue) {
		this(width, height, (i, j) -> null, defaultValue);
	}

	public Grid(int width, int height, BiFunction<Integer, Integer, E> initializer) {
		this(width, height, initializer, null);
	}

	
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


	public boolean isInGrid(int i, int j) {
		return i >= 0 && i < width
				&& j >= 0 && j < height;
	}
	public boolean isInGrid(Coord coord) {
		return isInGrid(coord.i, coord.j);
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
	public void set(Coord coord, E value) {
		set(coord.i, coord.j, value);
	}

	public E get(int i, int j) {
		if(!isInGrid(i, j)) {
			return defaultValue;
		}
		else {
			return getFromList(i, j);
		}
	}
	public E get(Coord coord) {
		return get(coord.i, coord.j);
	}
	
	public Coord coordinatesOf(E e) {
		int linearPosition = inside.indexOf(e);
		if(linearPosition == -1) {
			return null;
		}
		else {
			return computeCoordPosition(linearPosition);
		}
	}
	
	public List<Coord> getDirectlyAdjacentCoordinates(int i, int j){
		return streamCoordinates()
			  .filter(coord -> coord.taxiDistance(new Coord(i, j)) == 1)
			  .filter(this::isInGrid)
			  .toList();
	}

	public List<Coord> getAdjacentCoordinates(int i, int j){
		return streamCoordinates()
			  .filter(coord -> coord.kingDistance(new Coord(i, j)) == 1)
			  .filter(this::isInGrid)
			  .toList();
	}

	public List<E> getDirectlyAdjacentElements(int i, int j){
		List<Coord> coordsToGet = getDirectlyAdjacentCoordinates(i, j);		
		return coordsToGet.stream().map(this::get).toList();

	}

	public List<E> getAdjacentElements(int i, int j){
		List<Coord> coordsToGet = getAdjacentCoordinates(i, j);		
		return coordsToGet.stream().map(this::get).toList();

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
	
	private Coord computeCoordPosition(int linearPosition) {
		int i = linearPosition % width;
		int j = linearPosition / width;
		return new Coord(i, j);
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
	
	
	public Stream<Coord> streamCoordinates(){
		List<Coord> coordinates = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				coordinates.add(new Coord(i, j));
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








