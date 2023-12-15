package com.darzalgames.darzalcommon.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BiMap <T, U> {

	private Map<T, U> mapT;
	private Map<U, T> mapU;
	
	public BiMap()
	{
		mapT = new HashMap<>();
		mapU = new HashMap<>();
	}
	
	public void add(T objectT, U objectU)
	{
		if (!mapT.containsKey(objectT) && !mapU.containsKey(objectU))
		{
			mapT.put(objectT, objectU);
			mapU.put(objectU, objectT);
		}
	}
	
	public T getFirstValue(U objectU)
	{
		if (mapU.containsKey(objectU))
		{
			return mapU.get(objectU);
		}
		return null;
	}
	
	public U getSecondValue(T objectT)
	{
		if (mapT.containsKey(objectT))
		{
			return mapT.get(objectT);
		}
		return null;
	}
	
	public Set<T> getFirstKeySet()
	{
		return mapT.keySet();
	}
	
	public Set<U> getSecondKeyset()
	{
		return mapU.keySet();
	}
	
	public boolean containsFirstValue(T objectT) {
		return mapT.containsKey(objectT);
	}
	
	public boolean containsSecondValue(U objectU) {
		return mapU.containsKey(objectU);
	}
	
	public boolean remove(T objectT, U objectU)
	{
		if (mapT.containsKey(objectT) && mapU.containsKey(objectU))
		{
			mapT.remove(objectT);
			mapU.remove(objectU);
			return true;
		}
		return false;
	}
	
	public boolean removeByFirstType(T objectT)
	{
		if (mapT.containsKey(objectT))
		{
			return remove(objectT, mapT.get(objectT));
		}
		return false;
	}
	
	public boolean removeBySecondType(U objectU)
	{
		if (mapU.containsKey(objectU))
		{
			return remove(mapU.get(objectU), objectU);
		}
		return false;
	}
	
}
