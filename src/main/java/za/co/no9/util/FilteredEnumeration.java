package za.co.no9.util;

import za.co.no9.lang.Predicate;

public class FilteredEnumeration<T> implements java.util.Enumeration<T> {
    private final java.util.Enumeration<T> enumOfT;
    private final Predicate<T> filter;

    private T nextElement;

    public FilteredEnumeration(java.util.Enumeration<T> enumOfT, Predicate<T> filter) {
        this.enumOfT = enumOfT;
        this.filter = filter;

        setNextElement();
    }

    @Override
    public boolean hasMoreElements() {
        return nextElement != null;
    }

    @Override
    public T nextElement() {
        T returnElement = nextElement;
        setNextElement();
        return returnElement;
    }

    private void setNextElement() {
        nextElement = null;
        while (nextElement == null && enumOfT.hasMoreElements()) {
            T testElement = enumOfT.nextElement();
            if (filter.test(testElement)) {
                nextElement = testElement;
            }
        }
    }
}
