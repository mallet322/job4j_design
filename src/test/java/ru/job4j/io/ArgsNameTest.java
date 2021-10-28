package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        Assert.assertThat(jvm.get("Xmx"), Matchers.is("512"));
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        Assert.assertThat(jvm.get("Xmx"), Matchers.is("512"));
    }

    @Test
    public void whenGetSecondArgument() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Key=Value", "-Xmx=512"});
        Assert.assertThat(jvm.get("Key"), Matchers.is("Value"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {});
        jvm.get("Xmx");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSomeArgument() {
        ArgsName jvm = ArgsName.of(new String[] {"-enconding=UTF-8", "-Xmx="});
    }

}