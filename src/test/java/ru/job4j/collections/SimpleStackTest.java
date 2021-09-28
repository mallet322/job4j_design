package ru.job4j.collections;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class SimpleStackTest {

    @Test
    public void whenPushThenPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        Assert.assertThat(stack.pop(), Matchers.is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        Assert.assertThat(stack.pop(), Matchers.is(2));
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        Assert.assertThat(stack.pop(), Matchers.is(1));
    }

}