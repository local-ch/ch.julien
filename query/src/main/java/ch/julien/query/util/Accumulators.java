package ch.julien.query.util;

import ch.julien.common.delegate.Accumulator;

public class Accumulators {

	public static final <T extends Number> Accumulator<T, T> sum() {
		return new Accumulator<T, T>() {
			@SuppressWarnings("unchecked")
			@Override public T accumulate(T accumulate, T source) {
				Double sum = accumulate.doubleValue() + source.doubleValue();
				return (T) sum;
			}
		};
	}
	
}
