package ru.job4j.ood.lsp.food.service;

import ru.job4j.ood.lsp.food.model.Food;
import ru.job4j.ood.lsp.food.store.Store;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class ControlQuality {

    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distributeToStore(Food food) {
        for (Store store : stores) {
            var percent = getExpiredDatePercent(food.getCreateDate(), food.getExpiryDate());
            store.add(food, percent);
        }
    }

    public static Double getExpiredDatePercent(LocalDate createDate, LocalDate expiryDate) {
        var currentDate = LocalDate.now();
        var expiresDays = Double.valueOf(
                Duration.between(createDate.atStartOfDay(), expiryDate.atStartOfDay()).toDays());
        var reminderOfExpiresDays = Double.valueOf(
                Duration.between(currentDate.atStartOfDay(), expiryDate.atStartOfDay()).toDays());
        return (double) Math.round((reminderOfExpiresDays / expiresDays) * 100);
    }

}
