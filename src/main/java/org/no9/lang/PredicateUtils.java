package org.no9.lang;

public class PredicateUtils {
    public static <T> Predicate<T> truePredicate() {
        return new Predicate<T>() {
            @Override
            public boolean test(T element) {
                return true;
            }
        };
    }

    public static <T> Predicate<T> andPredicate(final Predicate<T> leftOperand, final Predicate<T> rightOperand) {
        return new Predicate<T>() {
            @Override
            public boolean test(T element) {
                return leftOperand.test(element) && rightOperand.test(element);
            }
        };
    }

    public static <T> Predicate<T> orPredicate(final Predicate<T> leftOperand, final Predicate<T> rightOperand) {
        return new Predicate<T>() {
            @Override
            public boolean test(T element) {
                return leftOperand.test(element) || rightOperand.test(element);
            }
        };
    }

    public static <T> Predicate<T> notPredicate(final Predicate<T> operand) {
        return new Predicate<T>() {
            @Override
            public boolean test(T element) {
                return !operand.test(element);
            }
        };
    }
}
