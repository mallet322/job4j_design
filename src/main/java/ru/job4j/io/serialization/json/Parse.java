package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Parse {

    private static final Gson GSON = new GsonBuilder().create();

    public static <T> String parseToJson(T obj) {
        return GSON.toJson(obj);
    }

    public static <T> T parseToObj(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

}
