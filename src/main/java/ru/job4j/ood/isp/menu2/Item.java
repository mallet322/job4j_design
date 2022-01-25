package ru.job4j.ood.isp.menu2;

import java.util.ArrayList;
import java.util.List;

public class Item {

    private String name;
    private final List<Item> childs = new ArrayList<>();
    private final Action action;

    public Item(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addChild(Item item) {
        childs.add(item);
    }

    public List<Item> getChilds() {
        return childs;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }

}
