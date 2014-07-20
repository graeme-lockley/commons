package org.no9.util;

public class Either<L, R> {
    private final L left;
    private final R right;

    private Either(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <L, R> Either<L, R> left(L left) {
        if (left == null) {
            throw new NullPointerException("Attempt to assign a null to left.");
        }
        return new Either<L, R>(left, null);
    }

    public static <L, R> Either<L, R> right(R right) {
        if (right == null) {
            throw new NullPointerException("Attempt to assign a null to right.");
        }
        return new Either<L, R>(null, right);
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    public Optional<L> left() {
        return Optional.ofNullable(this.left);
    }

    public Optional<R> right() {
        return Optional.ofNullable(this.right);
    }
}
