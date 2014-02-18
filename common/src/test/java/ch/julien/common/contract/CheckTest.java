package ch.julien.common.contract;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;


public class CheckTest {

	@Test
	public void testObjectNotNull() {
		Check.notNull(new Object(), null);
		Check.notNull(new Object(), "my message");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectNotNull_Fail1() {
		Check.notNull(null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testObjectNotNull_Fail2() {
		String message = "my message";
		try {
			Check.notNull(null, message);
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).startsWith(message);
			throw e;
		}
	}
	
	@Test
	public void testStringNotNullOrEmpty() {
		Check.notNullOrEmpty("string", null);
		Check.notNullOrEmpty("string", "my message");
	}
	
	@Test
	public void testStringNotNullOrEmpty_VariousFails() {
		Map<String, String> inputs = new HashMap<String, String>();
		inputs.put(null, null);
		inputs.put("","my message");
		inputs.put("\n", null);
		inputs.put("\t", null);
		
		for (Entry<String, String> input : inputs.entrySet()) {
			try {
				_testStringNotNullOrEmpty_Fail1(input.getKey(), input.getValue());
				fail("Expected exception: " + IllegalArgumentException.class.getName());
			} catch (IllegalArgumentException e) {
				// test passed
			} catch (RuntimeException e) {
				fail("Expected exception: " + IllegalArgumentException.class.getName());
			}
		}
	}
	
	private void _testStringNotNullOrEmpty_Fail1(String string, String paramName) {
		Check.notNullOrEmpty(string, paramName);
	}
	
	@Test
	public void testCollectionNotNullOrEmpty() {
		Check.notNullOrEmpty(Arrays.asList(new Object()), null);
		Check.notNullOrEmpty(Arrays.asList(new Object()), "my message");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCollectionNotNullOrEmpty_Fail1() {
		Check.notNullOrEmpty((Collection<Object>) null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCollectionNotNullOrEmpty_Fail2() {
		Check.notNullOrEmpty(new ArrayList<Object>(), "my message");
	}
	
}
