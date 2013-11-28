package ch.julien.query.util;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;


public class AccumulatorsTest {

	@Test
	public void testJoinOn() {
		assertThat(Accumulators.joinOn(null).accumulate("a", "b")).isEqualTo("a, b");
		assertThat(Accumulators.joinOn(" ").accumulate("a", "b")).isEqualTo("a b");
		assertThat(Accumulators.joinOn(" , ").accumulate("a", "b")).isEqualTo("a , b");
	}
	
	@Test
	public void testSum() {
		// test supported number types
		assertThat(Accumulators.<Byte>sum().accumulate((byte)2, (byte)3)).isEqualTo((byte)5);
		assertThat(Accumulators.<Double>sum().accumulate(2d, 3d)).isEqualTo(5d);
		assertThat(Accumulators.<Float>sum().accumulate(2f, 3f)).isEqualTo(5f);
		assertThat(Accumulators.<Integer>sum().accumulate(2, 3)).isEqualTo(5);
		assertThat(Accumulators.<Long>sum().accumulate(2l, 3l)).isEqualTo(5l);
		assertThat(Accumulators.<Short>sum().accumulate((short)2, (short)3)).isEqualTo((short)5);
	}
	
	@Test
	public void testSum_unsupportedTypes() {
		List<Number> unsupportedInputs = Arrays.asList(
				new BigInteger("1"),
				new BigDecimal(1),
				new AtomicInteger(1),
				new AtomicLong(1)
				);
		for (Number input : unsupportedInputs) {
			try {
				_testSum_unsupportedTypes(input);
				fail("Expected exception with type " + input.getClass().getName() + ": " + UnsupportedOperationException.class.getName());
			} catch (UnsupportedOperationException e) {
				// test passed
			} catch (RuntimeException e) {
				fail("Unexpected exception with type " + input.getClass().getName() + ", expected<" + UnsupportedOperationException.class.getName() + "> but was<" + e.getClass().getName() + ">");
			}
		}
	}
	
	private <T extends Number> void _testSum_unsupportedTypes(T input) {
		Accumulators.<T>sum().accumulate(input, input);
	}
	
	@Test
	public void testProduct() {
		// test supported number types
		assertThat(Accumulators.<Byte>product().accumulate((byte)2, (byte)3)).isEqualTo((byte)6);
		assertThat(Accumulators.<Double>product().accumulate(2d, 3d)).isEqualTo(6d);
		assertThat(Accumulators.<Float>product().accumulate(2f, 3f)).isEqualTo(6f);
		assertThat(Accumulators.<Integer>product().accumulate(2, 3)).isEqualTo(6);
		assertThat(Accumulators.<Long>product().accumulate(2l, 3l)).isEqualTo(6l);
		assertThat(Accumulators.<Short>product().accumulate((short)2, (short)3)).isEqualTo((short)6);
	}
	
	@Test
	public void testProduct_unsupportedTypes() {
		List<Number> unsupportedInputs = Arrays.asList(
				new BigInteger("1"),
				new BigDecimal(1),
				new AtomicInteger(1),
				new AtomicLong(1)
				);
		for (Number input : unsupportedInputs) {
			try {
				_testProduct_unsupportedTypes(input);
				fail("Expected exception with type " + input.getClass().getName() + ": " + UnsupportedOperationException.class.getName());
			} catch (UnsupportedOperationException e) {
				// test passed
			} catch (RuntimeException e) {
				fail("Unexpected exception with type " + input.getClass().getName() + ", expected<" + UnsupportedOperationException.class.getName() + "> but was<" + e.getClass().getName() + ">");
			}
		}
	}
	
	private <T extends Number> void _testProduct_unsupportedTypes(T input) {
		Accumulators.<T>product().accumulate(input, input);
	}
	
}
