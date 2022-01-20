package ru.job4j.ood.isp;

/**
 * Пример 1
 * Существует интерфейс, который реализуют 2 класса.
 * Для первого класса требуются все методы интерфейса, для второго только перывые 2 метода.
 * Т.к. во втором классе 3 метод будет пустым - это нарушение принципа ISP.
 */
public class Ex1 {

    interface CRUDService {
        void insert();
        void update();
        void delete();
    }

    static class PersonWithDelete implements CRUDService {

        @Override
        public void insert() {
            System.out.println("insert");
        }

        @Override
        public void update() {
            System.out.println("update");
        }

        @Override
        public void delete() {
            System.out.println("delete");
        }

    }

    static class PersonWithoutDelete implements CRUDService {

        @Override
        public void insert() {
            System.out.println("insert");
        }

        @Override
        public void update() {
            System.out.println("update");

        }

        @Override
        public void delete() {

        }

    }

}
