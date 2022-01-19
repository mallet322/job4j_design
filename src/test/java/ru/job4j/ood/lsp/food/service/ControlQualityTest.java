package ru.job4j.ood.lsp.food.service;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.ood.lsp.food.model.Food;
import ru.job4j.ood.lsp.food.store.Store;
import ru.job4j.ood.lsp.food.store.impl.Shop;
import ru.job4j.ood.lsp.food.store.impl.Trash;
import ru.job4j.ood.lsp.food.store.impl.Warehouse;

import java.time.LocalDate;
import java.util.List;

public class ControlQualityTest {

    @Test
    public void whenDistributeToWarehouse() {
        Food food = new Food("Рис",
                LocalDate.of(2022, 1, 20),
                LocalDate.of(2022, 1, 1),
                90.0,
                0.0);
        Warehouse warehouse = new Warehouse();
        List<Store> stores = List.of(warehouse);
        ControlQuality quality = new ControlQuality(stores);
        quality.distributeToStore(food);
        List<Food> foodsFromStore = warehouse.getAll();
        Assert.assertEquals(food, foodsFromStore.get(0));
    }

    @Test
    public void whenDistributeToShop() {
        Food food = new Food("Куриная грудка",
                LocalDate.of(2022, 2, 1),
                LocalDate.of(2022, 1, 1),
                156.98,
                0.0);
        Shop shop = new Shop();
        List<Store> stores = List.of(shop);
        ControlQuality quality = new ControlQuality(stores);
        quality.distributeToStore(food);
        List<Food> foodsFromStore = shop.getAll();
        Assert.assertEquals(food, foodsFromStore.get(0));
    }

    @Test
    public void whenDistributeToShopWithDiscount() {
        Food food = new Food("Яблоко",
                LocalDate.of(2022, 4, 1),
                LocalDate.of(2022, 1, 1),
                100.0,
                30.0);
        Shop shop = new Shop();
        List<Store> stores = List.of(shop);
        ControlQuality quality = new ControlQuality(stores);
        quality.distributeToStore(food);
        List<Food> foodsFromStore = shop.getAll();
        Assert.assertEquals(food, foodsFromStore.get(0));
        Double newPrice = 70.0;
        Assert.assertEquals(newPrice, food.getPrice());
    }

    @Test
    public void whenDistributeToTrash() {
        Food food = new Food("Молоко",
                LocalDate.of(2022, 1, 15),
                LocalDate.of(2022, 1, 1),
                70.0,
                0.0);
        Trash trash = new Trash();
        List<Store> stores = List.of(trash);
        ControlQuality quality = new ControlQuality(stores);
        quality.distributeToStore(food);
        List<Food> foodsFromStore = trash.getAll();
        Assert.assertEquals(food, foodsFromStore.get(0));
    }

}