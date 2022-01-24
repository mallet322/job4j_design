package ru.job4j.ood.isp.menu2;

public interface MenuService {

    void add(Item item);

    Item find(String itemName);

    Action select(String itemName);

}
