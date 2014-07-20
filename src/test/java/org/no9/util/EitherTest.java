package org.no9.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class EitherTest {
    final static Either<String, Integer> LEFT_EITHER = Either.left("Hello");
    final static Either<String, Integer> RIGHT_EITHER = Either.right(123);

    @Test(expected = java.lang.NullPointerException.class)
    public void should_fail_if_attempt_to_create_either_with_a_left_null() throws Exception {
        Either.left(null);
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void should_fail_if_attempt_to_create_either_with_a_right_null() throws Exception {
        Either.right(null);
    }

    @Test
    public void should_return_the_correct_is_results_for_LEFT_and_RIGHT() throws Exception {
        assertTrue(LEFT_EITHER.isLeft());
        assertFalse(LEFT_EITHER.isRight());

        assertFalse(RIGHT_EITHER.isLeft());
        assertTrue(RIGHT_EITHER.isRight());
    }

    @Test
    public void should_return_the_corresponding_left_value() throws Exception {
        assertTrue(LEFT_EITHER.left().isPresent());
        assertEquals("Hello", LEFT_EITHER.left().get());

        assertFalse(RIGHT_EITHER.left().isPresent());
    }

    @Test
    public void should_return_the_corresponding_right_value() throws Exception {
        assertFalse(LEFT_EITHER.right().isPresent());

        assertTrue(RIGHT_EITHER.right().isPresent());
        assertEquals(new Integer(123), RIGHT_EITHER.right().get());
    }
}
