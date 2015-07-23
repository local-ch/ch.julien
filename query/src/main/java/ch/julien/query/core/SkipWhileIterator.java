package ch.julien.query.core;

import java.util.Iterator;

import ch.julien.common.contract.Check;
import ch.julien.common.delegate.Predicate;

public class SkipWhileIterator<TSource> extends AbstractIterator<TSource, TSource> {
	private boolean isSkippingStopped = false;
	private final Predicate<? super TSource> predicate;

	public SkipWhileIterator(Iterator<? extends TSource> parent, Predicate<? super TSource> predicate) {
		super(parent);

		Check.notNull(predicate, "predicate must not be null.");

		this.predicate = predicate;
	}

	@Override
	protected TSource computeNext() {
		while (super.parent.hasNext()) {
			TSource next = this.parent.next();

			if (!this.predicate.invoke(next) || this.isSkippingStopped) {
				this.isSkippingStopped = true;
				return next;
			}
		}

		return computationEnd();
	}
}
