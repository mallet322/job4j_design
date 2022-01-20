package ru.job4j.ood.isp;

/**
 * Пример 2.
 * Существует интерфейс супер трансформера, который может создавать различных транфсормеров.
 * Но когда мы начнем созадвать различных трансформеров по отдельности, то столкнемся с ошибками,
 * что класс может только один единственным трафнсормером и нарушим принцип ISP.
 */
public class Ex2 {
    interface SuperTransformer {
        void toCar();
        void toPlane();
        void toShip();
    }

    static class CarTransformer implements SuperTransformer {

        @Override
        public void toCar() {
            System.out.println("Transform to car");
        }

        @Override
        public void toPlane() {
            System.out.println("I can`t transform to plane");
        }

        @Override
        public void toShip() {
            System.out.println("I can`t transform to ship");
        }

    }
}
