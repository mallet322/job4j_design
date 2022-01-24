package ru.job4j.ood.isp.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuComposite extends MenuItem {

    List<MenuItem> subMenus = new ArrayList<>();

    public MenuComposite(String name) {
        super(name);
    }

    public MenuComposite(String name, Action action) {
        super(name, action);
    }

    @Override
    public void add(MenuItem menuComponent) {
        this.subMenus.add(menuComponent);
    }

    @Override
    public void displayMenu() {
        System.out.println(getName());
        getAction().execute(getName());
        this.subMenus.forEach(MenuItem::displayMenu);
    }

}
