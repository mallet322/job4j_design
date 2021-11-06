package ru.job4j.io.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class Parse {

    private static final Gson GSON = new GsonBuilder().create();

    public static <T> String parseToJson(T obj) {
        return GSON.toJson(obj);
    }

    public static <T> T parseToObj(String json, Class<T> clazz) {
        return GSON.fromJson(json, clazz);
    }

    public static JSONObject getPersonJSONObject(Person person) {
        JSONObject json = new JSONObject();
        json.put("name", person.getName());
        json.put("age", person.getAge());
        json.put("sex", person.getSex());
        json.put("isMale", person.isMale());
        json.put("numbers", new JSONArray(person.getNumbers()));
        json.put("contacts", new JSONArray(person.getContacts()));
        return json;
    }

    public static String getJSONObjectString(JSONObject jsonObject) {
        return jsonObject.toString();
    }

}
