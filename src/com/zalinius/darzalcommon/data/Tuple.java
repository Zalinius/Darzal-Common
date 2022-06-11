package com.zalinius.darzalcommon.data;

import java.util.Objects;

public class Tuple <E,F> {

	public final E e;
	public final F f;
	
	public Tuple(E e, F f) {
		this.e = e;
		this.f = f;
	}

	public E getE() {
		return e;
	}
	public F getF() {
		return f;
	}

	@Override
	public int hashCode() {
		return Objects.hash(e, f);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple<?, ?> other = (Tuple<?, ?>) obj;
		return Objects.equals(e, other.e) && Objects.equals(f, other.f);
	}	
	
	
}
