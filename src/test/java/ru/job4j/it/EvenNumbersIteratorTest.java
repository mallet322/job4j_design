package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EvenNumbersIteratorTest {

    private Iterator<Integer> it;

    @Before
    public void setUp() {
        it = new EvenNumbersIterator(new int[] {1, 2, 3, 4, 5, 6, 7});
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        Assert.assertThat(it.hasNext(), Matchers.is(true));
        Assert.assertThat(it.next(), Matchers.is(2));
        Assert.assertThat(it.hasNext(), Matchers.is(true));
        Assert.assertThat(it.next(), Matchers.is(4));
        Assert.assertThat(it.hasNext(), Matchers.is(true));
        Assert.assertThat(it.next(), Matchers.is(6));
        Assert.assertThat(it.hasNext(), Matchers.is(false));
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        Assert.assertThat(it.hasNext(), Matchers.is(true));
        Assert.assertThat(it.hasNext(), Matchers.is(true));
        Assert.assertThat(it.next(), Matchers.is(2));
        Assert.assertThat(it.next(), Matchers.is(4));
        Assert.assertThat(it.next(), Matchers.is(6));
    }

    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EvenNumbersIterator(new int[]{1});
        Assert.assertThat(it.hasNext(), Matchers.is(false));
    }

    @Test
    public void allNumbersAreEven() {
        it = new EvenNumbersIterator(new int[] {2, 4, 6, 8});
        Assert.assertThat(it.hasNext(), Matchers.is(true));
        Assert.assertThat(it.next(), Matchers.is(2));
        Assert.assertThat(it.hasNext(), Matchers.is(true));
        Assert.assertThat(it.next(), Matchers.is(4));
        Assert.assertThat(it.hasNext(), Matchers.is(true));
        Assert.assertThat(it.next(), Matchers.is(6));
        Assert.assertThat(it.hasNext(), Matchers.is(true));
        Assert.assertThat(it.next(), Matchers.is(8));
    }
}