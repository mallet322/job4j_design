package ru.job4j.collections.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class SimpleLinkedListTest {

    @Test
    public void whenAddAndGet() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        Assert.assertThat(list.get(0), Matchers.is(1));
        Assert.assertThat(list.get(1), Matchers.is(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetFromOutOfBoundThenExceptionThrown() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.get(2);
    }

    @Test
    public void whenGetIteratorTwiceThenEveryFromBegin() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);

        Iterator<Integer> first = list.iterator();
        Assert.assertThat(first.hasNext(), Matchers.is(true));
        Assert.assertThat(first.next(), Matchers.is(1));
        Assert.assertThat(first.hasNext(), Matchers.is(true));
        Assert.assertThat(first.next(), Matchers.is(2));
        Assert.assertThat(first.hasNext(), Matchers.is(false));

        Iterator<Integer> second = list.iterator();
        Assert.assertThat(second.hasNext(), Matchers.is(true));
        Assert.assertThat(second.next(), Matchers.is(1));
        Assert.assertThat(second.hasNext(), Matchers.is(true));
        Assert.assertThat(second.next(), Matchers.is(2));
        Assert.assertThat(second.hasNext(), Matchers.is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        List<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        list.add(2);
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyListThenNextThrowException() {
        List<Integer> list = new SimpleLinkedList<>();
        list.iterator().next();
    }

}