package ru.job4j.gc;

import org.openjdk.jol.info.GraphLayout;

public class GCDemonstration {

    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    /**
     * Я создал 2 объекта юзеров, один с полями, один без полей.
     *
     * Про использование библиотеки JOL инфы в задании не было,
     * я пользовался ей для понимания, почему размер объекта становился больше.
     *
     * В первом случае у объекта без полей размер равен 16 байт, т.к. содержит он object header.
     *
     * Во втором случае у объекта с двумя полями равен 24 байт
     * т.к. содержит он object header 16 байт + 2 поля по 4 байта.
     *
     * В третьем случае размер объекта 96, т.к. он содержит:
     *   1. object header + 2 поля по 4 байта - 24 байта
     *   2. т.к. оба поля имеют ссылочный типа данных, размер этих объектов в хипе так же учитывается:
     *      Integer - 16 байт,
     *      String 24 байта + массив чаров внутри него - 32 байт.
     *      Итого: 24 header + 16 байт Integer + 24 байта String + 32 байта массива символов внутри String = 96 байт.
     */
    public static void getObjectsSizeInfo() {
        var emptyUser = new EmptyUser();
        System.out.println(GraphLayout.parseInstance(emptyUser).toPrintable());
        long emptyUserSize = GraphLayout.parseInstance(emptyUser).totalSize();
        System.out.println("Юзер без полей - " + emptyUserSize);

        System.out.println("========================");

        var notInitializedUser = new User();
        System.out.println(GraphLayout.parseInstance(new User()).toPrintable());
        long notInitializedUserSize = GraphLayout.parseInstance(notInitializedUser).totalSize();
        System.out.println("Юзер без инициализированных полей - " + notInitializedUserSize);

        System.out.println("========================");

        var initializedUser = new User(100000, "User100000");
        System.out.println(GraphLayout.parseInstance(initializedUser).toPrintable());
        long initializedUserSize = GraphLayout.parseInstance(initializedUser).totalSize();
        System.out.println("Юзер с инициализированными полями - " + initializedUserSize);
    }

    /**
     * Здесь разбирался с работой сборщика мусора.
     * У юзера переопределил метод finalize();
     * В параметрах ВМ установил размер хипа - 3мб.
     *
     * В цикле создавал 6000 - 10000 объектов без ссылки и наблюдал различное поведение.
     * В одних случаях freeMemory достигала после работы цикла 1мб, в одних меньше.
     * Явно GC вызывал пару раз, при таком способе картина была похожей.
     */
    public static void getGCWorkDemonstration() {
        info();
        for (int i = 0; i < 6000; i++) {
            new User(i, "User" + i);
        }
        info();
    }

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory);
        System.out.printf("Total: %d%n", totalMemory);
        System.out.printf("Max: %d%n", maxMemory);
    }

    public static void main(String[] args) {
        getObjectsSizeInfo();
        getGCWorkDemonstration();
    }

}
