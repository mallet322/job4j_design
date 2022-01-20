package ru.job4j.ood.isp.menu;

public class ActionImpl implements Action {

    @Override
    public void execute(String name) {
        System.out.println("Some action with " + name);
    }

}
