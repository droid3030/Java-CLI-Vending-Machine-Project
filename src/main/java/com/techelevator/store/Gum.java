package com.techelevator.store;

import java.math.BigDecimal;

public class Gum extends Product {
    public Gum(String slotLocation, String name, BigDecimal price) {
        super(slotLocation, name, price);
    }
    //Methods
    public int typeSoundNumber() {
        return 4;
    }
}
