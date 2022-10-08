package com.techelevator.store;

import java.math.BigDecimal;

public class Chip extends Product{

    public Chip(String slotLocation, String name, BigDecimal price) {
        super(slotLocation, name, price);
    }
    //Method
    public int typeSoundNumber() {
        return 1;
    }
}
