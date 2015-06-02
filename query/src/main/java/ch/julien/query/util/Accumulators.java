package ch.julien.query.util;

import ch.julien.common.delegate.Accumulator;
import ch.julien.query.core.Query;

/**
 * Common {@link Accumulator} implementations for convenience
 */
public class Accumulators {

	/**
	 * Append the strings to each other separated by <code>separator</code>
	 */
	public static Accumulator <String, String> joinOn(String separator) {
		final String _separator = (separator == null || separator.isEmpty()
				? ", "	// default separator
				: separator
				);
		return new Accumulator<String, String>() {
			@Override public String accumulate(String accumulate, String source) {
				return accumulate + _separator + source;
			}
		};
	}

	/**
	 * Sum up the numbers (elements)
	 */
	public static <T extends Number> Accumulator<T, T> sum() {
		return new Accumulator<T, T>() {
			@SuppressWarnings("unchecked")
			@Override public T accumulate(T accumulate, T source) {
				if (Byte.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Byte) ((Integer) (accumulate.byteValue() + source.byteValue())).byteValue();
				} else if (Double.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Double) (accumulate.doubleValue() + source.doubleValue());
				} else if (Float.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Float) (accumulate.floatValue() + source.floatValue());
				} else if (Integer.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Integer) (accumulate.intValue() + source.intValue());
				} else if (Long.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Long) (accumulate.longValue() + source.longValue());
				} else if (Short.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Short) ((Integer) (accumulate.shortValue() + source.shortValue())).shortValue();
				} else {
					throw new UnsupportedOperationException();
				}
			}
		};
	}

	/**
	 * Build the product of the numbers (elements)
	 */
	public static <T extends Number> Accumulator<T, T> product() {
		return new Accumulator<T, T>() {
			@SuppressWarnings("unchecked")
			@Override public T accumulate(T accumulate, T source) {
				if (Byte.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Byte) ((Integer) (accumulate.byteValue() * source.byteValue())).byteValue();
				} else if (Double.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Double) (accumulate.doubleValue() * source.doubleValue());
				} else if (Float.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Float) (accumulate.floatValue() * source.floatValue());
				} else if (Integer.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Integer) (accumulate.intValue() * source.intValue());
				} else if (Long.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Long) (accumulate.longValue() * source.longValue());
				} else if (Short.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Short) ((Integer) (accumulate.shortValue() * source.shortValue())).shortValue();
				} else {
					throw new UnsupportedOperationException();
				}
			}
		};
	}

	/**
	 * Build the union, i.e. a distinct concatenation of all Iterables
	 */
	public static <T> Accumulator<Iterable<T>, Iterable<T>> union() {
		return new Accumulator<Iterable<T>, Iterable<T>>() {
			@Override
			public Iterable<T> accumulate(Iterable<T> arg0, Iterable<T> arg1) {
				return Query.from(arg0).concat(arg1).distinct().asArrayList();
			}
		};
	}

	/**
	 * Build the intersection, i.e. the distinct elements that are contained in all Iterables
	 */
	public static <T> Accumulator<Iterable<T>, Iterable<T>> intersection() {
		return new Accumulator<Iterable<T>, Iterable<T>>() {
			@Override
			public Iterable<T> accumulate(Iterable<T> arg0, Iterable<T> arg1) {
				return Query.from(arg0).intersect(arg1).asArrayList();
			}
		};
	}
}
