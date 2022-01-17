package ru.job4j.ood.ocp;

/**
 * Пример 3.
 * 
 * Класс, который печатает фигуры.
 * На него напряму ссылюатся класс круг и квадрат и если мы захотим печатать еще и треугольник,
 * то столкнемся с нарушением принципа OCP.
 */
public class PrintFigures {

    public void printCircle(Circle circle) {

    }
    public void printSquare(Square square) {

    }

    public static class Circle {

    }
    
    public static class Square {

    }
    
}
