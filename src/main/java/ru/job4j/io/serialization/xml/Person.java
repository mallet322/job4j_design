package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int age;

    @XmlJavaTypeAdapter(CharacterAdapter.class)
    @XmlAttribute
    private Character sex;

    @XmlAttribute
    private boolean isMale;

    @XmlElementWrapper(name = "numbers")
    @XmlElement(name = "value")
    private String[] numbers;

    @XmlElementWrapper(name = "contacts")
    @XmlElement(name = "contact")
    private List<Contact> contacts;

    public Person() {
    }

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

    public Character getSex() {
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
