package com.zalinius.darzalcommon.data;

import java.util.Objects;

public class Coord {
	
	public final int i;
	public final int j;
	
	public Coord() {
		this(0,0);
	}

	public Coord(Tuple<Integer, Integer> tuple) {
		this(tuple.e, tuple.f);
	}

	public Coord(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}
	
	public Tuple<Integer, Integer> toTuple(){
		return new Tuple<>(i, j);
	}

	@Override
	public String toString() {
		return "Coord [i=" + i + ", j=" + j + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(i, j);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		return i == other.i && j == other.j;
	}
	
	
	public int taxiDistance(Coord coord) {
		return Math.abs(i - coord.i) + Math.abs(j - coord.j);
	}
	
	public int kingDistance(Coord coord) {
		return Math.max(Math.abs(i - coord.i), Math.abs(j - coord.j));
	}
	

}
