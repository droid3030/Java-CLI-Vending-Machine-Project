package com.techelevator.store;

import com.techelevator.enums.Sound;

import java.math.BigDecimal;

public class Drink extends Product {
    public Drink(String slotLocation, String name, BigDecimal price) {
        super(slotLocation, name, price);
    }
    //Method
    public Sound getSoundType() {
        return Sound.DRINK_SOUND;
    }
}
