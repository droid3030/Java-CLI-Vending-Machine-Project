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
                        products.add(new Chip(valueArray[0], valueArray[1], price));
                        break;
                    case "Candy":
                        products.add(new Candy(valueArray[0], price, valueArray[2]));
                        break;
                    case "Drink":
                        products.add(new Drink(valueArray[0], price, valueArray[2]));
                        break;
                    default:
                        System.err.println("Problem with the files");
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
    } //Currency

    public void setBalance(BigDecimal balance) {
        Balance = balance;
    }

    //Methods
    public void displayList() { //change name to getList
        for (Product product : products) {
            String itemDisplayed = product.getSlotLocation() + ") " + product.getName()
                    + " " + (product.getQuantity() == 0 ? "Sold Out!" : product.getQuantity());
            String itemFirstChar = product.getSlotLocation().substring(0, 1);
            String productRow = "";

            if (productRow.substring(0, 1).equals(itemFirstChar)) {
                //Adds to an existing line.
                productRow += " " + itemDisplayed;
            } else {
                //Prints out line and make a new row.
                System.out.println(productRow);
                productRow = itemDisplayed;
            }
        }
        System.out.println("");
    }
}
