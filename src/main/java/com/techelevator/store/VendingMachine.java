package com.techelevator.store;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    //Variables
    private static final File FILE = new File("vendingmachine.csv");

    private List<Product> products = new ArrayList<>();
    private BigDecimal Balance;

    //Constructors
    public VendingMachine() {
        //Read the file and assign products to the products list
        try (Scanner fileReader = new Scanner(FILE)) {
            List<String> fileLines = new ArrayList<>();
            while (fileReader.hasNextLine()) {
                fileLines.add(fileReader.nextLine());
            }
            for (String fileLine : fileLines) {
                String[] valueArray = fileLine.split("\\|");
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(valueArray[1]));

                switch (valueArray[3]) {
                    case "Gum":
                        products.add(new Gum(valueArray[0], price, valueArray[2]));
                        break;
                    case "Chip":
                        products.add(new Chip(valueArray[0], price, valueArray[2]));
                        break;
                    case "Candy":
                        products.add(new Candy(valueArray[0], price, valueArray[2]));
                        break;
                    case "Drink":
                        products.add(new Drink(valueArray[0], price, valueArray[2]));
                        break;
                    default:
                        System.out.println("Problem with the files");
                        System.exit(1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            System.exit(1);
        }
    }

    //Getters and Setters
    public BigDecimal getBalance() {
        return Balance;
    }

    public void setBalance(BigDecimal balance) {
        Balance = balance;
    }

    //Methods
    public void displayList() {
        for (Product product : products) {
            String productString = product.getSlotLocation() + ") " + product.getName()
                    + " " + (product.getQuantity() == 0 ? "Sold Out!" : product.getQuantity());
            String firstChar = product.getSlotLocation().substring(0, 1);
            String productRow = "";

            if (productRow.substring(0, 1).equals(firstChar)) {
                productRow += " " + productString;
            } else {
                System.out.println(productRow);
                productRow = productString;
            }
        }
        System.out.println("");
    }
}



