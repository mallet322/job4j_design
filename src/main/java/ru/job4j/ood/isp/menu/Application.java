package ru.job4j.ood.isp.menu;

public class Application {

    public static void main(String[] args) {
        var menu = getMenu();
        menu.displayMenu();
    }

    private static MenuItem getMenu() {
        MenuItem allTasks = new MenuComposite("Root", new ActionImpl());

        MenuItem task1 = new MenuComposite("Task 1", new ActionImpl());
        MenuItem task2 = new MenuComposite("Task 2", new ActionImpl());

        allTasks.add(task1);
        allTasks.add(task2);

        MenuItem task11 = new MenuComposite("Task 1.1", new ActionImpl());
        MenuItem task12 = new MenuComposite("Task 1.2", new ActionImpl());

        task1.add(task11);
        task1.add(task12);

        MenuItem task21 = new MenuComposite("Task 2.1", new ActionImpl());
        task2.add(task21);

        MenuItem task111 = new MenuComposite("Task 1.1.1", new ActionImpl());
        task11.add(task111);

        return allTasks;
    }

}
