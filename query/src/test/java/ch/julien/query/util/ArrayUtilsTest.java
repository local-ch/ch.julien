package ch.julien.query.util;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class ArrayUtilsTest {

	@Test
	public void testArrayFactory() {
		String[] expected1dArray = new String[] {null, null, null};
		assertThat(ArrayUtils.arrayFactory(String.class).invoke(3)).isEqualTo(expected1dArray);
		
		String[][] expected2dArray = new String[][] {null, null, null};
		assertThat(ArrayUtils.arrayFactory(String[].class).invoke(3)).isEqualTo(expected2dArray);
	}
	
}
