package ru.job4j.collections.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MapWithoutHashCode {

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        map.put(new User("Max", 1, new GregorianCalendar(1995, Calendar.NOVEMBER, 25)), new Object());
        map.put(new User("Max", 1, new GregorianCalendar(1995, Calendar.NOVEMBER, 25)), new Object());
        map.put(new User("Maxxx", 2, new GregorianCalendar(1995, Calendar.NOVEMBER, 25)), new Object());
        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println("key " + entry.getKey() + "; key name: " + entry.getKey().getName() + "; keyHash: " + entry.getKey().getName().hashCode()
                                + "; valueHash: " + entry.getValue().hashCode());
        }
    }

}
