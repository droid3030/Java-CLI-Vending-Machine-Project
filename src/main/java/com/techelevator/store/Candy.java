package com.techelevator.store;

import com.techelevator.enums.Sound;

import java.math.BigDecimal;

public class Candy extends Product {
    public Candy(String slotLocation, String name, BigDecimal price) {
        super(slotLocation, name, price);
    }
    //Method
    public Sound getSoundType() {
        return Sound.CANDY_SOUND;
    }
}
