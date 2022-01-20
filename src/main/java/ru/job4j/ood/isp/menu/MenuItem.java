package ru.job4j.ood.isp.menu;

public abstract class MenuItem {

    private String name;
    private Action action;

    public MenuItem(String name) {
        this.name = name;
    }

    public MenuItem(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public abstract void add(MenuItem component);

    public abstract void displayMenu();

}
