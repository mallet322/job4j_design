package ru.job4j.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        Assert.assertThat(input, Matchers.is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        Assert.assertThat(input, Matchers.is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveOddElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.removeIf(input, el -> el % 2 != 0);
        Assert.assertThat(input, Matchers.is(Arrays.asList(0, 2)));
    }

    @Test
    public void whenFirstElementLessThanZeroThenSet100500() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
        ListUtils.replaceIf(input, el -> el < 1, 100500);
        Assert.assertThat(input, Matchers.is(Arrays.asList(100500, 1, 2, 3)));
    }

    @Test
    public void whenRemoveAllElementFromListByElementsList() {
        List<Integer> list = new ArrayList<>(List.of(6, 1, 4, 5, 2, 3));
        List<Integer> elements = new ArrayList<>(List.of(1, 2, 3));
        ListUtils.removeAll(list, elements);
        Assert.assertThat(list, Matchers.is(Arrays.asList(6, 4, 5)));
    }

}