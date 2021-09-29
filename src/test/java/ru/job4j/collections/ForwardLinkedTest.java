package ru.job4j.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ForwardLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Assert.assertThat(linked.deleteFirst(), Matchers.is(1));
        Iterator<Integer> it = linked.iterator();
        Assert.assertThat(it.next(), Matchers.is(2));
    }

    @Test
    public void whenAddToFront() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.addFirst(100500);
        linked.add(4);
        linked.add(5);
        linked.add(6);


        Iterator<Integer> it = linked.iterator();
        Assert.assertThat(it.next(), Matchers.is(100500));
    }

    @Test
    public void whenAddThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        Assert.assertThat(it.next(), Matchers.is(1));
        Assert.assertThat(it.next(), Matchers.is(2));
    }

    @Test
    public void whenAddAndRevertThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        Assert.assertThat(it.next(), Matchers.is(2));
        Assert.assertThat(it.next(), Matchers.is(1));
    }

    @Test
    public void whenSize0ThenReturnFalse() {
        ForwardLinked<Integer> emptyList = new ForwardLinked<>();
        Assert.assertFalse(emptyList.revert());
    }

    @Test
    public void whenSize1ThenReturnFalse() {
        ForwardLinked<Integer> singleList = new ForwardLinked<>();
        singleList.add(1);
        Assert.assertFalse(singleList.revert());
    }

}