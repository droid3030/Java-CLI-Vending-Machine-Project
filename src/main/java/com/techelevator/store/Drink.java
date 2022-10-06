package com.techelevator.store;

import java.math.BigDecimal;

public class Drink extends Product {
    public Drink(String name, BigDecimal price, String slotLocation) {
        super(name, price, slotLocation);
    }
    //Method
    public int eatingSound() {
        return 3;
    }
}
