package com.techelevator.store;

import com.techelevator.enums.Sound;

import java.math.BigDecimal;

public class Gum extends Product {
    public Gum(String slotLocation, String name, BigDecimal price) {
        super(slotLocation, name, price);
    }
    //Methods
    public Sound getSoundType() {
        return Sound.GUM_SOUND;
    }
}
