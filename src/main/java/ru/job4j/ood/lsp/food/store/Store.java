package ru.job4j.ood.lsp.food.store;

import ru.job4j.ood.lsp.food.model.Food;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public interface Store {

    boolean add(Food food);

    void printFoodsInStorage();

    boolean checkQualityPercent(Food food);

    List<Food> getAll();

    default Double getExpiredDatePercent(Food food) {
        LocalDate createDate = food.getCreateDate();
        LocalDate expiryDate = food.getExpiryDate();
        var currentDate = LocalDate.now();
        var expiresDays = Double.valueOf(
                Duration.between(createDate.atStartOfDay(), expiryDate.atStartOfDay()).toDays());
        var reminderOfExpiresDays = Double.valueOf(
                Duration.between(currentDate.atStartOfDay(), expiryDate.atStartOfDay()).toDays());
        return (double) Math.round((reminderOfExpiresDays / expiresDays) * 100);
    }

    void clear();

}
