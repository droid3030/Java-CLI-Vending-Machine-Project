package com.techelevator.store;

import com.techelevator.enums.Sound;

import java.math.BigDecimal;

public class Chip extends Product{

    public Chip(String slotLocation, String name, BigDecimal price) {
        super(slotLocation, name, price);
    }
    //Method
    public Sound getSoundType() {
        return Sound.CHIP_SOUND;
    }
}
