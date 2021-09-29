package ru.job4j.collections.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private int size;

    private int modCount;

    private Node<E> head;

    private Node<E> tail;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value, null);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        modCount++;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node.getData();
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            private final int expectedModCount = modCount;
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = current.getData();
                current = current.getNext();
                return data;
            }

        };

    }

    private static class Node<E> {

        E data;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.data = element;
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

    }

}
