package za.co.no9.lang;

public class PredicateUtils {
    public static <T> Predicate<T> truePredicate() {
        return new Predicate<T>() {
            @Override
            public boolean test(T element) {
                return true;
            }
        };
    }
}
