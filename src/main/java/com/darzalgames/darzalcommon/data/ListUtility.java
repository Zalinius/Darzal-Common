package com.darzalgames.darzalcommon.data;

import java.util.List;

import com.darzalgames.darzalcommon.math.SimpleMath;

public class ListUtility {
	
	/**
	 * Given a list of values, get the index of the element "paired with" the element at the provided index.
	 * e.g. the elements at 0 and 1 are paired, 2 and 3 are paired, etc.  In an odd-sized list, the middle-most value is paired with itself.
	 * @param values
	 * @param index
	 * @return
	 */
	public static <E> int getClosestPairIndex(List<E> values, int index) {
		if (values.isEmpty()) {
			return -1;
		}
		
		int shift = getPairShiftFromIndex(index);
		if (!SimpleMath.isEven(values.size())) {
			int halfWayIndex = values.size()/2;
			if (index > halfWayIndex) {
				shift = -shift;
			} else if (index == halfWayIndex) {
				shift = 0; // the middle-most pairs with itself
			}
		}

		if (values.size() == 3) {
			shift = adjustShiftForListSize3(index);
		}
		
		return shift + index;
	}
	
	private static int adjustShiftForListSize3(int index) {
		switch (index) {
		case 0:
			return 2;
		case 1:
			return 0;
		case 2:
			return -2;
		}
		return index;
	}
	
	private static int getPairShiftFromIndex(int index) {
		return SimpleMath.isEven(index) ? 1 : -1;
	}
}
