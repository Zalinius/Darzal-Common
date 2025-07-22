package com.darzalgames.darzalcommon.data;

/**
 * A class representing a tuple of values, of potentially different types
 * @param <E> The first type
 * @param <F> The second type
 */
public record Tuple <E,F> (E e, F f) {}