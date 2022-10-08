package com.techelevator.store;

import java.math.BigDecimal;

public class Product {
    //Variables
    private String slotLocation;
    private String name;
    private BigDecimal price;
    private int Quantity = 5;

    //Constructor
    public Product(String slotLocation, String name, BigDecimal price) {
        this.slotLocation = slotLocation;
        this.name = name;
        this.price = price;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    } //Change to currency

    public String getSlotLocation() {
        return slotLocation;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    //Methods
    /**
     * Checks if there is stock of a product and reduces stock by 1 if there is
     */
    public void sellStock() {
        setQuantity(getQuantity() - 1);
    }

    /**
     * gets number to run through consumingSound()
     * @return 1 = chip, 2 = candy, 3 = drink, 4 = gum
     */
    public int typeSoundNumber() {
        return 0;
    }
}
