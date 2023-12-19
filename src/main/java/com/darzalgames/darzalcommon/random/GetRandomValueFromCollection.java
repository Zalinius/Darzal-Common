package com.darzalgames.darzalcommon.random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GetRandomValueFromCollection {

	private GetRandomValueFromCollection() {}

	public static <T> T next(Collection<T> toGetFrom, IntegerSequence ints)
	{
		if (toGetFrom.isEmpty())
			return null;
		
		int index = ints.nextInt(toGetFrom.size());
		Iterator<T> it = toGetFrom.iterator();
		while (index > 0)
		{
			it.next();
			--index;
		}
		return it.next();
	}
	
	public static <T> T next(ArrayList<T> toGetFrom, IntegerSequence ints)
	{
		if (toGetFrom.isEmpty())
			return null;
		
		int index = ints.nextInt(toGetFrom.size());
		return toGetFrom.get(index);
	}

	
	public static <T> T nextUnique(List<T> toGetFrom, Collection<T> toExclude, IntegerSequence ints)
	{		
		T nextValue;
		do {
			nextValue = next(toGetFrom, ints);
		} while (toExclude.contains(nextValue));
		return nextValue;
	}
	
}
