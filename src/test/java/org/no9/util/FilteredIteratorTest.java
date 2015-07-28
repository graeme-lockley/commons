package org.no9.util;

import org.junit.Test;
import org.no9.lang.Predicate;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FilteredIteratorTest {

    public static final List<Integer> INTEGER_LIST = Arrays.asList(1, 2, 3, 4, 5);

    @Test
    public void should_leave_all_for_a_true_predicate() {
        assertIteratorEquals(create(1, 2, 3, 4, 5), new FilteredIterator<Integer>(INTEGER_LIST.iterator(), new Predicate<Integer>() {
            @Override
            public boolean test(Integer element) {
                return true;
            }
        }));
    }

    @Test
    public void should_remove_all_for_a_false_predicate() {
        assertIteratorEquals(create(), new FilteredIterator<Integer>(INTEGER_LIST.iterator(), new Predicate<Integer>() {
            @Override
            public boolean test(Integer element) {
                return false;
            }
        }));
    }

    @Test
    public void should_remove_all_odd_elements() {
        assertIteratorEquals(create(2, 4), new FilteredIterator<Integer>(INTEGER_LIST.iterator(), new Predicate<Integer>() {
            @Override
            public boolean test(Integer element) {
                return (element % 2) == 0;
            }
        }));
    }

    private List<Integer> create(Integer... elements) {
        return Arrays.asList(elements);
    }

    private void assertIteratorEquals(Iterable<Integer> source, Iterator<Integer> iterator) {
        assertIteratorEquals(source.iterator(), iterator);
    }

    private void assertIteratorEquals(Iterator<Integer> source, Iterator<Integer> iterator) {
        while (true) {
            if (source.hasNext()) {
                assertEquals(source.next(), iterator.next());
            } else {
                assertEquals(source.hasNext(), iterator.hasNext());
                break;
            }
        }
    }
}
