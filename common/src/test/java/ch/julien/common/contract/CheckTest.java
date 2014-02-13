package ch.julien.common.contract;

import static org.fest.assertions.api.Assertions.assertThat;
import org.junit.Test;


public class CheckTest {

	@Test(expected=IllegalArgumentException.class)
	public void testNotNull_CorrectFail1() {
		Check.notNull(null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNotNull_CorrectFail2() {
		String message = "my message";
		try {
			Check.notNull(null, message);
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).startsWith(message);
			throw e;
		}
	}
	
}
