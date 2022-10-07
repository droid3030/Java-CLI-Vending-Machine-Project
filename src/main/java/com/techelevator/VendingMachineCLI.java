package com.techelevator;

import com.techelevator.store.Product;
import com.techelevator.store.VendingMachine;
import com.techelevator.view.Menu;
import org.w3c.dom.ls.LSOutput;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH = "Finish";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH};


    private VendingMachine vendingMachine = new VendingMachine();
    private Menu menu;
    private Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }

    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        while (true) {
            String choiceFromMenu = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choiceFromMenu.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                getList();
            } else if (choiceFromMenu.equals(MAIN_MENU_OPTION_PURCHASE)) {
                while (true) {
                    System.out.printf("%nCurrent money provided: %s%n", vendingMachine.getBalance());
                    String choiceFromPurchase = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

                    if (choiceFromPurchase.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                        takeMoneyInput();
                    } else if (choiceFromPurchase.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                        getList();
                    } else if (choiceFromPurchase.equals(PURCHASE_MENU_OPTION_FINISH)) {
                        break;
                    }
                }
            } else if (choiceFromMenu.equals(MAIN_MENU_OPTION_EXIT)) {
                giveChangeBack();
                System.out.printf("%nThank you for using our vending machine!");
                System.exit(1);
            }
        }
    }

    /**
     * Loops through list of products and displays (slotLocation, name, price || sold out) in each row.
     * Checks for the slot location of each product and prints each different letter in its respective row
     */
    public void getList() {
        String productRow = " ";
        for (Product product : vendingMachine.getProducts()) {
            String itemDisplayed = product.getSlotLocation() + ") " + product.getName()
                    + " " + (product.getQuantity() == 0 ? "Sold Out!" : product.getPrice());
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
     * Reads from figureOutChange method and returns an integer list of length (3)
     * 0 = quarters, 1 = dimes, 2 = nickels
     */
    public void giveChangeBack() {
        List<Integer> coinsCount = vendingMachine.figureOutChange();
        System.out.printf("%nYour change is: %s quarters, %s dimes, %s nickels", coinsCount.get(0), coinsCount.get(1), coinsCount.get(2));
    }

    public void takeMoneyInput() {
        System.out.printf("%nPlease enter a money amount >>> ");
        BigDecimal moneyInput = null;
        try {
            moneyInput = BigDecimal.valueOf(Double.parseDouble(userInput.nextLine()));
            if (moneyInput.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Nice Try");
            } else {
                vendingMachine.feedMoney(moneyInput);
            }
        } catch (NumberFormatException e) {
            //Eat the exception and display a message to the user below.
        }
        if (moneyInput == null) {
            System.out.println("The input you gave was not valid");
        }

    }
}
