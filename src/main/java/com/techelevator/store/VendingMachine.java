package com.techelevator.store;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    //Variables
    private static final File FILE = new File("vendingmachine.csv");

    private List<Product> products = new ArrayList<>();
    private BigDecimal Balance = new BigDecimal("0");

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
                String slotLocation = valueArray[0];
                String name = valueArray[1];
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(valueArray[2]));
                String type = valueArray[3];

                switch (type) {
                    case "Chip":
                        products.add(new Chip(slotLocation, name, price));
                        break;
                    case "Candy":
                        products.add(new Candy(slotLocation, name, price));
                        break;
                    case "Drink":
                        products.add(new Drink(slotLocation, name, price));
                        break;
                    case "Gum":
                        products.add(new Gum(slotLocation, name, price));
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

    public List<Product> getProducts() {
        return products;
    }
    //Methods

    /**
     * Takes money from user in BigDecimal and sets balance to the amount added
     *
     * @param money the money entered
     */
    public void feedMoney(BigDecimal money) {
        if (money.compareTo(BigDecimal.ZERO) > 0) {
            setBalance(getBalance().add(money));
        }
    }

    /**
     * Figures out the change amount you can get from the Balance, and adds it to a list.
     *
     * @return
     */
    public List<Integer> figureOutChange() {
        List<Integer> coinsCount = new ArrayList<>();
        List<BigDecimal> changeToGet = new ArrayList<>(Arrays.asList(new BigDecimal("0.25"),
                new BigDecimal("0.1"), new BigDecimal("0.05")));

        for (BigDecimal change : changeToGet) {
            Integer coinCount = getBalance().divide(change).intValue();
            setBalance(getBalance().subtract(change.multiply(new BigDecimal(coinCount))));
            coinsCount.add(coinCount);
        }
        return coinsCount;
    }
    public void subtractBalanceByItemPrice(BigDecimal price) {
        setBalance(getBalance().subtract(price));
    }
}
