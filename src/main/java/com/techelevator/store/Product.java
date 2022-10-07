package com.techelevator.store;

import java.math.BigDecimal;

public class Product {
    //Variables
    private String name;
    private BigDecimal price;
    private String slotLocation;
    private int Quantity = 5;
    //Constructor
    public Product(String name, BigDecimal price, String slotLocation) { //Change order around
        this.name = name;
        this.price = price;
        this.slotLocation = slotLocation;
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
    public void sellStock(Product product) { //not finished
        product.setQuantity(getQuantity() - 1);
    }
}
