package com.darzalgames.darzalcommon.random;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import com.darzalgames.darzalcommon.functional.Do;

class GetRandomValueFromCollectionTest {

	@Test
	void getRandomUnique_hasNoDuplicates() {
		IntegerSequence intSeq = new RandomIntegerSequence();

		Collection<String> usedNames = new ArrayList<>();
		usedNames.add("testOne");
		usedNames.add("testTwo");

		ArrayList<String> allNames = new ArrayList<>();
		allNames.add("testOne");
		allNames.add("testTwo");
		allNames.add("testThree");
		allNames.add("testFour");
		allNames.add("testFive");
		allNames.add("testSix");
		
		Do.xTimes(3000, ()->{
			String randomName = GetRandomValueFromCollection.nextUnique(allNames, usedNames, intSeq);
			assertFalse(usedNames.contains(randomName));
		});
	}
}
