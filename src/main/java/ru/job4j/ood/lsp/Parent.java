package ru.job4j.ood.lsp;

/**
 * 3 пример.
 * Есть суперкласс с методом.
 * Есть его нслденик, в котором не реализован метод родителя.
 * LSP нарушен.
 */
public class Parent {

    public void doSomething() {
        System.out.println("Something...");
    }

    public class Child extends Parent {
        @Override
        public void doSomething() {

        }
    }
}
