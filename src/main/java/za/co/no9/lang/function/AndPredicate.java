package za.co.no9.lang.function;

import za.co.no9.lang.Predicate;

public class AndPredicate<T> implements Predicate<T> {
    private final Predicate<T> leftOperand;
    private final Predicate<T> rightOperand;

    public AndPredicate(Predicate<T> leftOperand, Predicate<T> rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public boolean test(T element) {
        return leftOperand.test(element) && rightOperand.test(element);
    }
}
