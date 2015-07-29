package ch.julien.common.monad;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class OptionTest {
	@Test
	public void testHasValueOfSome() {
		Option<Boolean> actual = Option.some(true);

		assertThat(actual.hasValue()).isTrue();
	}

	@Test
	public void testHasValueOfNone() {
		Option<Boolean> actual = Option.none();

		assertThat(actual.hasValue()).isFalse();
	}

	@Test
	public void testGetOfSome() {
		Option<Boolean> actual = Option.some(true);

		assertThat(actual.get()).isTrue();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetOfNone() {
		Option<Boolean> actual = Option.none();

		actual.get();
	}

	@Test
	public void testFromNullable() {
		Option<Boolean> some = Option.fromNullable(true);
		assertThat(some.hasValue()).isTrue();
		assertThat(some.get()).isTrue();
		assertThat(Option.fromNullable(null).hasValue()).isFalse();
	}

	@Test
	public void testGetOrElseOfNone() {
		Option<Boolean> actual = Option.none();

		assertThat(actual.getOrElse(true)).isTrue();
	}

	@Test
	public void testGetOrElseOfSome() {
		Option<Boolean> actual = Option.some(true);

		assertThat(actual.getOrElse(false)).isTrue();
	}

	@Test
	public void testOrOfNone() {
		Option<Boolean> actual = Option.none();
		Option<Boolean> or = Option.some(true);

		assertThat(actual.or(or)).isSameAs(or);
		assertThat(actual.or(or).get()).isTrue();
	}

	@Test
	public void testOrOfSome() {
		Option<Boolean> actual = Option.some(true);
		Option<Boolean> or = Option.some(false);

		assertThat(actual.or(or)).isSameAs(actual);
		assertThat(actual.or(or).get()).isTrue();
	}

	@Test
	public void testOrOptionOfNone() {
		Option<Boolean> actual = Option.none();

		assertThat(actual.orOption(true).get()).isTrue();
	}

	@Test
	public void testOrOptionOfSome() {
		Option<Boolean> actual = Option.some(true);

		assertThat(actual.orOption(false)).isSameAs(actual);
		assertThat(actual.orOption(false).get()).isTrue();
	}

	@Test
	public void testEqualsOfNone() {
		assertThat(Option.none().equals(Option.none())).isTrue();
		assertThat(Option.none().equals(Option.some(null))).isFalse();
		assertThat(Option.none().equals(Option.<Object>some(1))).isFalse();
		assertThat(Option.none().equals(new Object())).isFalse();
		assertThat(Option.none().equals(Option.some(null))).isFalse();
		assertThat(Option.none().equals(Option.some(1))).isFalse();

		assertThat(Option.none().hashCode()).isEqualTo(Option.none().hashCode());
		assertThat(Option.none().hashCode()).isNotEqualTo(Option.some(null).hashCode());
		assertThat(Option.none().hashCode()).isNotEqualTo(Option.<Object>some(1).hashCode());
		assertThat(Option.none().hashCode()).isNotEqualTo(new Object().hashCode());
		assertThat(Option.none().hashCode()).isNotEqualTo(Option.some(null).hashCode());
		assertThat(Option.none().hashCode()).isNotEqualTo(Option.some(1).hashCode());
	}

	@Test
	public void testEqualsOfSome() {
		assertThat(Option.some(1).equals(Option.some(1))).isTrue();
		assertThat(Option.some(null).equals(Option.some(null))).isTrue();
		assertThat(Option.some(1).equals(Option.some(2))).isFalse();
		assertThat(Option.some(1).equals(Option.some(null))).isFalse();
		assertThat(Option.some(null).equals(Option.some(1))).isFalse();
		assertThat(Option.some(1).equals(Option.none())).isFalse();
		assertThat(Option.some(null).equals(Option.none())).isFalse();

		assertThat(Option.some(1).hashCode()).isEqualTo(Option.some(1).hashCode());
		assertThat(Option.some(null).hashCode()).isEqualTo(Option.some(null).hashCode());
		assertThat(Option.some(1).hashCode()).isNotEqualTo(Option.some(2).hashCode());
		assertThat(Option.some(1).hashCode()).isNotEqualTo(Option.some(null).hashCode());
		assertThat(Option.some(null).hashCode()).isNotEqualTo(Option.some(1).hashCode());
		assertThat(Option.some(1).hashCode()).isNotEqualTo(Option.none().hashCode());
		assertThat(Option.some(null).hashCode()).isNotEqualTo(Option.none().hashCode());
	}
}
