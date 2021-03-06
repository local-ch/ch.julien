package ch.julien.common.monad;

public abstract class Option<T> {
	private static final Option NONE = new None();

	@SuppressWarnings("unchecked")
	public static <T> Option<T> none() {
		return (Option<T>) NONE;
	}

	public static <T> Option<T> some(T value) {
		return new Some<T>(value);
	}

	/**
	 * @return Same as {@code Option.some(value)} if {@code value} is not {@code null}, {@code Option.none()} otherwise.
	 */
	public static <T> Option<T> fromNullable(T value) {
		return value == null ? Option.<T>none() : some(value);
	}

	private Option() {}

	public abstract Boolean hasValue();

	public abstract T get();

	/**
	 * A value access method to obtain the value contained in this {@code Option}
	 * or an alternative. If a value is present, it is returned. If no value is
	 * present, the supplied alternative value is returned.
	 */
	public abstract T getOrElse(T value);

	public abstract Option<T> or(Option<? extends T> other);

	public abstract Option<T> orOption(T other);

	private static class None<T> extends Option<T> {
		@Override
		public Boolean hasValue() {
			return false;
		}

		@Override
		public T get() {
			throw new UnsupportedOperationException("Cannot resolve value on None.");
		}

		@Override
		public T getOrElse(T value) {
			return value;
		}

		@Override
		@SuppressWarnings("unchecked")
		public Option<T> or(Option<? extends T> other) {
			return (Option<T>)other;
		}

		@Override
		public Option<T> orOption(T other) {
			return some(other);
		}
	}

	private static class Some<T> extends Option<T> {
		private final T value;

		public Some(T value) {
			this.value = value;
		}

		@Override
		public Boolean hasValue() {
			return true;
		}

		@Override
		public T get() {
			return this.value;
		}

		@Override
		public T getOrElse(T value) {
			return get();
		}

		@Override
		public Option<T> or(Option<? extends T> other) {
			return this;
		}

		@Override
		public Option<T> orOption(T other) {
			return this;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof Some)) {
				return false;
			}

			Some some = (Some) o;

			return !(value != null ? !value.equals(some.value) : some.value != null);
		}

		@Override
		public int hashCode() {
			return value != null ? value.hashCode() : 0;
		}
	}
}
