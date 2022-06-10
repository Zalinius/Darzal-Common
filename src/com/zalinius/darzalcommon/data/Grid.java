package com.zalinius.darzalcommon.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;

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
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				set(i, j, initializer.apply(i, j));
			}
		}

		this.width = width;
		this.height = height;
		this.defaultValue = defaultValue;
	}


	public boolean isInGrid(int x, int y) {
		return x >= 0 && x < width
				&& y >= 0 && y < height;
	}

	public int size() {
		return width*height;
	}

	public void set(int x, int y, E value) {
		if(!isInGrid(x, y)) {
			throw new IllegalArgumentException();
		}

		setToList(x, y, value);


	}


	public E get(int x, int y) {
		if(!isInGrid(x, y)) {
			return defaultValue;
		}
		else {
			return getFromList(x, y);
		}

	}


	private E getFromList(int x, int y) {
		int position = computeLinearPosition(x, y);
		return inside.get(position);
	}
	private void setToList(int x, int y, E value) {
		int position = computeLinearPosition(x, y);		
		inside.set(position, value);
	}

	private int computeLinearPosition(int x, int y) {
		return x + y*width;
	}
	
	

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				sb.append(get(i, j));
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
		throw new RuntimeException("Not implemented Teehee");
	}

	@Override
	public boolean remove(Object o) {
		throw new RuntimeException("Not implemented Teehee");
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return inside.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new RuntimeException("Not implemented Teehee");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new RuntimeException("Not implemented Teehee");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new RuntimeException("Not implemented Teehee");
	}

	@Override
	public void clear() {
		inside.clear();		
	}
	
}








