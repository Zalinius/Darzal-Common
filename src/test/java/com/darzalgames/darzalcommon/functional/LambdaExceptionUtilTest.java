package com.darzalgames.darzalcommon.functional;

import static com.darzalgames.darzalcommon.functional.LambdaExceptionUtil.rethrowConsumer;
import static com.darzalgames.darzalcommon.functional.LambdaExceptionUtil.rethrowFunction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class LambdaExceptionUtilTest {

	@Test
	void testConsumer() throws MyTestException {
		assertThrows(MyTestException.class, () -> 
		Stream.of((String)null).forEach(
				rethrowConsumer(this::checkValue)
				));
	}

	private void checkValue(String value) throws MyTestException {
		if(value==null) {
			throw new MyTestException();
		}
	}

	private class MyTestException extends RuntimeException {
		private static final long serialVersionUID = 1L; }

	@Test
	void testConsumerRaisingExceptionInTheMiddle() {
		MyLongAccumulator accumulator = new MyLongAccumulator();
		try {
			Stream.of(2L, 3L, 4L, null, 5L).forEach(rethrowConsumer(s -> accumulator.add(s)));
		} catch (MyTestException e) {
			assertEquals(9L, accumulator.acc);
		}
	}

	private class MyLongAccumulator {
		private long acc = 0;
		public void add(Long value) throws MyTestException {
			if(value==null) {
				throw new MyTestException();
			}
			acc += value;
		}
	}

	@Test
	void testFunction() throws MyTestException {
		List<Integer> sizes = Stream.of("ciao", "hello").<Integer>map(rethrowFunction(this::transform)).toList();
		assertEquals(2, sizes.size());
		assertEquals(4, sizes.get(0).intValue());
		assertEquals(5, sizes.get(1).intValue());
	}

	private Integer transform(String value) throws MyTestException {
		if(value==null) {
			throw new MyTestException();
		}
		return value.length();
	}

	@Test
	void testFunctionRaisingException() throws MyTestException {
		assertThrows(MyTestException.class, () ->
		Stream.of("ciao", null, "hello").<Integer>map(rethrowFunction(this::transform)).toList());
	}

}
