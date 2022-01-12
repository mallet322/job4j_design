package ru.job4j.ood.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findEl(value, comparator, p -> p < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findEl(value, comparator, p -> p > 0);
    }

    private <T> T findEl(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T temp = value.get(0);
        for (T el : value) {
            if (predicate.test(comparator.compare(temp, el))) {
                temp = el;
            }
        }
        return temp;
    }

}
