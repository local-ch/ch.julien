package ch.julien.query.core;

import java.util.Iterator;

class TakeIterator<TSource> extends AbstractIterator<TSource, TSource> {
	private long remaining;

	public TakeIterator(Iterator<TSource> iterator, long count) {
		super(iterator);
		this.remaining = count;
	}

	@Override
	protected TSource computeNext() {
		if (super.parent.hasNext() && this.remaining-- > 0) {
			return this.parent.next();
		}

		return computationEnd();
	}
}
