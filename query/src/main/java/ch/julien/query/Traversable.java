package ch.julien.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import ch.julien.common.datastructure.Tuple;
import ch.julien.common.delegate.Accumulator;
import ch.julien.common.delegate.Action;
import ch.julien.common.delegate.EqualityComparator;
import ch.julien.common.delegate.Func;
import ch.julien.common.delegate.Predicate;
import ch.julien.common.monad.Option;
import ch.julien.query.util.ArrayUtils;

public interface Traversable<T> extends Iterable<T> {
	T aggregate(Accumulator<T, T> accumulator);
	<TAccumulate> TAccumulate aggregate(TAccumulate initial, Accumulator<TAccumulate, T> accumulator);
	<TAccumulate, TResult> TResult aggregate(TAccumulate initial, Accumulator<TAccumulate, T> accumulator,
		Func<TAccumulate, TResult> resultSelector);

	boolean all(Predicate<? super T> predicate);

	boolean any();
	boolean any(Predicate<? super T> predicate);

	/** @see ArrayUtils#arrayFactory(Class) */
	T[] asArray(Func<Integer, T[]> allocator);

	ArrayList<T> asArrayList();

	<TCollection extends Collection<T>> TCollection asCollection(TCollection collection);

	<TKey> HashMap<TKey, T> asHashMap(Func<T, TKey> keySelector);
	<TKey, TElement> HashMap<TKey, TElement> asHashMap(Func<T, TKey> keySelector, Func<T, TElement> elementSelector);

	HashSet<T> asHashSet();
	<TKey> HashSet<TKey> asHashSet(Func<T, TKey> keySelector);

	LinkedList<T> asLinkedList();

	<TKey> LinkedHashMap<TKey, T> asLinkedHashMap(Func<T, TKey> keySelector);
	<TKey, TElement> LinkedHashMap<TKey, TElement> asLinkedHashMap(Func<T, TKey> keySelector, Func<T, TElement> elementSelector);

	LinkedHashSet<T> asLinkedHashSet();
	<TKey> LinkedHashSet<TKey> asLinkedHashSet(Func<T, TKey> keySelector);

	Traversable<T> concat(Iterable<? extends T> appendant);
	Traversable<T> concat(T[] appendant);

	long count();

	Traversable<T> difference(Iterable<? extends T> other);
	Traversable<T> difference(Iterable<? extends T> other, EqualityComparator<T> equalityComparator);
	Traversable<T> distinct();
	Traversable<T> distinct(EqualityComparator<T> equalityComparator);

	Traversable<T> each(Action<T> action);

	Option<T> first();
	Option<T> first(Predicate<? super T> predicate);

	<TResult> Traversable<TResult> flat(Func<T, Iterable<TResult>> selector);

	Traversable<T> intersect(Iterable<? extends T> other);
	Traversable<T> intersect(Iterable<? extends T> other, EqualityComparator<T> equalityComparator);

	Option<T> last();
	Option<T> last(Predicate<? super T> predicate);

	<TResult> Traversable<TResult> map(Func<? super T, TResult> resultSelector);

	Traversable<T> reverse();

	Traversable<T> select(Predicate<? super T> predicate);

	Traversable<T> skip(long count);
	Traversable<T> take(long count);

	<TKey> OrderedTraversable<T, TKey> sortBy(Func<T, TKey> keySelector);
	<TKey> OrderedTraversable<T, TKey> sortBy(Func<T, TKey> keySelector, Comparator<TKey> comparator);

	<TKey> OrderedTraversable<T, TKey> sortByDescending(Func<T, TKey> keySelector);
	<TKey> OrderedTraversable<T, TKey> sortByDescending(Func<T, TKey> keySelector, Comparator<TKey> comparator);

	Traversable<T> union(Iterable<? extends T> appendant);
	Traversable<T> union(T[] appendant);

	<TOther> Traversable<Tuple<T, TOther>> zip(Iterable<TOther> other);
	<TOther> Traversable<Tuple<T, TOther>> zipAll(Iterable<TOther> other);
}
