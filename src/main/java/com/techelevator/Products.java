package com.techelevator;

import java.math.BigDecimal;

public class Products {

    public Products(String name, BigDecimal price, String slotLocation) {
        this.name = name;
        this.price = price;
        this.slotLocation = slotLocation;
    }

    String name;
    BigDecimal price;
    String slotLocation;
    int Quantity = 5;



    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public String getSlotLocation() {
        return slotLocation;
    }


    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void sellStock(Products product) {
        product.setQuantity(getQuantity() - 1);

    }



}
