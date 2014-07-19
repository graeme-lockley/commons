package org.no9.util;

import org.no9.util.function.Function;

import java.util.Iterator;

public class MapIterator<T, R> implements Iterator<R> {
    private final Iterator<T> iterator;
    private final Function<T, R> function;

    public MapIterator(Iterator<T> iterator, Function<T, R> function) {
        this.iterator = iterator;
        this.function = function;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public R next() {
        return function.apply(iterator.next());
    }

    @Override
    public void remove() {
        iterator.remove();
    }
}
