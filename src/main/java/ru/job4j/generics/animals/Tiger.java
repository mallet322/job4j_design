package ru.job4j.generics.animals;

public class Tiger extends Predator {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tiger{"
                + "name='" + name
                + '\''
                + '}';
    }

}
