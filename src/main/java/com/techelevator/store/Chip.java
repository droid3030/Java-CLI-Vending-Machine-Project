package com.techelevator.store;

import java.math.BigDecimal;

public class Chip extends Product{

    public Chip(String name, BigDecimal price, String slotLocation) {
        super(name, price, slotLocation);
    }
    //Method
    public int eatingSound() {
        return 1;
    }
}
