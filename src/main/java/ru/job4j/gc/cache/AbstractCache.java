package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        if (value != null && value != " ") {
            cache.put(key, new SoftReference<>(value));
        }
    }

    public V get(K key) {
        SoftReference<V> value = cache.get(key);
        if (value == null) {
            value = new SoftReference<>(load(key));
            cache.put(key, value);
        }
        return value.get();
    }

    protected abstract V load(K key);

}
