package ru.job4j.it;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlatMapTest {

    @Test
    public void whenDiffNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator(),
                List.of(2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        Assert.assertThat(flat.next(), Matchers.is(1));
        Assert.assertThat(flat.next(), Matchers.is(2));
        Assert.assertThat(flat.next(), Matchers.is(3));
    }

    @Test
    public void whenSeqNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        Assert.assertThat(flat.next(), Matchers.is(1));
        Assert.assertThat(flat.next(), Matchers.is(2));
        Assert.assertThat(flat.next(), Matchers.is(3));
    }

    @Test
    public void whenMultiHasNext() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        Assert.assertThat(flat.hasNext(), Matchers.is(true));
        Assert.assertThat(flat.hasNext(), Matchers.is(true));
    }

    @Test
    public void whenHasNextFalse() {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        Assert.assertThat(flat.next(), Matchers.is(1));
        Assert.assertThat(flat.hasNext(), Matchers.is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmpty() {
        Iterator<Iterator<Object>> data = List.of(
                List.of().iterator()
        ).iterator();
        FlatMap<Object> flat = new FlatMap<>(data);
        flat.next();
    }

    @Test
    public void whenSeveralEmptyAndNotEmpty() {
        Iterator<Iterator<?>> it = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of(1).iterator()
        ).iterator();
        FlatMap flat = new FlatMap(it);
        Assert.assertTrue(flat.hasNext());
        Assert.assertThat(1, Matchers.is(flat.next()));
    }

    @Test
    public void whenSeveralEmptyThenReturnFalse() {
        Iterator<Iterator<Object>> it = List.of(
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator(),
                List.of().iterator()
        ).iterator();
        FlatMap flat = new FlatMap(it);
        Assert.assertFalse(flat.hasNext());
    }

}