package com.darzalgames.darzalcommon.hexagon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

class HexagonPrinterTest {

	@Test
	void toString_withFilledMap_printsOutCorrectly() {
		HexagonMap<String> map = new HexagonMap<>();
		map.put(new Hexagon(0, 0), "origin");
		map.put(new Hexagon(0, 1), "down");
		map.put(new Hexagon(1, 0), "rright");
		map.put(new Hexagon(1, 1), "downright");
		map.put(new Hexagon(0, -1), "up");
		map.put(new Hexagon(-1, 0), "left");
		map.put(new Hexagon(-1, -1), "upleft");

		String printResult = HexagonPrinter.toString(map);

		assertEquals(expectedBasicPrint(), printResult);
	}

	@Test
	void toString_withFilledMapAndCustomStringMapper_printsOutCorrectly() {
		HexagonMap<String> map = new HexagonMap<>();
		map.put(new Hexagon(0, 0), "origin");
		map.put(new Hexagon(0, 1), "down");
		map.put(new Hexagon(1, 0), "rright");
		map.put(new Hexagon(1, 1), "downright");
		map.put(new Hexagon(0, -1), "up");
		map.put(new Hexagon(-1, 0), "left");
		map.put(new Hexagon(-1, -1), "upleft");
		Function<String, String> stringMapper = String::toUpperCase;

		String printResult = HexagonPrinter.toString(map, stringMapper);

		assertEquals(expectedCustomPrint(), printResult);
	}

	@Test
	void toString_withFilledMapCustomStringMapperAndHexDetailMapper_printsOutCorrectly() {
		HexagonMap<String> map = new HexagonMap<>();
		map.put(new Hexagon(0, 0), "origin");
		map.put(new Hexagon(0, 1), "down");
		map.put(new Hexagon(1, 0), "rright");
		map.put(new Hexagon(1, 1), "downright");
		map.put(new Hexagon(0, -1), "up");
		map.put(new Hexagon(-1, 0), "left");
		map.put(new Hexagon(-1, -1), "upleft");
		Function<String, String> stringMapper = String::toUpperCase;
		Function<Hexagon, String> hexDetailMapper = hex -> {
			if (hex.equals(new Hexagon(-1, -1))) {
				return "ON";
			} else if (hex.equals(new Hexagon(0, 0))) {
				return "OFF";
			} else {
				return "";
			}
		};

		String printResult = HexagonPrinter.toString(map, stringMapper, hexDetailMapper);

		assertEquals(expectedCustomWithHexDetailsPrint(), printResult);
	}

	private static String expectedBasicPrint() {
		return """
				      --------                             \s
				    --        --                           \s
				  --            --                         \s
				--     upleft     --------                 \s
				  --  (-1, -1)  --        --               \s
				    --        --            --             \s
				      --------       up       --           \s
				    --        --  ( 0, -1)  --             \s
				  --            --        --               \s
				--      left      --------                 \s
				  --  (-1,  0)  --        --               \s
				    --        --            --             \s
				      --------     origin     --------     \s
				              --  ( 0,  0)  --        --   \s
				                --        --            -- \s
				                  --------     rright     --
				                --        --  ( 1,  0)  -- \s
				              --            --        --   \s
				            --      down      --------     \s
				              --  ( 0,  1)  --        --   \s
				                --        --            -- \s
				                  --------   downright    --
				                          --  ( 1,  1)  -- \s
				                            --        --   \s
				                              --------     \s
				""";
	}

	private static String expectedCustomPrint() {
		return """
				      --------                             \s
				    --        --                           \s
				  --            --                         \s
				--     UPLEFT     --------                 \s
				  --  (-1, -1)  --        --               \s
				    --        --            --             \s
				      --------       UP       --           \s
				    --        --  ( 0, -1)  --             \s
				  --            --        --               \s
				--      LEFT      --------                 \s
				  --  (-1,  0)  --        --               \s
				    --        --            --             \s
				      --------     ORIGIN     --------     \s
				              --  ( 0,  0)  --        --   \s
				                --        --            -- \s
				                  --------     RRIGHT     --
				                --        --  ( 1,  0)  -- \s
				              --            --        --   \s
				            --      DOWN      --------     \s
				              --  ( 0,  1)  --        --   \s
				                --        --            -- \s
				                  --------   DOWNRIGHT    --
				                          --  ( 1,  1)  -- \s
				                            --        --   \s
				                              --------     \s
				""";
	}

	private static String expectedCustomWithHexDetailsPrint() {
		return """
				      --------                             \s
				    --        --                           \s
				  --  (  ON  )  --                         \s
				--     UPLEFT     --------                 \s
				  --  (-1, -1)  --        --               \s
				    --        --            --             \s
				      --------       UP       --           \s
				    --        --  ( 0, -1)  --             \s
				  --            --        --               \s
				--      LEFT      --------                 \s
				  --  (-1,  0)  --        --               \s
				    --        --  ( OFF  )  --             \s
				      --------     ORIGIN     --------     \s
				              --  ( 0,  0)  --        --   \s
				                --        --            -- \s
				                  --------     RRIGHT     --
				                --        --  ( 1,  0)  -- \s
				              --            --        --   \s
				            --      DOWN      --------     \s
				              --  ( 0,  1)  --        --   \s
				                --        --            -- \s
				                  --------   DOWNRIGHT    --
				                          --  ( 1,  1)  -- \s
				                            --        --   \s
				                              --------     \s
				""";
	}

}
