package com.darzalgames.darzalcommon.data;

import java.util.Objects;

/**
 * A class representing a tuple of values, of potentially different types
 * @param <E> The first type
 * @param <F> The second type
 */
public class Tuple <E,F> {

	//TODO make these private at version 0.7.0
	public final E e;
	public final F f;

	/**
	 * Creates a tuple with the two specified values
	 * @param e the first value
	 * @param f the second value
	 */
	public Tuple(E e, F f) {
		this.e = e;
		this.f = f;
	}

	/**
	 * Gets the first value of the tuple
	 * @return The value of the first type
	 */
	public E e() {
		return e;
	}

	/**
	 * Gets the second value of the tuple
	 * @return The value of the second type
	 */
	public F f() {
		return f;
	}

	//TODO remove this at version 0.7.0
	@Deprecated(forRemoval = true)
	/**
	 * @deprecated use e() instead
	 */
	public E getE() {
		return e;
	}

	//TODO remove this at version 0.7.0
	@Deprecated(forRemoval = true)
	/**
	 * @deprecated use f() instead
	 */
	public F getF() {
		return f;
	}

	@Override
	public String toString() {
		return "Tuple: [" + e + ", " + f + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(e, f);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Tuple<?, ?> other = (Tuple<?, ?>) obj;
		return Objects.equals(e, other.e) && Objects.equals(f, other.f);
	}


}
