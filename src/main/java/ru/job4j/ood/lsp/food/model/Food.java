package ru.job4j.ood.lsp.food.model;

import java.time.LocalDate;

public class Food {

    private String name;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private Double price;
    private Double discount;

    public Food(String name,
                LocalDate expiryDate,
                LocalDate createDate,
                Double price,
                Double discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return String.format(
                "Food: Name %s, expiryDate %s, createDate %s, price %f, discount %f",
                name,
                expiryDate,
                createDate,
                price,
                discount);
    }

}
