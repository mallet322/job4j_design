package ru.job4j.collections.arraylist;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList() {
        this.container = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            grow();
        }
        container[size] = value;
        size++;
    }

    private void grow() {
        int oldCapacity = container.length;
        if (oldCapacity == 0) {
            this.container = (T[]) new Object[DEFAULT_CAPACITY];
        } else {
            container = Arrays.copyOf(container, oldCapacity * 2);
        }
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        modCount++;
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(container, index + 1, container, index, newSize - index);
        }
        size = newSize;
        container[size] = null;
        return oldValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursor = 0;

            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }

}