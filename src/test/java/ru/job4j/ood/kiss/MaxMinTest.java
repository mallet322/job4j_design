package ru.job4j.ood.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class MaxMinTest {

    @Test
    public void whenMax228() {
        List<Integer> list = List.of(1, 2, 3, 40, 228);
        MaxMin finder = new MaxMin();
        Integer expected = 228;
        Integer actual = finder.max(list, Comparator.comparingInt(num -> num));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenMin1488() {
        List<Integer> list = List.of(1488, 1232132, 3000, 40123, 2228);
        MaxMin finder = new MaxMin();
        Integer expected = 1488;
        Integer actual = finder.min(list, Comparator.comparingInt(num -> num));
        Assert.assertEquals(expected, actual);
    }

}