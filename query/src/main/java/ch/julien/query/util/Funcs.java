package ch.julien.query.util;

import java.util.Map;

import ch.julien.common.datastructure.Tuple;
import ch.julien.common.delegate.Func;
import ch.julien.common.monad.Option;


/**
 * Common {@link Func} implementations for convenience
 */
public class Funcs {

	/**
	 * Cast the element to <code>clazz</code>
	 */
	public static <T> Func<Object, T> to(final Class<T> clazz) {
		return new Func<Object, T>() {
			@SuppressWarnings("unchecked")
			@Override public T invoke(Object arg) {
				return (T) arg;
			}
		};
	}

	/**
	 * Replace all matches for the <code>regex</code> with a <code>replacement</code>
	 *
	 * @see String#replaceAll(String, String)
	 */
	public static Func<String, String> replaceAll(final String regex, final String replacement) {
		return new Func<String, String>() {
			@Override
			public String invoke(String arg) {
				return arg.replaceAll(regex, replacement);
			}
		};
	}

	/**
	 * Trim the string
	 *
	 * @see String#trim()
	 */
	public static Func<String, String> trimString() {
		return new Func<String, String>() {
			@Override
			public String invoke(String arg) {
				return arg.trim();
			}
		};
	}

	/**
	 * To string
	 *
	 * @see Object#toString()
	 */
	public static Func<Object, String> toStringRepresentation() {
		return new Func<Object, String>() {
			@Override
			public String invoke(Object arg) {
				return arg.toString();
			}
		};
	}

	/**
	 * Parse the element to an {@link Integer}
	 *
	 * @see Integer#parseInt(String)
	 */
	public static Func<String, Integer> parseInteger() {
		return new Func<String, Integer>() {
			@Override
			public Integer invoke(String arg) {
				return Integer.parseInt(arg);
			}
		};
	}

	/**
	 * Parse the element to a {@link Long}
	 *
	 * @see Long#parseLong(String)
	 */
	public static Func<String, Long> parseLong() {
		return new Func<String, Long>() {
			@Override
			public Long invoke(String arg) {
				return Long.parseLong(arg);
			}
		};
	}

	/**
	 * Parse the element to a {@link Double}
	 *
	 * @see Double#parseDouble(String)
	 */
	public static Func<String, Double> parseDouble() {
		return new Func<String, Double>() {
			@Override
			public Double invoke(String arg) {
				return Double.parseDouble(arg);
			}
		};
	}

	/**
	 * The unmodified element
	 */
	public static <T> Func<T, T> self() {
		return new Func<T, T>() {
			@Override
			public T invoke(T arg) {
				return arg;
			}
		};
	}

	/**
	 * The {@link Map.Entry}'s key
	 *
	 * @see Map.Entry#getKey()
	 */
	public static <T> Func<Map.Entry<T, ?>, T> mapEntryKey() {
		return new Func<Map.Entry<T, ?>, T>() {
			@Override
			public T invoke(Map.Entry<T, ?> arg) {
				return arg.getKey();
			}
		};
	}

	/**
	 * The {@link Map.Entry}'s value
	 *
	 * @see Map.Entry#getValue()
	 */
	public static <T> Func<Map.Entry<?, T>, T> mapEntryValue() {
		return new Func<Map.Entry<?, T>, T>() {
			@Override
			public T invoke(Map.Entry<?, T> arg) {
				return arg.getValue();
			}
		};
	}

	/**
	 * The {@link Option}'s value
	 *
	 * @see Option#get()
	 */
	public static <T> Func<Option<T>, T> optionValue() {
		return new Func<Option<T>, T>() {
			@Override
			public T invoke(Option<T> arg) {
				return arg.get();
			}
		};
	}

	public static <T> Func<Tuple<T, ?>, T> firstOfTuple() {
		return new Func<Tuple<T, ?>, T>() {
			@Override
			public T invoke(Tuple<T, ?> arg) {
				return arg.getFirst();
			}
		};
	}

	public static <T> Func<Tuple<?, T>, T> secondOfTuple() {
		return new Func<Tuple<?, T>, T>() {
			@Override
			public T invoke(Tuple<?, T> arg) {
				return arg.getSecond();
			}
		};
	}
}
