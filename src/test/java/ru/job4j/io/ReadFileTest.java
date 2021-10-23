package ru.job4j.io;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ReadFileTest {

    @Test
    public void readEvenNumber() {
        String fileName = "even.txt";
        List<Integer> expected = List.of(2, 22, 34, 52, 66, 88, 100);
        List<Integer> actual = ReadFile.getEvenNumbers(fileName);
        Assert.assertThat(actual, Matchers.is(expected));
    }

    @Test(expected = NullPointerException.class)
    public void readEvenNumberFail() {
        ReadFile.getEvenNumbers(null);
    }

}