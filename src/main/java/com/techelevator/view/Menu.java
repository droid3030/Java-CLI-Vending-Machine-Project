package com.techelevator.view;

import com.techelevator.store.Product;
import com.techelevator.store.VendingMachine;

import java.io.*;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    private static File log_book = new File("Log.txt");
    private static File sales_book = new File("Sales.txt");
    private PrintWriter out;
    private Scanner in;
    private VendingMachine vendingMachine = new VendingMachine();
    private NumberFormat currency = NumberFormat.getCurrencyInstance();

    public Menu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }

    private void displayMenuOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

    /**
     * Loops through list of products and displays (slotLocation, name, price || sold out) in each row.
     * Checks for the slot location of each product and prints each different letter in its respective row
     */
    public void getList() {

        String productRow = " ";
        for (Product product : vendingMachine.getProducts()) {
            String price = currency.format(product.getPrice());
            String itemDisplayed = product.getSlotLocation() + ") " + product.getName()
                    + " " + (product.getQuantity() == 0 ? "Sold Out!" : price);
            String itemFirstChar = product.getSlotLocation().substring(0, 1);

            if (productRow.substring(0, 1).equals(itemFirstChar)) {
                //Adds to an existing line.
                productRow += " " + itemDisplayed;
            } else {
                //Prints out line and make a new row.
                System.out.println(productRow);
                productRow = itemDisplayed;
            }
        }
        System.out.println(productRow);
        System.out.println("");
    }

    /**
     * Stores user money input as a double
     * compares it to our BigDecimal value
     * stores it in the machine
     * gives invalid input if not enough money or null
     */
    public void takeMoneyInput() {
        System.out.printf("%nPlease enter a money amount >>> ");
        BigDecimal moneyInput = null;
        try {
            moneyInput = BigDecimal.valueOf(Double.parseDouble(in.nextLine()));
            if (moneyInput.compareTo(BigDecimal.ZERO) > 0) {
                vendingMachine.feedMoney(moneyInput);
                String moneyInputAsCurrency = currency.format(moneyInput);
                logAction("FEED MONEY", moneyInputAsCurrency);
            }
        } catch (NumberFormatException e) {
            //Eat the exception and display a message to the user below.
        }
        if (moneyInput == null || moneyInput.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("The input you gave was not valid");
        }
    }

    /**
     * displays current money in the machine in a readable format
     */
    public void showBalance() {
        String balance = currency.format(vendingMachine.getBalance());
        System.out.printf("%nCurrent money provided: %s%n", balance);
    }

    /**
     * Reads from figureOutChange method and returns an integer list of length (3)
     * 0 = quarters, 1 = dimes, 2 = nickels
     */
    public void giveChangeBack() {
        String oldBalance = currency.format(vendingMachine.getBalance());
        List<Integer> coinsCount = vendingMachine.figureOutChange();
        logAction("GIVE CHANGE", oldBalance);
        System.out.printf("%nYour change is: %s quarters, %s dimes, %s nickels%n", coinsCount.get(0), coinsCount.get(1), coinsCount.get(2));
    }

    /**
     * Asks user for selection, stores it, then runs through our list of products to see if it matches
     * if it matches, there is quantity remaining, and there is enough money in the machine
     * sells/dispenses the item with sound, reduces balance, and prints selection message
     * <p>
     * else it displays "we were unable to process that transaction"
     */
    public void userPurchaseSelection() {
        int counter = 0;

        getList();
        System.out.print("Which snack would you like? ");
        String userInput = in.nextLine();

        for (Product product : vendingMachine.getProducts()) {
            userInput = userInput.toUpperCase();
            String slotLocation = product.getSlotLocation();
            int productQuantity = product.getQuantity();
            String productName = product.getName();
            BigDecimal productPrice = product.getPrice();
            String productPriceAsCurrency = currency.format(product.getPrice());
            BigDecimal balance = vendingMachine.getBalance();

            if (slotLocation.equals(userInput)) {
                if (productQuantity > 0 && balance.compareTo(productPrice) >= 0) {
                    //Will happen if there is stock, and balance is enough.
                    product.sellStock();
                    vendingMachine.subtractBalanceByItemPrice(productPrice);
                    String balanceAsCurrency = currency.format(vendingMachine.getBalance());

                    System.out.printf("%nYou have selected %s for %s. Your remaining balance is %s%n", productName, productPriceAsCurrency, balanceAsCurrency);
                    logAction(productName + " " + product.getSlotLocation(), productPriceAsCurrency);
                    vendingMachine.setSalesTotal(vendingMachine.getSalesTotal().add(productPrice));
                    consumingSound(product.typeSoundNumber());
                } else {
                    //Will happen if there isn't stock, or price is more than balance.
                    System.out.println("Unable to do transaction due to lack of product/balance.");
                }
                break;
            }
            counter++;
        }
        if (counter == vendingMachine.getProducts().size()) {
            //Happens if it goes through the list, and no item was given
            System.out.println("Input given was not valid");
        }
    }


    /**
     * Prints sound based on item type.
     *
     * @param number gets the value from product.typeSoundNumber()
     */
    public void consumingSound(int number) {
        switch (number) {
            case 1:
                System.out.println("Crunch Crunch, Yum!");
                break;
            case 2:
                System.out.println("Munch Munch, Yum!");
                break;
            case 3:
                System.out.println("Glug Glug, Yum!");
                break;
            case 4:
                System.out.println("Chew Chew, Yum!");
                break;
        }
    }

    /**
     * logs action for buying, feeding, and giving change into a txt file
     *
     * @param theAction can be GIVE CHANGE or FEED MONEY or (productName + slotLocation)
     * @param num can be oldBalance or moneyInput or productPrice
     */
   public void logAction(String theAction, String num) {
       DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String balance = currency.format(vendingMachine.getBalance());
       try (PrintWriter logFile = new PrintWriter(new FileWriter("log.txt", true))) {
           if ("FEED MONEY".equals(theAction) || "GIVE CHANGE".equals(theAction)) {
               logFile.format("%s %s: %s %s%n", dateFormatter.format(LocalDateTime.now()), theAction, num, balance);
           } else {
               logFile.format("%s %s %s %s%n", dateFormatter.format(LocalDateTime.now()), theAction, num, balance);
           }
       } catch (IOException e) {
           System.err.println("Exception problem");
       }
   }
   public void logSales() {
       try (PrintWriter salesFile = new PrintWriter(sales_book)) {
           for (Product product : vendingMachine.getProducts()) {
               int quantity = 5 - product.getQuantity();
               salesFile.format("%s|%d%n", product.getName(), quantity);
           }
           salesFile.format("%n**TOTAL SALES** %s", currency.format(vendingMachine.getSalesTotal()));
       } catch (FileNotFoundException e) {
           System.err.println("Sales file not found");
       }
   }
}
