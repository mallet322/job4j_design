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
    public Action select(String itemName) {
        Action result = null;
        for (MenuItem item : subMenus) {
            if (itemName.equals(item.getName())) {
                result = item.getAction();
            }
        }
        return result;
    }

    @Override
    public String displayMenu() {
        var res = buildMenu(getName());
        select(getName());
        this.subMenus.forEach(MenuItem::displayMenu);
        return res;
    }

    private String buildMenu(String item) {
        StringBuilder sb = new StringBuilder();
        sb.append(item).append(System.lineSeparator());
        return sb.toString();
    }

}
