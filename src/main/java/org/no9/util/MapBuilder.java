package org.no9.util;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {
    private Map<K, V> map = new HashMap<K, V>();

    public static <K, V> MapBuilder<K, V> create() {
        return new MapBuilder<K, V>();
    }

    public MapBuilder<K, V> add(K key, V value) {
        map.put(key, value);
        return this;
    }

    public Map<K, V> toHashMap() {
        return map;
    }
}
