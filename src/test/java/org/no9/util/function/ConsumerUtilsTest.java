package org.no9.util.function;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConsumerUtilsTest {
    @Test
    public void should_apply_both_consumers_in_sequence() {
        List<String> strings = new ArrayList<String>();
        strings.add("Hello");
        strings.add("World");

        ChompList first = new ChompList();
        ChompList second = new ChompList();

        ConsumerUtils.andThen(first, second).accept(strings);

        assertEquals("Hello", first.head);
        assertEquals("World", second.head);

        assertEquals(0, strings.size());
    }

    class ChompList implements Consumer<List<String>> {
        public String head;

        @Override
        public void accept(List<String> element) {
            head = element.remove(0);
        }
    }
}
