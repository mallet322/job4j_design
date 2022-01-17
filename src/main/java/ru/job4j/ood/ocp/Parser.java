package ru.job4j.ood.ocp;

/**
 * 2 пример.
 *
 * Абстракция, которая парсит какой-то объект в необходимое расширение с помощью 3-х методов.
 * Если понадобится добавить парсинг в новое расширение, то принцип OCP будет нарушен.
 */
public interface Parser {

    void parseInJSON(Object argument);
    void parseInXML(Object argument);
    void parseInYML(Object argument);

}
