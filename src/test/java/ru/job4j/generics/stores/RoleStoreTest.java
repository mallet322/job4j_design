package ru.job4j.generics.stores;

import org.junit.Assert;
import org.junit.Test;

public class RoleStoreTest {

    @Test
    public void whenAddRole() {
        MemStore<Role> store = new MemStore<>();
        Role role = new Role("1");
        store.add(role);
        Assert.assertEquals(role, store.findById(role.getId()));
    }

    @Test
    public void whenReplaceRole() {
        MemStore<Role> store = new MemStore<>();
        Role oldRole = new Role("1");
        store.add(oldRole);
        Role newRole = new Role("1");
        store.replace(newRole.getId(), newRole);
        Assert.assertNotEquals(oldRole, store.findById(newRole.getId()));
    }

    @Test
    public void whenDeleteRole() {
        MemStore<Role> store = new MemStore<>();
        Role role = new Role("1");
        store.add(role);
        store.delete(role.getId());
        Assert.assertNull(store.findById(role.getId()));
    }

}