package ru.job4j.collections.set;

import org.junit.Assert;
import org.junit.Test;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        Assert.assertTrue(set.add(1));
        Assert.assertTrue(set.contains(1));
        Assert.assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        Assert.assertTrue(set.add(null));
        Assert.assertTrue(set.contains(null));
        Assert.assertFalse(set.add(null));
    }

    @Test
    public void whenNotAddToSet() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Assert.assertFalse(set.add(3));
    }

    @Test
    public void when1ContainsInSetElements() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Assert.assertTrue(set.contains(1));
    }

    @Test
    public void when4NotContainsInSetElements() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        Assert.assertFalse(set.contains(4));
    }

}