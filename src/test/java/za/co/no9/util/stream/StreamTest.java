package za.co.no9.util.stream;

import org.junit.Test;
import za.co.no9.lang.Predicate;

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
        assertIteratorEquals(items.iterator(), Stream.create(items.iterator()).toIterator());
    }

    @Test
    public void should_filter_elements_from_the_list() {
        List<String> control = Arrays.asList("A", "C", "E");

        assertIteratorEquals(control.iterator(), Stream
                .create(items.iterator())
                .filter(oddFilter)
                .toIterator());
    }

    @Test
    public void should_return_the_correct_count_following_applying_a_filter() {
        assertEquals(3, Stream
                .create(items.iterator())
                .filter(oddFilter)
                .count());
    }

    @Test
    public void should_locate_an_element_that_matches_the_pattern() {
        assertEquals("C", Stream
                .create(items.iterator())
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

    private <T> void assertIteratorEquals(Iterator<T> control, Iterator<T> experiment) {
        while (control.hasNext() && experiment.hasNext()) {
            assertEquals(control.next(), experiment.next());
        }
        assertEquals(control.hasNext(), experiment.hasNext());
    }


}
