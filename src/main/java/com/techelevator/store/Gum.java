package com.techelevator.store;

import java.math.BigDecimal;

public class Gum extends Product {
    public Gum(String name, BigDecimal price, String slotLocation) {
        super(name, price, slotLocation);
    }
    //Methods
    public int eatingSound() {
        return 4;
    }
}
