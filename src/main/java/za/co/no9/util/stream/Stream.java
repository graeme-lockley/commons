package za.co.no9.util.stream;

import za.co.no9.lang.Predicate;
import za.co.no9.lang.PredicateUtils;
import za.co.no9.lang.function.AndPredicate;
import za.co.no9.util.EnumerationUtils;
import za.co.no9.util.FilteredEnumeration;
import za.co.no9.util.Optional;

public class Stream<T> {
    private final java.util.Enumeration<T> enumOfT;
    public Predicate<T> filterPredicate = PredicateUtils.<T>truePredicate();

    public Stream(java.util.Enumeration<T> enumOfT) {
        this.enumOfT = enumOfT;
    }

    public static <T> Stream<T> create(java.util.Enumeration<T> enumOfT) {
        return new Stream<T>(enumOfT);
    }

    public Stream<T> filter(Predicate<T> predicate) {
        filterPredicate = new AndPredicate<T>(filterPredicate, predicate);

        return this;
    }

    public Optional<T> findAny(final Predicate<T> matchPredicate) {
        return EnumerationUtils.first(new FilteredEnumeration<T>(enumOfT, new AndPredicate<T>(filterPredicate, matchPredicate)));
    }

    public long count() {
        return EnumerationUtils.size(new FilteredEnumeration<T>(enumOfT, filterPredicate));
    }
}
