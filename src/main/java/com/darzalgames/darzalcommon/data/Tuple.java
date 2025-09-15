package com.darzalgames.darzalcommon.data;

/**
 * A class representing a tuple of values, of potentially different types
 * @param <E> The first type
 * @param <F> The second type
 * @param e   the first element of the tuple
 * @param f   the second element of the tuple
 */
public record Tuple<E, F>(E e, F f) {}