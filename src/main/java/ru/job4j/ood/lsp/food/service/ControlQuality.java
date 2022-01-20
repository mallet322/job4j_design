package ru.job4j.ood.lsp.food.service;

import ru.job4j.ood.lsp.food.model.Food;
import ru.job4j.ood.lsp.food.store.Store;

import java.util.List;

public class ControlQuality {

    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distributeToStore(Food food) {
        for (Store store : stores) {
            store.add(food);
        }
    }

}
