package ru.job4j.ood.lsp.food.store.impl;

import ru.job4j.ood.lsp.food.model.Food;
import ru.job4j.ood.lsp.food.store.Store;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private final List<Food> trashStorage = new ArrayList<>();

    @Override
    public void add(Food food, Double percent) {
        if (!trashStorage.contains(food)) {
            if (checkQualityPercent(percent)) {
                trashStorage.add(food);
            }
        }
    }

    @Override
    public boolean checkQualityPercent(Double percent) {
        return percent < 0;
    }

    @Override
    public List<Food> getAll() {
        return trashStorage;
    }

    @Override
    public void printFoodsInStorage() {
        trashStorage.forEach(f -> System.out.println("Food in trash: " + f));
    }

}
