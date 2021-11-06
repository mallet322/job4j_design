package ru.job4j.io.serialization.xml;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ParseTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenSerializePerson() throws Exception {
        var numbers = new String[] {"+1 456 567677452", "+1 457 12345678"};
        var contacts = List.of(
                new Contact("Michael", "+1 123 32131231"),
                new Contact("Andy", "+1 321 56757521"),
                new Contact("Ruby", "+1 312 328989031"),
                new Contact("Set", "+1 876 312367868"),
                new Contact("Romeo", "+1 567 09764731")
        );
        Person person = new Person("Jim", 34, 'm', true, numbers, contacts);
        File target = temporaryFolder.newFile("Person.xml");
        var expected = Parse.serializeToXml(person, target.getAbsolutePath()).concat(System.lineSeparator());
        Assert.assertFalse(Files.readString(target.toPath()).isEmpty());
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test
    public void whenDeserializeToPerson() throws Exception {
        var numbers = new String[] {"+1 456 567677452", "+1 457 12345678"};
        var contacts = List.of(
                new Contact("Michael", "+1 123 32131231"),
                new Contact("Andy", "+1 321 56757521"),
                new Contact("Ruby", "+1 312 328989031"),
                new Contact("Set", "+1 876 312367868"),
                new Contact("Romeo", "+1 567 09764731")
        );
        Person expected = new Person("Jim", 34, 'm', true, numbers, contacts);
        File target = temporaryFolder.newFile("Person.xml");
        var xml = Parse.serializeToXml(expected, target.getAbsolutePath());
        var actual = Parse.deserializeXml(xml);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getAge(), actual.getAge());
        Assert.assertEquals(expected.getSex(), actual.getSex());
        Assert.assertEquals(expected.getNumbers()[0], actual.getNumbers()[0]);
        Assert.assertEquals(expected.getContacts().size(), actual.getContacts().size());
        Assert.assertEquals(expected.getContacts().get(3).getName(), actual.getContacts().get(3).getName());
    }

}