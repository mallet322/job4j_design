package ru.job4j.ood.lsp.food;

import ru.job4j.ood.lsp.food.model.Food;
import ru.job4j.ood.lsp.food.service.ControlQuality;
import ru.job4j.ood.lsp.food.store.Store;
import ru.job4j.ood.lsp.food.store.impl.Shop;
import ru.job4j.ood.lsp.food.store.impl.Trash;
import ru.job4j.ood.lsp.food.store.impl.Warehouse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {
         Food meat = new Food("Куриная грудка",
                 LocalDate.of(2022, 2, 1),
                 LocalDate.of(2022, 1, 1),
                 259.99,
                 0.0);
        Food rice = new Food("Рис",
                LocalDate.of(2022, 1, 25),
                LocalDate.of(2022, 1, 1),
                90.0,
                0.0);
        Food milk = new Food("Молоко",
                LocalDate.of(2022, 1, 15),
                LocalDate.of(2022, 1, 1),
                70.0,
                0.0);
        Food apple = new Food("Яблоко",
                LocalDate.of(2022, 4, 1),
                LocalDate.of(2022, 1, 1),
                100.0,
                30.0);
        List<Food> foods = Arrays.asList(meat, milk, rice, apple);
        Warehouse warehouse = new Warehouse();
        Shop shop = new Shop();
        Trash trash = new Trash();
        List<Store> stores = Arrays.asList(shop, warehouse, trash);
        ControlQuality quality = new ControlQuality(stores);
        for (Food food : foods) {
            quality.distributeToStore(food);
        }
        warehouse.printFoodsInStorage();
        shop.printFoodsInStorage();
        trash.printFoodsInStorage();
    }

}
