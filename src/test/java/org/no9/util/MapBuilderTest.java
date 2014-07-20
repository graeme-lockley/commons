package org.no9.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MapBuilderTest {
    @Test
    public void should_create_an_empty_map() {
        assertEquals(0, MapBuilder.create().toHashMap().size());
    }

    @Test
    public void should_create_a_single_entry_hash_map() {
        Map<String, String> map = MapBuilder.<String, String>create()
                .add("Hello", "World")
                .toHashMap();

        assertTrue(map instanceof HashMap);
        assertEquals(1, map.size());
        assertEquals("World", map.get("Hello"));
    }

    @Test
    public void should_support_a_null_value() {
        Map<String, String> map = MapBuilder.<String, String>create()
                .add("Hello", "World")
                .add("Bye bye", null)
                .toHashMap();

        assertEquals(2, map.size());
        assertEquals("World", map.get("Hello"));
        assertNull(map.get("Bye bye"));
    }

    @Test
    public void should_support_a_null_key() {
        Map<String, String> map = MapBuilder.<String, String>create()
                .add("Hello", "World")
                .add(null, "Love")
                .toHashMap();

        assertEquals(2, map.size());
        assertEquals("World", map.get("Hello"));
        assertEquals("Love", map.get(null));

    }
}
