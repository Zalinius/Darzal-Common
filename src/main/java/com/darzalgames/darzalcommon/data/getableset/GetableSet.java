package com.darzalgames.darzalcommon.data.getableset;

import java.util.Set;

/**
 * A Set with a get() function similar to maps
 * @param <E> the type of elements maintained by this set
 */
public interface GetableSet<E> extends Set<E> {

	/**
	 * Gets the object in the set equal to the specified element
	 * @param e element to get from the set
	 * @return the element in the set equal to the specified input element, or null if there is no such element
	 * @throws ClassCastException   if the type of the specified element is incompatible with this set (optional)
	 * @throws NullPointerException if the specified element is null and this set does not permit null elements (optional)
	 */
	E get(E e);

}
