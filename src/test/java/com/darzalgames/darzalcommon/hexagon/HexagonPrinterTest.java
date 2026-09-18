package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HexagonPrinterTest {

	@Test
	void toString_withFilledMap_printsOutCorrectly() {
		HexagonMap<String> map = new HexagonMap<>();
		map.put(new Hexagon(0, 0), "origin");
		map.put(new Hexagon(0, 1), "down");
		map.put(new Hexagon(1, 0), "right");
		map.put(new Hexagon(1, 1), "downright");
		map.put(new Hexagon(0, -1), "up");
		map.put(new Hexagon(-1, 0), "left");
		map.put(new Hexagon(-1, -1), "upleft");

		String printResult = HexagonPrinter.toString(map);

		assertEquals(expectedPrint(), printResult);
	}

	private static String expectedPrint() {
		return """
				      --------                             \s
				    --        --                           \s
				  --            --                         \s
				--     upleft     --------                 \s
				  --  (-1)(-1)  --        --               \s
				    --        --            --             \s
				      --------       up       --           \s
				    --        --  ( 0)(-1)  --             \s
				  --            --        --               \s
				--      left      --------                 \s
				  --  (-1)( 0)  --        --               \s
				    --        --            --             \s
				      --------     origin     --------     \s
				              --  ( 0)( 0)  --        --   \s
				                --        --            -- \s
				                  --------     right      --
				                --        --  ( 1)( 0)  -- \s
				              --            --        --   \s
				            --      down      --------     \s
				              --  ( 0)( 1)  --        --   \s
				                --        --            -- \s
				                  --------   downright    --
				                          --  ( 1)( 1)  -- \s
				                            --        --   \s
				                              --------     \s
				""";
	}

}
