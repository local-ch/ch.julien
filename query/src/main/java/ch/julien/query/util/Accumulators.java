package ch.julien.query.util;

import ch.julien.common.delegate.Accumulator;


public class Accumulators {

	public static final <T extends Number> Accumulator<T, T> sum() {
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
	
	public static final <T extends Number> Accumulator<T, T> product() {
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
	
}
