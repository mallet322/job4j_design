package ru.job4j.ood.dip;

/**
 * Пример 1.
 * Существует бизнесс логика, в которой верхние уровни ссылаются сразу на реализацию,
 * а не на абстракцию, нарушение DIP.
 */
public class Ex1 {

    class Controller {

        private final Service service;

        public Controller(Service service) {
            this.service = service;
        }

        void getSomething(String something) {
            service.getBySomething(something);
        }
    }

    class Service {

        private final DAO dao;

        public Service(DAO dao) {
            this.dao = dao;
        }

        public void getBySomething(String something) {
            dao.findBySomething(something);
        }
    }

    class DAO {

        void findBySomething(String something) {
            System.out.println("getting by something object from db");
        }

    }

}
