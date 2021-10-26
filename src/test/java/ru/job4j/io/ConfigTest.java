package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "src/main/resources/data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        Assert.assertThat(config.value("name"), Matchers.is("Michael"));
        Assert.assertThat(config.value("surname"), Matchers.is("Scott"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "src/main/resources/data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        Assert.assertThat(config.value("name"), Matchers.is("Michael"));
        Assert.assertThat(config.value("surname"), Matchers.is("Scott"));
    }

    @Test
    public void whenPairWithEmptyLine() {
        String path = "src/main/resources/data/pair_with_empty_line.properties";
        Config config = new Config(path);
        config.load();
        Assert.assertThat(config.value("name"), Matchers.is("Michael"));
        Assert.assertThat(config.value("surname"), Matchers.is("Scott"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithKeyValueViolations() {
        String path = "src/main/resources/data/pair_without_key.properties";
        Config config = new Config(path);
        config.load();
    }

}