package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.darzalgames.darzalcommon.data.ListFactory;

class HexagonTest {

	@ParameterizedTest
	@CsvSource({
			"0, 0,		0, 0",
			"1, 1,		1, 1",
			"2, -1,		2, 0",
			"-1, 1,		-1, 0",
	})
	void axialToOffsetCoordinates_variousCoordinates_returnsTheCorrectlyConvertedCoordinates(int hexagonQ, int hexagonR, int expectedColumn, int expectedRow) {

		Hexagon result = new Hexagon(hexagonQ, hexagonR);

		assertEquals(expectedColumn, result.getColumn());
		assertEquals(expectedRow, result.getRow());
	}

	@ParameterizedTest
	@CsvSource({
			"0, 0,		0, 0",
			"1, 1,		1, 1",
			"2, 0,		2, -1",
			"-1, 0,		-1, 1",
	})
	void offsetToAxialCoordinates_variousCoordinates_returnsTheCorrectlyConvertedCoordinates(int column, int row, int expectedQ, int expectedR) {

		Hexagon result = Hexagon.makeHexagonFromOffsetCoordinates(row, column);

		assertEquals(expectedQ, result.q());
		assertEquals(expectedR, result.r());
	}

	@ParameterizedTest
	@CsvSource({
			"0,  0,	0", // s = -q-r
			"3, -3, 0",
			"-5, -2, 7",
			"4, 9, -13",
			"-8, 1, 7",
			"0, 5, -5",
			"-8, 0, 8"
	})
	void getS_isCalculatedCorrectly(int q, int r, int expectedS) {
		Hexagon hexagon = new Hexagon(q, r);

		assertEquals(expectedS, hexagon.s());
	}

	@ParameterizedTest
	@CsvSource({ "0, 0", "2, -2", "2, 0", "-1, 1" })
	void getAllHexagonNeighboursOf_variousHexagons_alwaysReturns6Hexagons(int hexagonQ, int hexagonR) {
		Hexagon hexagon = new Hexagon(hexagonQ, hexagonR);
		Set<Hexagon> neighbours = hexagon.getNeighbours();

		assertEquals(6, neighbours.size());
	}

	@Test
	void equals_sameCoordinates_isTrue() {
		Hexagon hexagon1 = new Hexagon(3, -5);
		Hexagon hexagon2 = new Hexagon(3, -5);

		assertEquals(hexagon1, hexagon2);
	}

	@Test
	void equals_differentCoordinates_isFalse() {
		Hexagon hexagon1 = new Hexagon(3, 20);
		Hexagon hexagon2 = new Hexagon(3, -5);

		assertNotEquals(hexagon1, hexagon2);
	}

	@Test
	void equals_sameHexagon_isTrue() {
		Hexagon hexagon1 = new Hexagon(3, -5);

		assertEquals(hexagon1, hexagon1);
	}

	@Test
	void equals_withNull_isFalse() {
		Hexagon hexagon1 = new Hexagon(3, 20);

		boolean nullComparisonResult = hexagon1.equals(null);

		assertFalse(nullComparisonResult);
	}

	@Test
	void equals_withNonHexagon_isFalse() {
		Hexagon hexagon1 = new Hexagon(3, 20);
		String hexagonString = "hexagon";

		assertNotEquals(hexagon1, hexagonString);
	}

	@Test
	void hashCode_sameHexagon_isEqual() {
		Hexagon hexagon1 = new Hexagon(3, -5);
		Hexagon hexagon2 = new Hexagon(3, -5);

		assertEquals(hexagon1.hashCode(), hexagon2.hashCode());
	}

	@Test
	void hashCode_sameCoordinates_isEqual() {
		Hexagon hexagon = new Hexagon(2, -8);

		assertEquals(hexagon.hashCode(), hexagon.hashCode());
	}

	@ParameterizedTest
	@CsvSource({
			"0,0, 0,0, 0",
			"0,1, 0,0, 1",
			"0,0, 1,0, 1",
			"3,-3, 0,0, 3",
			"-4,1, -3,3, 3",
			"-2,4, 1,-3, 7"
	})
	void computeDistance_variousPairsOfHexes_returnsCorrectDistance(int q1, int r1, int q2, int r2, int expectedDistance) {
		Hexagon h1 = new Hexagon(q1, r1);
		Hexagon h2 = new Hexagon(q2, r2);

		int distance = Hexagon.computeDistance(h1, h2);

		assertEquals(expectedDistance, distance);
	}

	@ParameterizedTest
	@CsvSource({
			"0,0, 0,0",
			"1,0, -1,0",
			"3,-4, 0,1",
			"-4,1, -3,8",
			"-2,4, 1,-3"
	})
	void areAdjacent_onPairsOfNonAdjacentHexes_returnsFalse(int q1, int r1, int q2, int r2) {
		Hexagon h1 = new Hexagon(q1, r1);
		Hexagon h2 = new Hexagon(q2, r2);

		assertFalse(Hexagon.areAdjacent(h1, h2));
	}

	@ParameterizedTest
	@CsvSource({
			"0,0, -1,0",
			"1,0, 0,0",
			"2,1, 3,0",
			"-3,0, -2,0"
	})
	void areAdjacent_onPairsOfAdjacentHexes_returnsTrue(int q1, int r1, int q2, int r2) {
		Hexagon h1 = new Hexagon(q1, r1);
		Hexagon h2 = new Hexagon(q2, r2);

		assertTrue(Hexagon.areAdjacent(h1, h2));
	}

	@Test
	void collectionsSort_onAListOfHexagons_correctlySortsThemUsingBuiltInComparator() {
		Hexagon h0 = new Hexagon(0, 0);
		Hexagon h1 = new Hexagon(-3, 2);
		Hexagon h2 = new Hexagon(3, -4);
		Hexagon h3 = new Hexagon(0, -1);
		Hexagon h4 = new Hexagon(2, 0);
		List<Hexagon> list = ListFactory.of(h0, h1, h2, h3, h4);

		Collections.sort(list);

		assertEquals(h1, list.get(0));
		assertEquals(h3, list.get(1));
		assertEquals(h0, list.get(2));
		assertEquals(h4, list.get(3));
		assertEquals(h2, list.get(4));
	}

	@ParameterizedTest
	@CsvSource({
			"0, 0,		0, 0,	0",
			"1, 1,		1, 1,	0",
			"2, 1,		2, 0,	1",
			"1, 0,		1, 1,	-1",
			"1, 0,		2, 0,	-1",
			"2, 0,		1, 0,	1",
			"-2, 0,		-1, 0,	-1",
	})
	void topToBottomLeftToRightComparator_variousOffsetCoordinateComparisons_providesExpectedValue(int hexagon1Column, int hexagon1Row, int hexagon2Column, int hexagon2Row, int expectedComparison) {
		Hexagon hexagon1 = Hexagon.makeHexagonFromOffsetCoordinates(hexagon1Row, hexagon1Column);
		Hexagon hexagon2 = Hexagon.makeHexagonFromOffsetCoordinates(hexagon2Row, hexagon2Column);

		assertEquals(expectedComparison, Hexagon.topToBottomLeftToRightComparator.compare(hexagon1, hexagon2));
	}

	@ParameterizedTest
	@CsvSource({
			"0, 0,		0, 0,	0",
			"1, 1,		1, 1,	0",
			"-1, 0,		-1, 1,	-1",
			"1, 0,		1, -1,	1",
			"-2, 2,		-1, 2,	-1",
			"1, 1,		0, 1,	1",
	})
	void topToBottomLeftToRightComparator_variousAxialCoordinateComparisons_providesExpectedValue(int hexagon1Q, int hexagon1R, int hexagon2Q, int hexagon2R, int expectedComparison) {
		Hexagon hexagon1 = new Hexagon(hexagon1Q, hexagon1R);
		Hexagon hexagon2 = new Hexagon(hexagon2Q, hexagon2R);

		assertEquals(expectedComparison, Hexagon.topToBottomLeftToRightComparator.compare(hexagon1, hexagon2));
	}

}
