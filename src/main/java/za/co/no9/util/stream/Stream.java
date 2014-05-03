package za.co.no9.util.stream;

import za.co.no9.lang.Predicate;
import za.co.no9.lang.PredicateUtils;
import za.co.no9.util.FilteredIterator;
import za.co.no9.util.IteratorUtils;
import za.co.no9.util.Optional;

import java.util.Iterator;

import static za.co.no9.lang.PredicateUtils.andPredicate;

public class Stream<T> {
    private final java.util.Iterator<T> iterator;
    public Predicate<T> filterPredicate = PredicateUtils.<T>truePredicate();

    public Stream(java.util.Iterator<T> iterator) {
        this.iterator = iterator;
    }

    public static <T> Stream<T> create(Iterator<T> iterator) {
        return new Stream<T>(iterator);
    }

    public Stream<T> filter(Predicate<T> predicate) {
        filterPredicate = andPredicate(filterPredicate, predicate);

        return this;
    }

    public Optional<T> findAny(final Predicate<T> matchPredicate) {
        return IteratorUtils.first(new FilteredIterator<T>(iterator, andPredicate(filterPredicate, matchPredicate)));
    }

    public long count() {
        return IteratorUtils.size(new FilteredIterator<T>(iterator, filterPredicate));
    }

    public Iterator<T> toIterator() {
        return new FilteredIterator<T>(iterator, filterPredicate);
    }
}
