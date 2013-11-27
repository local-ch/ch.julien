package ch.julien.query.util;

import ch.julien.common.delegate.Accumulator;

public class Accumulators {

	public static final <T extends Number> Accumulator<T, T> sum() {
		return new Accumulator<T, T>() {
			@SuppressWarnings("unchecked")
			@Override public T accumulate(T accumulate, T source) {
				Double sum = accumulate.doubleValue() + source.doubleValue();
				
				if (Double.class.isAssignableFrom(accumulate.getClass())) {
					return (T) sum;
				} else if (Float.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Float) sum.floatValue();
				} else if (Integer.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Integer) sum.intValue();
				} else if (Long.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Long) sum.longValue();
				} else if (Short.class.isAssignableFrom(accumulate.getClass())) {
					return (T) (Short) sum.shortValue();
				} else {
					throw new UnsupportedOperationException();
				}
			}
		};
	}
	
}
