package za.co.no9.util;

import za.co.no9.util.stream.Stream;

import java.util.Enumeration;
import java.util.Iterator;

public class IteratorUtils {
    public static <T> Stream<T> toStream(final Iterator<T> iterator) {
        return Stream.create(iterator);
    }

    public static <T> Enumeration<T> toEnumeration(final Iterator<T> iterator) {
        return new Enumeration<T>() {
            @Override
            public boolean hasMoreElements() {
                return iterator.hasNext();
            }

            @Override
            public T nextElement() {
                return iterator.next();
            }
        };
    }

    public static <T> Optional<T> first(Iterator<T> iterator) {
        return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.<T>empty();
    }

    public static <T> long size(Iterator<T> iterator) {
        long size = 0;
        while (iterator.hasNext()) {
            iterator.next();
            size += 1;
        }
        return size;
    }
}
