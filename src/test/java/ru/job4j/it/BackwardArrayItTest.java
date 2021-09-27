package ru.job4j.it;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class BackwardArrayItTest {

    @Test
    public void whenMultiCallhasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        Assert.assertThat(it.hasNext(), Matchers.is(true));
        Assert.assertThat(it.hasNext(), Matchers.is(true));
    }

    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        Assert.assertThat(it.next(), Matchers.is(3));
        Assert.assertThat(it.next(), Matchers.is(2));
        Assert.assertThat(it.next(), Matchers.is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {}
        );
        it.next();
    }

}