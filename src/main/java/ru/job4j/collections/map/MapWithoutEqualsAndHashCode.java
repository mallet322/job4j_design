package ru.job4j.collections.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class MapWithoutEqualsAndHashCode {

    public static void main(String[] args) {
        Map<User, Object> map = new HashMap<>();
        map.put(new User("Max", 1, new GregorianCalendar(1995, Calendar.NOVEMBER, 25)), new Object());
        map.put(new User("John", 3, new GregorianCalendar(1986, Calendar.MAY, 10)), new Object());
        for (Map.Entry<User, Object> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey().getName().hashCode() + " " + "value: " + entry.getValue());
        }
    }

}
