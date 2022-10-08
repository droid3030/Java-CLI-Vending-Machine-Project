package com.techelevator.store;

import java.math.BigDecimal;

public class Drink extends Product {
    public Drink(String slotLocation, String name, BigDecimal price) {
        super(slotLocation, name, price);
    }
    //Method
    public int typeSoundNumber() {
        return 3;
    }
}
