package com.techelevator.store;

import java.math.BigDecimal;

public class Drink extends Product {
    public Drink(String name, String slotLocation,  BigDecimal price) {
        super(name, price, slotLocation);
    }
    //Method
    public int eatingSound() {
        return 3;
    }
}
