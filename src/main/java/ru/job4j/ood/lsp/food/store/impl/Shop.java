package ru.job4j.ood.lsp.food.store.impl;

import ru.job4j.ood.lsp.food.model.Food;
import ru.job4j.ood.lsp.food.store.Store;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private final List<Food> shopStorage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        var result = false;
        var percent = getExpiredDatePercent(food);
        if (checkQualityPercent(percent)) {
            shopStorage.add(food);
            result = true;
        } else if (checkQualityPercentForDiscount(percent)) {
            setNewPrice(food);
            shopStorage.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public boolean checkQualityPercent(Double percent) {
        return percent > 25 && percent < 75;
    }

    public boolean checkQualityPercentForDiscount(Double percent) {
        return percent >= 75 && percent <= 100;
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(shopStorage);
    }

    @Override
    public void printFoodsInStorage() {
        shopStorage.forEach(f -> System.out.println("Food in shop: " + f));
    }

    private void setNewPrice(Food food) {
        var newPrice = food.getPrice() - food.getDiscount();
        food.setPrice(newPrice);
    }

}
