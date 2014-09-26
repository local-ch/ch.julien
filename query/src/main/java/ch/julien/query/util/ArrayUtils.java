package ch.julien.query.util;

import java.lang.reflect.Array;

import ch.julien.common.delegate.Func;
import ch.julien.query.Traversable;

public class ArrayUtils {

	/**
	 * Array allocator as required for {@link Traversable#asArray(Func)}.
	 * <p/>
	 * <pre>
	 * ArrayUtils.arrayFactory(String.class);		// facory for 1D String arrays: String[]
	 * ArrayUtils.arrayFactory(String[].class);	// facory for 2D String arrays: String[][]
	 * // etc...
	 * </pre>
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
