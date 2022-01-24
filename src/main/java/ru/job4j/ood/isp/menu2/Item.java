package ru.job4j.ood.isp.menu2;

import java.util.List;

public class Item {

    private String name;
    private List<Item> childs;
    private Action action;

    public Item(String name, List<Item> childs, Action action) {
        this.name = name;
        this.childs = childs;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getChilds() {
        return childs;
    }

    public void setChilds(List<Item> childs) {
        this.childs = childs;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }

}
