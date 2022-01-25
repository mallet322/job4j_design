package ru.job4j.ood.isp.menu2;

public class Menu implements MenuService, Print {

    private static final Integer ROOT = 0;

    @Override
    public void print(Item item) {
        print(item, ROOT);
    }

    @Override
    public Action select(Item item) {
        return item.getAction();
    }

    private void print(Item root, int level) {
        System.out.println(root.getName());
        for (Item child : root.getChilds()) {
            print(child, level + 1);
        }
    }

    private Item getMenu() {
        Item root = new Item("Меню:", new MenuAction());
        Item task1 = new Item("Задача 1", new ItemAction());
        task1.addChild(new Item("Задача 1.1", new ItemAction()));
        task1.addChild(new Item("Задача 1.2", new ItemAction()));
        Item task2 = new Item("Задача 2", new ItemAction());
        Item task21 = new Item("Задача 2.1", new ItemAction());
        task2.addChild(task21);
        task21.addChild(new Item("Задача 2.1.1", new ItemAction()));
        task2.addChild(new Item("Задача 2.2", new ItemAction()));
        task2.addChild(new Item("Задача 2.3", new ItemAction()));
        root.addChild(task1);
        root.addChild(task2);
        return root;
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        Item root = menu.getMenu();
        menu.print(root);
    }

}
