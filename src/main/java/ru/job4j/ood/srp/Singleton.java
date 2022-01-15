package ru.job4j.ood.srp;

/**
 * 1 пример.
 *
 * Синглтон по мимо своих обязяанностей в методе makeSomeone() так же занимается и инициализацией своего инстанса,
 * тем самым нарушая принцип единой отвественности.
 */
public class Singleton {

    private static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void makeSomeone() {

    }

}
