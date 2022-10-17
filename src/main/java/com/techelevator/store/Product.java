package com.techelevator.store;

import com.techelevator.enums.CheckValidity;
import com.techelevator.enums.Sound;

import java.math.BigDecimal;

public abstract class Product {
    //Variables
    private final int INITIAL_QUANTITY = 5;

    private String slotLocation;
    private String name;
    private BigDecimal price;
    private int Quantity = INITIAL_QUANTITY;
    private CheckValidity validity;

    //Constructor
    public Product(String slotLocation, String name, BigDecimal price) {
        this.slotLocation = slotLocation;
        this.name = name;
        this.price = price;
    }

    //Getters and Setters
    private int getINITIAL_QUANTITY() {
        return INITIAL_QUANTITY;
    }
    public String getSlotLocation() {
        return slotLocation;
    }
    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public int getQuantity() {
        return Quantity;
    }
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
    public CheckValidity getValidity() {
        return validity;
    }
    public void setValidity(CheckValidity validity) {
        this.validity = validity;
    }
    //Methods
    /**
     * gets an enum from the children classes to run through consumingSound().
     */
    public abstract Sound getSoundType();
    /**
     * Find out the amount quantities sold of this product
     */
    public int findQuantitiesSold() {
        return getINITIAL_QUANTITY() - getQuantity();
    }
}
