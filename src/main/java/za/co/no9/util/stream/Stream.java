package za.co.no9.util.stream;

import za.co.no9.lang.Predicate;
import za.co.no9.util.FilteredIterator;
import za.co.no9.util.IteratorUtils;
import za.co.no9.util.MapIterator;
import za.co.no9.util.Optional;
import za.co.no9.util.function.BiFunction;
import za.co.no9.util.function.Consumer;
import za.co.no9.util.function.Function;

import java.util.Iterator;

public class Stream<T> {
    private java.util.Iterator<T> iterator;

    public Stream(java.util.Iterator<T> iterator) {
        this.iterator = iterator;
    }

    public static <T> Stream<T> create(Iterator<T> iterator) {
        return new Stream<T>(iterator);
    }

    public Stream<T> filter(Predicate<T> predicate) {
        iterator = new FilteredIterator<T>(iterator, predicate);

        return this;
    }

    public Optional<T> findAny(final Predicate<T> matchPredicate) {
        return IteratorUtils.first(new FilteredIterator<T>(iterator, matchPredicate));
    }

    public long count() {
        return IteratorUtils.size(iterator);
    }

    public Iterator<T> toIterator() {
        return iterator;
    }

    public <R> Stream<R> map(final Function<T, R> function) {
        return new Stream<R>(new MapIterator<T, R>(iterator, function));
    }

    public void each(final Consumer<T> consumer) {
        IteratorUtils.each(iterator, consumer);
    }

    public <R> R fold(R init, BiFunction<R, T, R> function) {
        return IteratorUtils.fold(iterator, init, function);
    }
}
