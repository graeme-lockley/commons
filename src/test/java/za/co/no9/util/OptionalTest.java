package za.co.no9.util;

import org.junit.Test;
import za.co.no9.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OptionalTest {
    private static final Optional<String> EMPTY_OPTIONAL = Optional.ofNullable(null);
    private static final Optional<String> SET_OPTIONAL = Optional.ofNullable("Hello");

    @Test(expected = java.lang.NullPointerException.class)
    public void should_throw_excpeption_when_attempting_to_create_an_optional_using_of_with_null() {
        Optional.of(null);
    }

    @Test
    public void should_create_an_empty_using_ofNullable() {
        assertTrue(EMPTY_OPTIONAL.isNotPresent());
    }

    @Test
    public void should_create_a_present_using_ofNullable() {
        assertTrue(SET_OPTIONAL.isPresent());
    }

    @Test
    public void should_be_able_to_extract_the_of_content() {
        assertEquals("Hello", SET_OPTIONAL.get());
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void should_throw_an_exception_if_attempting_to_get_on_an_empty_option() {
        EMPTY_OPTIONAL.get();
    }

    @Test
    public void should_return_element_on_set_optional_on_else() {
        assertEquals("Hello", SET_OPTIONAL.orElse("World"));
    }

    @Test
    public void should_return_default_element_on_empty_optional_on_else() {
        assertEquals("Hello", SET_OPTIONAL.orElse("World"));
    }

    @Test
    public void should_invoke_apply_if_present() {
        TestConsumer consumer = new TestConsumer();

        SET_OPTIONAL.ifPresent(consumer);

        assertEquals("Hello", consumer.value);
    }

    @Test
    public void should_not_invoke_apply_if_not_present() {
        TestConsumer consumer = new TestConsumer();

        EMPTY_OPTIONAL.ifPresent(consumer);

        assertEquals("World", consumer.value);
    }

    class TestConsumer implements Consumer<String> {
        public String value = "World";

        @Override
        public void accept(String element) {
            value = element;
        }
    }
}
