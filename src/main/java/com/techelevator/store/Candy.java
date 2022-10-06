package com.techelevator.store;

import java.math.BigDecimal;

public class Candy extends Product {
    public Candy(String name, BigDecimal price, String slotLocation) {
        super(name, price, slotLocation);
    }
    //Method
    public int eatingSound() {
        return 2;
    }
}
