package za.co.no9.util;

import za.co.no9.util.function.BiFunction;
import za.co.no9.util.function.Consumer;
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

    public static <T> void each(Iterator<T> iterator, Consumer<T> consumer) {
        while (iterator.hasNext()) {
            consumer.accept(iterator.next());
        }
    }

    public static <T, R> R fold(Iterator<T> iterator, R initValue, BiFunction<R, T, R> biFunction) {
        R result = initValue;

        while (iterator.hasNext()) {
            result = biFunction.apply(result, iterator.next());
        }

        return result;
    }
}
