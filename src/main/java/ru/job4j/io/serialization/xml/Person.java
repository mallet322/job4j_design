package ru.job4j.io.serialization.xml;

import java.util.Arrays;
import java.util.List;

public class Person {

    private final String name;
    private final int age;
    private final char sex;
    private final boolean isMale;
    private final String[] numbers;
    private final List<Contact> contacts;

    public Person(String name, int age, char sex, boolean isMale, String[] numbers, List<Contact> contacts) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.isMale = isMale;
        this.numbers = numbers;
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getSex() {
        return sex;
    }

    public boolean isMale() {
        return isMale;
    }

    public String[] getNumbers() {
        return numbers;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", sex=" + sex
                + ", isMale=" + isMale
                + ", numbers=" + Arrays.toString(numbers)
                + ", contacts=" + contacts
                + '}';
    }

}
