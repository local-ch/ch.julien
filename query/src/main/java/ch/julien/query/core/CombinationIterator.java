package ch.julien.query.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ch.julien.common.contract.Check;
import ch.julien.common.datastructure.Tuple;

public class CombinationIterator<TSource, TSourceOther> extends AbstractIterator<TSource, Tuple<TSource, TSourceOther>> {
	private TSource nextElement = null;
	private boolean isOtherElementsCacheComplete = false;
	private final List<TSourceOther> cachedOtherElements = new ArrayList<TSourceOther>();
	private Iterator<TSourceOther> otherIterator;


	public CombinationIterator(Iterator<? extends TSource> parent, Iterator<TSourceOther> otherIterator) {
		super(parent);

		Check.notNull(otherIterator, "otherIterator");

		this.otherIterator = otherIterator;
	}

	@Override
	protected Tuple<TSource, TSourceOther> computeNext() {
		if ( ! this.otherIterator.hasNext()) {
			// reinitialize inner loop
			this.nextElement = null;
			this.otherIterator = this.cachedOtherElements.iterator();
			this.isOtherElementsCacheComplete = true;
		}

		// outer loop
		if (this.nextElement == null) {
			if (super.parent.hasNext()) {
				this.nextElement = super.parent.next();
			} else {
				return computationEnd();
			}
		}

		// inner loop
		if (this.otherIterator.hasNext()) {
			TSourceOther nextOtherElement = this.otherIterator.next();
			if ( ! this.isOtherElementsCacheComplete) {
				this.cachedOtherElements.add(nextOtherElement);
			}
			return new Tuple<TSource, TSourceOther>(this.nextElement, nextOtherElement);
		} else {
			return computationEnd();
		}
	}
}
