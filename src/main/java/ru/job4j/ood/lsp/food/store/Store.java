package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.model.Food;

import java.util.List;

public interface Store {

    void add(Food food, Double percent);

    void printFoodsInStorage();

    boolean checkQualityPercent(Double percent);

    List<Food> getAll();

}
