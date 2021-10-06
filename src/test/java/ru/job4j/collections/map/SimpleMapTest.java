package ru.job4j.collections.map;

import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class SimpleMapTest {

    @Test
    public void whenPutElementsInMap() {
        Map<User, String> map = new SimpleMap<>();
        var key = new User("Alex", 2, new GregorianCalendar(1995, Calendar.NOVEMBER, 25));
        map.put(key, "user-value");
        Assert.assertEquals("user-value", map.get(key));
    }

    @Test
    public void whenMapExpandsAfterPut7Element() {
        Map<String, String> map = new SimpleMap<>();
        map.put("0", "AnyElement");
        map.put("1", "AnyElement");
        map.put("2", "AnyElement");
        map.put("3", "AnyElement");
        map.put("4", "AnyElement");
        map.put("5", "AnyElement");
        map.put("6", "AnyElement");
        Assert.assertEquals(8, map.size());
        map.put("7", "AnyElement");
        Assert.assertEquals(16, map.size());
    }

    @Test
    public void whenGetElementFromMap() {
        Map<User, String> map = new SimpleMap<>();
        var key = new User("Alex", 2, new GregorianCalendar(1995, Calendar.NOVEMBER, 25));
        map.put(key, "user-value");
        var el = map.get(key);
        Assert.assertEquals("user-value", el);
    }

    @Test
    public void whenNotGetElementFromMap() {
        Map<User, String> map = new SimpleMap<>();
        var key = new User("Alex", 2, new GregorianCalendar(1995, Calendar.NOVEMBER, 25));
        map.put(key, "user-value");
        key.setChildren(3);
        Assert.assertNull(map.get(key));
    }

    @Test
    public void whenRemoveElementFromMap() {
        Map<User, String> map = new SimpleMap<>();
        var key = new User("Alex", 2, new GregorianCalendar(1995, Calendar.NOVEMBER, 25));
        map.put(key, "user-value");
        Assert.assertTrue(map.remove(key));
    }

    @Test
    public void whenElementNotRemove() {
        Map<User, String> map = new SimpleMap<>();
        var key = new User("Alex", 2, new GregorianCalendar(1995, Calendar.NOVEMBER, 25));
        map.put(key, "user-value");
        key.setName("Max");
        boolean isRemove = map.remove(key);
        Assert.assertEquals(false, isRemove);

    }

    @Test(expected = NoSuchElementException.class)
    public void whenRemoveElementFromMapAndIteratorNextThrowException() {
        Map<User, String> map = new SimpleMap<>();
        var key = new User("Alex", 2, new GregorianCalendar(1995, Calendar.NOVEMBER, 25));
        map.put(key, "user-value");
        Assert.assertTrue(map.remove(key));
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorThrowConcurrentModificationException() {
        Map<User, String> map = new SimpleMap<>();
        var key = new User("Alex", 2, new GregorianCalendar(1995, Calendar.NOVEMBER, 25));
        map.put(key, "user-value");
        Iterator<User> it = map.iterator();
        key.setChildren(100500);
        map.put(key, "user-value");
        it.next();
    }


}