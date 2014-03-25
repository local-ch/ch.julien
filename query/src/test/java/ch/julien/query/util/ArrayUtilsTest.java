package ch.julien.query.util;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class ArrayUtilsTest {

	@Test
	public void testAllocator() {
		assertThat(ArrayUtils.arrayFactory(String.class).invoke(3)).isEqualTo(new String[3]);
	}
	
}
