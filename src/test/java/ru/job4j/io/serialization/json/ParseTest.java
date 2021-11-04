package ru.job4j.io.serialization.json;

import java.util.List;

import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Test;

public class ParseTest {

    @Test
    public void whenParseToJson() {
        var numbers = new String[] {"+1 456 567677452", "+1 457 12345678"};
        var contacts = List.of(
                new Contact("Michael", "+1 123 32131231"),
                new Contact("Andy", "+1 321 56757521"),
                new Contact("Ruby", "+1 312 328989031"),
                new Contact("Set", "+1 876 312367868"),
                new Contact("Romeo", "+1 567 09764731")
        );
        var expected = new Person("Jim", 34, 'm', true, numbers, contacts);
        var actual = Parse.parseToObj(Parse.parseToJson(expected), JsonObject.class);
        Assert.assertEquals(expected.getName(), actual.get("name").getAsString());
        Assert.assertEquals(expected.isMale(), actual.get("isMale").getAsBoolean());
        Assert.assertEquals(expected.getContacts().size(), actual.get("contacts").getAsJsonArray().size());
    }

    @Test
    public void whenParseToObj() {
        var numbers = new String[] {"+1 456 567677452", "+1 457 12345678"};
        var contacts = List.of(
                new Contact("Michael", "+1 123 32131231"),
                new Contact("Andy", "+1 321 56757521"),
                new Contact("Ruby", "+1 312 328989031"),
                new Contact("Set", "+1 876 312367868"),
                new Contact("Romeo", "+1 567 09764731")
        );
        var expected = new Person("Jim", 34, 'm', true, numbers, contacts);
        var actual = Parse.parseToObj(Parse.parseToJson(expected), Person.class);
        Assert.assertFalse(actual.toString().isEmpty());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.isMale(), actual.isMale());
        Assert.assertEquals(expected.getContacts().size(), actual.getContacts().size());
    }

}