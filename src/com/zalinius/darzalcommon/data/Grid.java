package com.zalinius.darzalcommon.data;

import java.util.ArrayList;
import java.util.List;

/**
 * A generic rectangular grid data structure of a fixed size
 * @param <E> The Generic type the grid contains
 */
public class Grid<E> {

	private List<E> inside;
	public final int width;
	public final int height;
	public final E   defaultValue;
	
	public Grid(int width, int height) {
		this(width, height, null);
	}

	public Grid(int width, int height, E defaultValue) {
		inside = new ArrayList<>(width*height);
		for (int i = 0; i < width*height; i++) {
			inside.add(null);
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

}








