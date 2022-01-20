package ru.job4j.ood.lsp.food.store.impl;

import ru.job4j.ood.lsp.food.model.Food;
import ru.job4j.ood.lsp.food.store.Store;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private final List<Food> warehouseStorage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        var result = false;
        if (checkQualityPercent(food)) {
            warehouseStorage.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public void printFoodsInStorage() {
        warehouseStorage.forEach(f -> System.out.println("Food in warehouse: " + f));
    }

    @Override
    public boolean checkQualityPercent(Food food) {
        var percent = getExpiredDatePercent(food);
        return percent < 25 && percent > 0;
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(warehouseStorage);
    }

}
