package com.techelevator.store;

import java.math.BigDecimal;

public class Candy extends Product {
    public Candy(String slotLocation, String name, BigDecimal price) {
        super(slotLocation, name, price);
    }
    //Method
    public int eatingSound() {
        return 2;
    }
}
