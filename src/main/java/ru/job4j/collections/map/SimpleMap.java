package ru.job4j.collections.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count > capacity * LOAD_FACTOR) {
            expand();
        }
        int bucketIndex = indexFor(hash(key.hashCode()));
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new MapEntry<>(key, value);
        } else {
            return false;
        }
        count++;
        modCount++;
        return false;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int entryIndex = indexFor(hash(entry.getKey().hashCode()));
                newTable[entryIndex] = entry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        var entry = getEntry(key);
        return entry == null ? null : entry.getValue();
    }

    private MapEntry<K, V> getEntry(K key) {
        int index = indexFor(hash(key.hashCode()));
        var e = table[index];
        return (e == null || !key.equals(e.getKey())) ? null : e;
    }

    @Override
    public boolean remove(K key) {
        var entry = getEntry(key);
        if (entry == null) {
            return false;
        }
        var entryIndex = indexFor(hash(entry.getKey().hashCode()));
        table[entryIndex] = null;
        count--;
        modCount++;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < capacity && table[cursor] == null) {
                    cursor++;
                }
                return cursor < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].getKey();
            }
        };
    }

    @Override
    public int size() {
        return capacity;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

}
