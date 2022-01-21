package ru.job4j.ood.dip;

/**
 * Пример 2.
 * Верхнеуровневый класс PasswordReminder напрямую вызывает подклчение из низкоуровневого класса SQLConnection.
 * Т.к. не через абстракцию, DIP нарушен.
 */
public class Ex2 {

    class PasswordReminder {

        private final SQLConnection dbConnection;

        public PasswordReminder(SQLConnection dbConnection) {
            this.dbConnection = dbConnection;
        }

        void connection() {
            dbConnection.getConnection();
        }
    }

    class SQLConnection  {

        public void getConnection() {
            System.out.println("Database connection");         }

    }

}
