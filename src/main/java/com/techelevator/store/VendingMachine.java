package com.techelevator.store;

import com.techelevator.enums.CheckValidity;
import com.techelevator.exceptions.InputEnteredException;

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

    private BigDecimal salesTotal = new BigDecimal("0");
    private List<Product> products = new ArrayList<>();
    private BigDecimal balance = new BigDecimal("0");

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
                BigDecimal price = new BigDecimal(valueArray[2]);
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
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Product> getProducts() {
        return products;
    }

    public BigDecimal getSalesTotal() {
        return salesTotal;
    }

    //Methods

    /**
     * A method that adds to the salesTotal variable whenever it's called
     * @param salesTotal the amount of money you're adding to salesTotal.
     */
    private void addToSalesTotal(BigDecimal salesTotal) {
        this.salesTotal = this.salesTotal.add(salesTotal);
    }

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

    /**
     * When an item is purchased, it calls this method and updates both the balance and the stock
     * by calling the set methods.
     *
     * @param product the current product being sold.
     */
    private void updateInventory(Product product) {
        product.setQuantity(product.getQuantity() - 1);
        setBalance(getBalance().subtract(product.getPrice()));
    }

    /**
     * Checks if the item exists, and if you can buy that item.
     *
     * @param enteredSlotLocation used in the for loop to return the specific product.
     * @return the desired product to the menu.
     */
    public Product purchaseItem(String enteredSlotLocation) {
        Product resultProduct = null;

        for (Product product : products) {
            if (enteredSlotLocation.toUpperCase().equals(product.getSlotLocation())) {
                int quantity = product.getQuantity();
                BigDecimal balance = getBalance();
                BigDecimal price = product.getPrice();

                if (quantity == 0) {
                    product.setValidity(CheckValidity.NOT_ENOUGH_STOCK);
                } else if (balance.compareTo(price) < 0) {
                    product.setValidity(CheckValidity.NOT_ENOUGH_MONEY);
                } else {
                    updateInventory(product);
                    addToSalesTotal(price);
                    product.setValidity(CheckValidity.VALID);
                }
                resultProduct = product;
            }
        }
        if (resultProduct == null) {
            throw new InputEnteredException();
        }
        return resultProduct;
    }
}
