package ru.job4j.ood.tdd.template;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

@Ignore
public class GeneratorTest {

    @Test
    public void whenGeneratorReturnCorrectValues() {
        Map<String, String> values = new HashMap<>();
        values.put("Michael Scott", "you");
        Generator generator = new GeneratorImpl();
        String template = "I am a Michael Scott, Who are you? ";
        String actual = generator.produce(template, values);
        Assert.assertEquals(template, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGeneratorCheckInCorrectValuesThenThrowException() {
        Map<String, String> values = new HashMap<>();
        values.put("Dwight Schrute", "Dwight");
        Generator generator = new GeneratorImpl();
        String template = "I am a Michael Scott, Who are you? ";
        String actual = generator.produce(template, values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGeneratorCheckNoValuesThenThrowException() {
        Map<String, String> values = new HashMap<>();
        Generator generator = new GeneratorImpl();
        String template = "I am a Michael Scott, Who are you? ";
        String actual = generator.produce(template, values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGeneratorCheckExtraValuesThenThrowException() {
        Map<String, String> values = new HashMap<>();
        values.put("Dwight Schrute", "Dwight");
        values.put("Michael Scott", "you");
        Generator generator = new GeneratorImpl();
        String template = "I am a Michael Scott, Who are you? ";
        String actual = generator.produce(template, values);
    }

}