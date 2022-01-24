package ru.job4j.ood.isp.menu2;

import java.util.ArrayList;
import java.util.List;

public class Menu implements MenuService, Print {

    private final List<Item> items = new ArrayList<>();

    @Override
    public void print() {
        items.forEach(item -> {
            System.out.println(item);
            select(item.getName());
        });
    }

    @Override
    public void add(Item item) {
        items.add(item);
        for (var child : item.getChilds()) {
            add(child);
        }
    }

    @Override
    public Item find(String itemName) {
        Item result = null;
        for (Item item : items) {
            if (itemName.equals(item.getName())) {
                result = item;
            }
        }
        return result;
    }

    @Override
    public Action select(String name) {
        return find(name).getAction();
    }

    public static void main(String[] args) {
        Item item = new Item("Меню:", List.of(
                new Item("Задача 1", List.of(
                        new Item("Задача 1.1", new ArrayList<>(), new ActionImpl()),
                        new Item("Задача 1.2", new ArrayList<>(), new ActionImpl())
                ), new ActionImpl()),
                new Item("Задача 2", List.of(
                        new Item("Задача 2.1", List.of(
                                new Item("Задача 2.1.1", new ArrayList<>(), new ActionImpl())
                        ), new ActionImpl())
                ), new ActionImpl())), new ActionImpl());
        Menu menu = new Menu();
        menu.add(item);
        menu.print();
    }

}
