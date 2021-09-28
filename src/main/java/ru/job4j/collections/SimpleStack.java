package ru.job4j.collections;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    private int size = 0;

    public T pop() {
        var result = linked.deleteFirst();
        size--;
        return result;
    }

    public void push(T value) {
        linked.addFirst(value);
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
