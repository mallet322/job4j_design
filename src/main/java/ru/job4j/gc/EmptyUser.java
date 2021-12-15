package ru.job4j.gc;

public class EmptyUser {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("User successfully garbage collected");
    }

}
