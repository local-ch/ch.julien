package ch.julien.query.util;

import java.lang.reflect.Array;

import ch.julien.query.Traversable;

import ch.julien.common.delegate.Func;


/**
 * Common {@link Func} implementations for convenience
 */
public class Funcs {
	
	/**
	 * Cast the element to <code>clazz</code>
	 */
	public static final <T> Func<Object, T> to(final Class<T> clazz) {
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
	public static final Func<String, String> replaceAll(final String regex, final String replacement) {
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
	public static final Func<String, String> trimString() {
		return new Func<String, String>() {
			@Override
			public String invoke(String arg) {
				return arg.trim();
			}
		};
	}
	
	/**
	 * Parse the element to an {@link Integer}
	 * 
	 * @see Integer#parseInt(String)
	 */
	public static final Func<String, Integer> parseInteger() {
		return new Func<String, Integer>() {
			@Override
			public Integer invoke(String arg) {
				return Integer.parseInt(arg);
			}
		};
	}
	
	/**
	 * Parse the element to a {@link Double}
	 * 
	 * @see Double#parseDouble(String)
	 */
	public static final Func<String, Double> parseDouble() {
		return new Func<String, Double>() {
			@Override
			public Double invoke(String arg) {
				return Double.parseDouble(arg);
			}
		};
	}
	
	/**
	 * Array allocator as required for {@link Traversable#asArray(Func)}
	 * 
	 * @param clazz
	 *            The generic type of the array to allocate
	 */
	public static final <T> Func<Integer, T[]> allocator(final Class<T> clazz) {
		return new Func<Integer, T[]>() {
			@Override
			@SuppressWarnings("unchecked")
			public T[] invoke(Integer arg) {
				return (T[]) Array.newInstance(clazz, arg);
			}
		};
	}
	
}
