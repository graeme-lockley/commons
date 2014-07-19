package org.no9.util;

import org.no9.lang.Predicate;

import java.util.Enumeration;

public class FilteredEnumeration<T> implements Enumeration<T> {
    private FilteredIterator<T> iterator;

    public FilteredEnumeration(Enumeration<T> enumeration, Predicate<T> filter) {
        this.iterator = new FilteredIterator<T>(EnumerationUtils.toIterator(enumeration), filter);
    }

    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    @Override
    public T nextElement() {
        return iterator.next();
    }
}
