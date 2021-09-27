package ru.job4j.it;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

public class MatrixItTest {

    @Test
    public void when4El() {
        int[][] in = {
                {1}
        };
        MatrixIt it = new MatrixIt(in);
        Assert.assertThat(it.next(), Matchers.is(1));
    }

    @Test
    public void whenFirstEmptyThenNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        Assert.assertThat(it.next(), Matchers.is(1));
    }

    @Test
    public void whenFirstEmptyThenHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        Assert.assertThat(it.hasNext(), Matchers.is(true));
    }

    @Test
    public void whenRowHasDiffSize() {
        int[][] in = {
                {1}, {2, 3}
        };
        MatrixIt it = new MatrixIt(in);
        Assert.assertThat(it.next(), Matchers.is(1));
        Assert.assertThat(it.next(), Matchers.is(2));
        Assert.assertThat(it.next(), Matchers.is(3));
    }

    @Test
    public void whenFewEmpty() {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);
        Assert.assertThat(it.next(), Matchers.is(1));
        Assert.assertThat(it.next(), Matchers.is(2));
    }

    @Test
    public void whenEmpty() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        Assert.assertThat(it.hasNext(), Matchers.is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyThenNext() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        it.next();
    }

    @Test
    public void whenMultiHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        Assert.assertThat(it.hasNext(), Matchers.is(true));
        Assert.assertThat(it.hasNext(), Matchers.is(true));
    }

    @Test
    public void whenNoElements() {
        int[][] in = {{}, {}, {}};
        MatrixIt it = new MatrixIt(in);
        Assert.assertThat(it.hasNext(), Matchers.is(false));
    }

}