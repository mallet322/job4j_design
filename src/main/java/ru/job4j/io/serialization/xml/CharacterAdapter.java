package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CharacterAdapter extends XmlAdapter<String, Character> {

    @Override
    public Character unmarshal(String v) {
        return v.charAt(0);
    }

    @Override
    public String marshal(Character v) {
        return String.valueOf(v);
    }

}
