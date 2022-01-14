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
        values.put("name", "Michael Scott");
        values.put("subject", "you");
        Generator generator = new GeneratorImpl();
        String template = "I am a ${name}, Who are ${subject}? ";
        String actual = generator.produce(template, values);
        Assert.assertEquals(template, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGeneratorCheckIncorrectKeyThenThrowException() {
        Map<String, String> values = new HashMap<>();
        values.put("name", "Michael Scott");
        values.put("subject", "you");
        Generator generator = new GeneratorImpl();
        String template = "I am a ${key}, Who are ${subject}? ";
        String actual = generator.produce(template, values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGeneratorCheckNoValuesThenThrowException() {
        Map<String, String> values = new HashMap<>();
        Generator generator = new GeneratorImpl();
        String template = "I am a ${name}, Who are ${subject}? ";
        String actual = generator.produce(template, values);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGeneratorCheckExtraValuesThenThrowException() {
        Map<String, String> values = new HashMap<>();
        values.put("name", "Michael Scott");
        values.put("subject", "you");
        values.put("key", "Jim Halpert");
        values.put("value", "you");
        Generator generator = new GeneratorImpl();
        String template = "I am a ${name}, Who are ${subject}? ";
        String actual = generator.produce(template, values);
    }

}