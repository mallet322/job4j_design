package ru.job4j.io.serialization.xml;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Parse {

    public static void main(String[] args) {
        var numbers = new String[] {"+1 456 567677452", "+1 457 12345678"};
        var contacts = List.of(
                new Contact("Michael", "+1 123 32131231"),
                new Contact("Andy", "+1 321 56757521"),
                new Contact("Ruby", "+1 312 328989031"),
                new Contact("Set", "+1 876 312367868"),
                new Contact("Romeo", "+1 567 09764731")
        );
        Person person = new Person("Jim", 34, 'm', true, numbers, contacts);
        getXMLFile(getPersonTags(person));
    }

    private static void getXMLFile(String out) {
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream("Person.xml")))) {
            writer.println(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPersonTags(Person person) {
        String header = "<?xml version=\"1.1\" encoding=\"UTF-8\"?>";
        String startTag = "<Person>";
        String endTag = "</Person>";
        String args =
                "<name>" + person.getName() + "</name>"
                        + "<age>" + person.getAge() + "</age>"
                        + "<sex>" + person.getSex() + "</sex>"
                        + "<isMale>" + person.isMale() + "</isMale>";

        var numbers = getNumberTags(person).trim();
        var contacts = getContactTags(person).trim();

        return header + startTag + args + numbers + contacts + endTag;
    }

    private static String getNumberTags(Person person) {
        String startTag = "<numbers>";
        String endTag = "</numbers>";
        StringBuilder numbers = new StringBuilder();
        for (var num : person.getNumbers()) {
            numbers.append(getNumberTag(num));
        }
        return startTag + numbers + endTag;
    }

    private static String getNumberTag(String num) {
        return "<value>" + num + "</value>";
    }

    private static String getContactTags(Person person) {
        String startTag = "<contacts>";
        String endTag = "</contacts>";
        StringBuilder contacts = new StringBuilder();
        for (Contact contact : person.getContacts()) {
            contacts.append(getContactTag(contact));
        }
        return startTag + contacts + endTag;
    }

    private static String getContactTag(Contact contact) {
        return "<Contact>"
                + "<name>" + contact.getName() + "</name>"
                + "<phone>" + contact.getPhone() + "</phone>"
                + "</Contact>";
    }

}
