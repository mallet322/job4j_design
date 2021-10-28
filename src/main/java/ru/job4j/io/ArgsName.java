package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Wrong arguments!");
        }
        for (String s : args) {
            String[] strings = s.split("=");
            if (strings.length < 2) {
                throw new IllegalArgumentException("Wrong argument type!");
            }
            values.put(strings[0].substring(1), strings[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

}
