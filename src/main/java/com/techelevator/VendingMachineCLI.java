package com.techelevator;

import com.techelevator.store.Product;
import com.techelevator.store.VendingMachine;
import com.techelevator.view.Menu;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private VendingMachine vendingMachine = new VendingMachine();
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
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				getList();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
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
	 *  Reads from figureOutChange method and returns an integer list of length (3)
	 *  0 = quarters, 1 = dimes, 2 = nickels
	 */
	public void giveChangeBack() {
		List<Integer> coinsCount = vendingMachine.figureOutChange();
		System.out.printf("%nYour change is: %s quarters, %s dimes, %s nickels", coinsCount.get(0), coinsCount.get(1), coinsCount.get(2));
	}

}
