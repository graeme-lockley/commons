package za.co.no9.util;

import za.co.no9.util.stream.Stream;

import java.util.Enumeration;
import java.util.Iterator;

public class EnumerationUtils {
    public static <T> Stream<T> toStream(final Enumeration<T> enumeration) {
        return Stream.create(toIterator(enumeration));
    }

    public static <T> Iterator<T> toIterator(final Enumeration<T> enumeration) {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return enumeration.hasMoreElements();
            }

            @Override
            public T next() {
                return enumeration.nextElement();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static <T> Optional<T> first(Enumeration<T> enumeration) {
        return IteratorUtils.first(toIterator(enumeration));
    }

    public static <T> long size(Enumeration<T> iterator) {
        return IteratorUtils.size(toIterator(iterator));
    }
}
