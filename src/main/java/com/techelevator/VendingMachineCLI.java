package com.techelevator;

import com.techelevator.store.Product;
import com.techelevator.store.VendingMachine;
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };

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
				vendingMachine.getList();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
			}
		}
	}
	public void getList() { //change name to getList
		String productRow = " ";
		for (Product product : vendingMachine.getProducts()) {
			String itemDisplayed = product.getSlotLocation() + ") " + product.getName()
					+ " " + (product.getQuantity() == 0 ? "Sold Out!" : product.getQuantity());
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

}
