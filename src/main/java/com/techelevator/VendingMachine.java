package com.techelevator;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    List<Products> products = new ArrayList<>();
    BigDecimal Balance;

    static final File FILE = new File("vendingmachine.csv");

    public VendingMachine() {
        try (Scanner fileReader = new Scanner(FILE)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                products.add()

            }

        }
    }

    @Overwrite
    public void sort(List<Products> products) {

    }

    public void displayList() {

        for (i = 0; i < ; i++)

    }



}
