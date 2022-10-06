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
                if (valueArray[3] == "Gum") {
                    products.add(new Gum(valueArray[0], valueArray[1], valueArray[2]));
                } else if (valueArray[3] == "Chip") {
                    products.add(new Chip(valueArray[0], valueArray[1], valueArray[2]));
                } else if (valueArray[3] == "Candy") {
                    products.add(new Candy(valueArray[0], valueArray[1], valueArray[2]));
                } else if (valueArray[3] == "Drink") {
                    products.add(new Drink(valueArray[0], valueArray[1], valueArray[2]));
                } else {
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
            if (product.getSlotLocation().contains("A")) {
                if (product.getSlotLocation().contains("1")) {
                    System.out.println(product.getSlotLocation() + ") " + product.getName()
                            + " " + (product.getQuantity() <= 0 ? "Sold Out!" : product.getQuantity()));
                } else {
                    System.out.print(" " + product.getSlotLocation() + ") " + product.getName()
                            + " " + (product.getQuantity() <= 0 ? "Sold Out!" : product.getQuantity()));
                }
            }
            else if (product.getSlotLocation().contains("B")) {
                if (product.getSlotLocation().contains("1")) {
                    System.out.println(product.getSlotLocation() + ") " + product.getName()
                            + " " + (product.getQuantity() <= 0 ? "Sold Out!" : product.getQuantity()));
                } else {
                    System.out.print(" " + product.getSlotLocation() + ") " + product.getName()
                            + " " + (product.getQuantity() <= 0 ? "Sold Out!" : product.getQuantity()));
                }
            }
            else if (product.getSlotLocation().contains("C")) {
                if (product.getSlotLocation().contains("1")) {
                    System.out.println(product.getSlotLocation() + ") " + product.getName()
                            + " " + (product.getQuantity() <= 0 ? "Sold Out!" : product.getQuantity()));
                } else {
                    System.out.print(" " + product.getSlotLocation() + ") " + product.getName()
                            + " " + (product.getQuantity() <= 0 ? "Sold Out!" : product.getQuantity()));
                }
            }
            else if (product.getSlotLocation().contains("D")) {
                if (product.getSlotLocation().contains("1")) {
                    System.out.println(product.getSlotLocation() + ") " + product.getName()
                            + " " + (product.getQuantity() <= 0 ? "Sold Out!" : product.getQuantity()));
                } else {
                    System.out.print(" " + product.getSlotLocation() + ") " + product.getName()
                            + " " + (product.getQuantity() <= 0 ? "Sold Out!" : product.getQuantity()));
                }
            }
        }
    }
}


}
