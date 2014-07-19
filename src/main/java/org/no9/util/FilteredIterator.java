package org.no9.util;

import org.no9.lang.Predicate;

public class FilteredIterator<T> implements java.util.Iterator<T> {
    private final java.util.Iterator<T> iteratorOfT;
    private final Predicate<T> filter;

    private T nextElement;

    public FilteredIterator(java.util.Iterator<T> iteratorOfT, Predicate<T> filter) {
        this.iteratorOfT = iteratorOfT;
        this.filter = filter;

        setNextElement();
    }

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }

    @Override
    public T next() {
        T returnElement = nextElement;
        setNextElement();
        return returnElement;
    }

    private void setNextElement() {
        nextElement = null;
        while (nextElement == null && iteratorOfT.hasNext()) {
            T testElement = iteratorOfT.next();
            if (filter.test(testElement)) {
                nextElement = testElement;
            }
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
