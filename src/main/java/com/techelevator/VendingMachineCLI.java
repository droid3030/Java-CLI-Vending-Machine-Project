package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH = "Finish";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH};

    private Menu menu;

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
                menu.displayList();
            } else if (choiceFromMenu.equals(MAIN_MENU_OPTION_PURCHASE)) {
                while (true) {
                    menu.showBalance();
                    String choiceFromPurchase = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

                    if (choiceFromPurchase.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                        menu.takeMoneyInput();
                    } else if (choiceFromPurchase.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                        menu.userPurchaseSelection();
                    } else if (choiceFromPurchase.equals(PURCHASE_MENU_OPTION_FINISH)) {
                        menu.giveChangeBack();
                        break;
                    }
                }
            } else if (choiceFromMenu.equals(MAIN_MENU_OPTION_EXIT)) {
                menu.logSales();
                System.exit(1);
            }
        }
    }
}
