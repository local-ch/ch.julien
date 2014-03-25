package ch.julien.query.util;

import java.lang.reflect.Array;

import ch.julien.common.delegate.Func;
import ch.julien.query.Traversable;

public class ArrayUtils {

	/**
	 * 1D-Array allocator as required for {@link Traversable#asArray(Func)}
	 * 
	 * @param clazz
	 *            The generic type of the array to allocate
	 */
	public static final <T> Func<Integer, T[]> arrayFactory(final Class<T> clazz) {
		return new Func<Integer, T[]>() {
			@Override
			@SuppressWarnings("unchecked")
			public T[] invoke(Integer arg) {
				return (T[]) Array.newInstance(clazz, arg);
			}
		};
	}
	
}
