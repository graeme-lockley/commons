package org.no9.util.stream;

import org.junit.Test;
import org.no9.lang.Predicate;
import org.no9.util.function.BiFunction;
import org.no9.util.function.Consumer;
import org.no9.util.function.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StreamTest {
    private List<String> items = Arrays.asList("A", "B", "C", "D", "E", "F");
    private Predicate<String> oddFilter = new Predicate<String>() {
        private int count = 0;

        @Override
        public boolean test(String element) {
            count += 1;
            return (count % 2 == 1);
        }
    };

    @Test
    public void should_iterate_through_the_list() {
        assertIteratorEquals(items, Stream.create(items.iterator()).toIterator());
    }

    @Test
    public void should_filter_elements_from_the_list() {
        List<String> control = Arrays.asList("A", "C", "E");

        assertIteratorEquals(control, Stream.create(items.iterator())
                .filter(oddFilter)
                .toIterator());
    }

    @Test
    public void should_return_the_correct_count_following_applying_a_filter() {
        assertEquals(3, Stream.create(items.iterator())
                .filter(oddFilter)
                .count());
    }

    @Test
    public void should_locate_an_element_that_matches_the_pattern() {
        assertEquals("C", Stream.create(items.iterator())
                .findAny(new Predicate<String>() {
                    @Override
                    public boolean test(String element) {
                        return element.contains("C");
                    }
                })
                .get());
    }

    @Test
    public void should_return_an_empty_if_no_element_matches_the_pattern() {
        assertFalse(Stream
                .create(items.iterator())
                .findAny(new Predicate<String>() {
                    @Override
                    public boolean test(String element) {
                        return element.contains("X");
                    }
                })
                .isPresent());
    }

    @Test
    public void should_map_a_string_stream_onto_an_int_stream() {
        assertIteratorEquals(Arrays.asList(0, 1, 2, 3, 4, 5), Stream
                        .create(items.iterator())
                        .map(new Function<String, Integer>() {
                            @Override
                            public Integer apply(String s) {
                                return s.charAt(0) - 'A';
                            }
                        })
                        .toIterator()
        );
    }

    @Test
    public void should_execute_consumer_for_each_element() {
        final List<String> result = new ArrayList<String>();

        Stream.create(items.iterator())
                .each(new Consumer<String>() {
                    @Override
                    public void accept(String element) {
                        result.add(element);
                    }
                });
        assertIteratorEquals(items, result.iterator());
    }

    @Test
    public void should_fold_into_a_string() {
        String result = Stream.create(items.iterator())
                .fold(null, new BiFunction<String, String, String>() {
                    @Override
                    public String apply(String s, String s2) {
                        return s == null ? s2 : (s + ", " + s2);
                    }
                });

        assertEquals("A, B, C, D, E, F", result);
    }

    private <T> void assertIteratorEquals(Iterable<T> control, Iterator<T> experiment) {
        assertIteratorEquals(control.iterator(), experiment);
    }

    private <T> void assertIteratorEquals(Iterator<T> control, Iterator<T> experiment) {
        while (control.hasNext() && experiment.hasNext()) {
            assertEquals(control.next(), experiment.next());
        }
        assertEquals(control.hasNext(), experiment.hasNext());
    }
}
