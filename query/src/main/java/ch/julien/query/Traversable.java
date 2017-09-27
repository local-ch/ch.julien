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
	T aggregate(Accumulator<T, ? super T> accumulator);
	<TAccumulate> TAccumulate aggregate(TAccumulate initial, Accumulator<TAccumulate, ? super T> accumulator);
	<TAccumulate, TResult> TResult aggregate(TAccumulate initial, Accumulator<TAccumulate, ? super T> accumulator, Func<TAccumulate, TResult> resultSelector);

	boolean all(Predicate<? super T> predicate);

	boolean any();
	boolean any(Predicate<? super T> predicate);

	T[] asArray(Class<T> clazz);
	/** @see ArrayUtils#arrayFactory(Class) */
	T[] asArray(Func<Integer, T[]> allocator);

	ArrayList<T> asArrayList();

	<TCollection extends Collection<? super T>> TCollection asCollection(TCollection collection);

	<TKey> HashMap<TKey, T> asHashMap(Func<? super T, TKey> keySelector);
	<TKey, TElement> HashMap<TKey, TElement> asHashMap(Func<? super T, TKey> keySelector, Func<? super T, TElement> elementSelector);

	HashSet<T> asHashSet();
	<TKey> HashSet<TKey> asHashSet(Func<? super T, TKey> keySelector);

	LinkedList<T> asLinkedList();

	<TKey> LinkedHashMap<TKey, T> asLinkedHashMap(Func<? super T, TKey> keySelector);
	<TKey, TElement> LinkedHashMap<TKey, TElement> asLinkedHashMap(Func<? super T, TKey> keySelector, Func<? super T, TElement> elementSelector);

	LinkedHashSet<T> asLinkedHashSet();
	<TKey> LinkedHashSet<TKey> asLinkedHashSet(Func<? super T, TKey> keySelector);

	Traversable<T> concat(Iterable<? extends T> appendant);
	Traversable<T> concat(T... appendant);

	long count();

	Traversable<T> difference(Iterable<? extends T> other);
	Traversable<T> difference(Iterable<? extends T> other, EqualityComparator<T> equalityComparator);
	Traversable<T> distinct();
	Traversable<T> distinct(EqualityComparator<? super T> equalityComparator);

	Traversable<T> each(Action<? super T> action);

	Option<T> first();
	Option<T> first(Predicate<? super T> predicate);

	<TResult> Traversable<TResult> flat(Func<? super T, Iterable<TResult>> selector);

	Traversable<T> intersect(Iterable<? extends T> other);
	Traversable<T> intersect(Iterable<? extends T> other, EqualityComparator<T> equalityComparator);

	Option<T> last();
	Option<T> last(Predicate<? super T> predicate);

	<TResult> Traversable<TResult> map(Func<? super T, TResult> resultSelector);

	Traversable<T> reverse();

	Traversable<T> select(Predicate<? super T> predicate);

	Traversable<T> skip(long count);
	Traversable<T> skipWhile(Predicate<? super T> predicate);

	Traversable<T> take(long count);

	<TKey> OrderedTraversable<T, TKey> sortBy(Func<? super T, TKey> keySelector);
	<TKey> OrderedTraversable<T, TKey> sortBy(Func<? super T, TKey> keySelector, Comparator<TKey> comparator);

	<TKey> OrderedTraversable<T, TKey> sortByDescending(Func<? super T, TKey> keySelector);
	<TKey> OrderedTraversable<T, TKey> sortByDescending(Func<? super T, TKey> keySelector, Comparator<TKey> comparator);

	Traversable<T> union(Iterable<? extends T> appendant);
	Traversable<T> union(T... appendant);

	<TOther> Traversable<Tuple<T, TOther>> zip(Iterable<TOther> other);
	<TOther> Traversable<Tuple<T, TOther>> zipAll(Iterable<TOther> other);

	<TOther> Traversable<Tuple<T, TOther>> combine(Iterable<TOther> other);
}
