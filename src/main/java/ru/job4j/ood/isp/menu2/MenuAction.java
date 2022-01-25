package ru.job4j.ood.isp.menu2;

public class MenuAction implements Action {

    @Override
    public void execute(Item item) {
        System.out.println("Something with root level " + item.getName());
    }

}
