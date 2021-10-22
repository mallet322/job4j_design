package ru.job4j.collections.question;

import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class AnalizeTest {

    @Test
    public void whenNotChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3);
        Assert.assertThat(
                Analize.diff(previous, current),
                Matchers.is(new Info(0, 0, 0))
        );
    }

    @Test
    public void whenOneChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, new User(2, "BB"), u3);
        Assert.assertThat(
                Analize.diff(previous, current),
                Matchers.is(new Info(0, 1, 0))
        );
    }

    @Test
    public void whenTwoChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(new User(1, "AA"), new User(2, "BB"), u3);
        Assert.assertThat(
                Analize.diff(previous, current),
                Matchers.is(new Info(0, 2, 0))
        );
    }

    @Test
    public void whenOneDeleted() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u3);
        Assert.assertThat(
                Analize.diff(previous, current),
                Matchers.is(new Info(0, 0, 1))
        );
    }

    @Test
    public void whenFourDeleted() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        User u4 = new User(4, "D");
        User u5 = new User(5, "E");
        User u6 = new User(6, "F");
        User u7 = new User(7, "G");
        Set<User> previous = Set.of(u1, u2, u3, u4, u5, u6, u7);
        Set<User> current = Set.of(u1, u2, u3);
        Assert.assertThat(
                Analize.diff(previous, current),
                Matchers.is(new Info(0, 0, 4))
        );
    }

    @Test
    public void whenOneAdded() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3, new User(4, "D"));
        Assert.assertThat(
                Analize.diff(previous, current),
                Matchers.is(new Info(1, 0, 0))
        );
    }

    @Test
    public void whenTwoAdded() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(u1, u2, u3, new User(4, "D"), new User(5, "D"));
        Assert.assertThat(
                Analize.diff(previous, current),
                Matchers.is(new Info(2, 0, 0))
        );
    }

    @Test
    public void whenAllChanged() {
        User u1 = new User(1, "A");
        User u2 = new User(2, "B");
        User u3 = new User(3, "C");
        Set<User> previous = Set.of(u1, u2, u3);
        Set<User> current = Set.of(new User(1, "AA"), u2, new User(4, "D"));
        Assert.assertThat(
                Analize.diff(previous, current),
                Matchers.is(new Info(1, 1, 1))
        );
    }

}