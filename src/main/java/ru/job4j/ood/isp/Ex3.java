package ru.job4j.ood.isp;

/**
 * 3 пример.
 * Существует абстракция, которая описывает автомобиль.
 * Описывая конкретную модель (Лада Веста, например) мы указываем набор пакетов и прикольных плюшек,
 * которые она может содержать.
 * Но конекртное содержание зависит от комплектации автомобиля.
 * Когда мы начинаем реализовывать абстракцию Vesta классами, описывающими разные комплектции,
 * нарушаем принцип ISP, так как не все, что содержит люкс есть в комфорте.
 */
public class Ex3 {

    abstract class Car {
        private final String brand;
        private final String model;

        public Car(String brand, String model) {
            this.brand = brand;
            this.model = model;
        }
    }

    abstract class Lada extends Car {

        public Lada(String brand, String model) {
            super(brand, model);
        }
    }

    abstract class Vesta extends Lada {

        public Vesta(String brand, String model) {
            super(brand, model);
        }

        abstract void getWinterPackage();
        abstract void getMultimediaPackage();
        abstract void getBigWheels();
        abstract void getMetallicColor();
        abstract void getAutomatedTransmission();

    }

    class VestaLuxe extends Vesta {

        public VestaLuxe(String brand, String model) {
            super(brand, model);
        }

        @Override
        void getWinterPackage() {
            System.out.println("Got it!");
        }

        @Override
        void getMultimediaPackage() {
            System.out.println("Got it!");
        }

        @Override
        void getBigWheels() {
            System.out.println("Got it!");
        }

        @Override
        void getMetallicColor() {
            System.out.println("Got it!");
        }

        @Override
        void getAutomatedTransmission() {
            System.out.println("Got it!");
        }

    }

    class VestaComfort extends Vesta {
        public VestaComfort(String brand, String model) {
            super(brand, model);
        }

        @Override
        void getWinterPackage() {

        }

        @Override
        void getMultimediaPackage() {
            System.out.println("Got it!");
        }

        @Override
        void getBigWheels() {

        }

        @Override
        void getMetallicColor() {
            System.out.println("Got it!");
        }

        @Override
        void getAutomatedTransmission() {
            System.out.println("Got it!");
        }

    }

}
