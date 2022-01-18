package ru.job4j.ood.lsp;

/**
 * 1 пример.
 *
 * В некоторых случаях можно считать что квадрат происхоит от прямоугльника, у него просто все стороны равны.
 * В этом примере существует класс, который вычисляет площадь прямоугольника.
 * Если мы унаслелуем от прямоугльника квадрат, изменяя у него сеттеры для длины и ширины,
 * а потом попытаемся в методе getFigure() возвращать Square, то нарушим принцип LSP.
 *
 */
public class AreaService {

    public int getArea() {
        var rec = getFigure();
        rec.setHeight(3);
        rec.setWidth(4);
        return  rec.getHeight() * rec.getWidth();
    }

    public Rectangle getFigure() {
        return new Square();
    }


    public class Rectangle {

        private int width;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

    }

    public class Square extends Rectangle {

        private int width;
        private int height;

        @Override
        public void setWidth(int width) {
            this.height = width;
            this.width = width;
        }

        @Override
        public void setHeight(int height) {
            this.height = height;
            this.width = height;
        }

    }

}
