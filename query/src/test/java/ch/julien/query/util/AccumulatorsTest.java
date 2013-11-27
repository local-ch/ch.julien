package ch.julien.query.util;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class AccumulatorsTest {

	@Test
	public void testSum() {
		// test basic number types
		assertThat(Accumulators.<Integer>sum().accumulate(1, 1)).isEqualTo(2);
		assertThat(Accumulators.<Double>sum().accumulate(1d, 1d)).isEqualTo(2d);
		assertThat(Accumulators.<Float>sum().accumulate(1f, 1f)).isEqualTo(2f);
	}
	
}
