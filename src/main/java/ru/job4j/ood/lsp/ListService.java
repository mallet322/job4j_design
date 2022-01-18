package ru.job4j.ood.lsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 2 пример.
 * Есть класс и метод который объединяет 2 коллекции.
 * Если в первом случае вы возвращаем в методе new ArrayList<>(); - то все работает нормально.
 * Но если мы будем возвращать Collections.emptyList(); - то свалимся с UnsupportedOperationException,
 * потому что в этом методе используется реализация AbstractList (родитель ArrayList),
 * где реализация метода add() бросает ексепшн и отличается от ArrayList.
 *
 * Прикол, полчается, что в библиотеке джавы нарушен принцип lsp :)
 */
public class ListService {

    public static List<String> addStrings(List<String> source, List<String> toAdd) {
        source.addAll(toAdd);
        return source;
    }

    private static List<String> getEmptyList() {
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        List<String> emptyList = getEmptyList();
        List<String> toAdd = new ArrayList<>();
        toAdd.add("1");
        toAdd.add("2");
        toAdd.add("3");
        System.out.println(addStrings(emptyList, toAdd));
    }

}
