package ru.job4j.generics.stores;

import org.junit.Assert;
import org.junit.Test;

public class UserStoreTest {

    @Test
    public void whenAddRole() {
        MemStore<User> store = new MemStore<>();
        User user = new User("1");
        store.add(user);
        Assert.assertEquals(user, store.findById(user.getId()));
    }

    @Test
    public void whenReplaceRole() {
        MemStore<User> store = new MemStore<>();
        User oldUser = new User("1");
        store.add(oldUser);
        User newUser = new User("1");
        store.replace(newUser.getId(), newUser);
        Assert.assertNotEquals(oldUser, store.findById(newUser.getId()));
    }

    @Test
    public void whenDeleteRole() {
        MemStore<User> store = new MemStore<>();
        User user = new User("1");
        store.add(user);
        store.delete(user.getId());
        Assert.assertNull(store.findById(user.getId()));
    }

}