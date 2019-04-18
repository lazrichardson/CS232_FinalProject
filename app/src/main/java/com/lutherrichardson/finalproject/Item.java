package com.lutherrichardson.finalproject;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Item extends StandardItem implements Information {
    private int itemNumber;
    private String itemName;
    private int itemPriority;
    private double itemPrice;
    private boolean purchased = false;
    private int numItems;
    private int numItemsPurchased;
    private boolean prioritized;

    public Item(String itemName) { // inherited from standardItem
        super(itemName);
    }

    public Item() {
        this.itemNumber = -100;
        this.itemName = "no input";
        this.itemPriority = -1;
        this.itemPrice = -1;
        this.purchased = false;
        this.numItems = -1;
        this.numItemsPurchased = 0;
    }

    public Item(int itemNumber, String itemName, int itemPriority) {
        super(itemName);
        this.itemNumber = itemNumber;
        this.itemPriority = itemPriority;
    }

    public Item(int itemNumber, String itemName, int itemPriority, boolean purchased) {
        super(itemName);
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemPriority = itemPriority;
        this.purchased = purchased;
    }

    public boolean equals(Item itemInput) {
        // self check
        if (this == itemInput) {
            return true;
        }
        // check if it's null
        if (this == null) {
            return false;
        }
        // make sure it's the same type of class
        if (getClass() != itemInput.getClass()) {
            return false;
        }

        // compare the fields
        return Objects.equals(itemNumber, itemInput.itemNumber) &&
                Objects.equals(itemName, itemInput.itemName) &&
                Objects.equals(itemPriority, itemInput.itemPriority) &&
                Objects.equals(itemPrice, itemInput.itemPrice) &&
                Objects.equals(purchased, itemInput.purchased);
    }

    // Setters
    public void setItemNumber(int itemNumberInput) {
        itemNumber = itemNumberInput;
    }

    public void setItemName(String itemNameInput) {
        itemName = itemNameInput;
    }

    public void setItemPriority(int itemPriorityInput) {
        itemPriority = itemPriorityInput;
    }

    public void setItemPrice(double itemPriceInput) {
        itemPrice = itemPriceInput;
    }

    public void setPurchased(boolean itemWasPurchased) {
        purchased = itemWasPurchased;
    }

    public void setNumItemsPurchased(int items) {
        numItemsPurchased = items;
    }

    public void setPrioritized(boolean isPrioritized) {
        prioritized = isPrioritized;
    }

    public void setNumItems(int items) {
        numItems = items;
    }

    // Getters
    public int getItemNumber() {
        return itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public String getName() {
        return itemName;
    }

    public int getItemPriority() {
        return itemPriority;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public boolean getPurchased() {
        return purchased;
    }

    public int getnumItems() {
        return numItems;
    }

    public int getNumItemsPurchased() {
        return numItemsPurchased;
    }

    public boolean getPrioritized() {
        return prioritized;
    }


    // put item names into a list of strings
    public static String[] itemNamesToString(ArrayList<Item> list) {

        String[] newList = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            newList[i] = list.get(i).getItemName();
        }
        return newList;
    }

    // put item priorities into a list of strings
    public static int[] itemPriorityToString(Item[] list) {

        int[] newList = new int[list.length];

        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i].getItemPriority();
        }
        return newList;
    }

    // check if item name is already in a given list
    public static boolean itemInList(ArrayList<Item> list, String item) {
        int matchCounter = 0;
        boolean isNew = true;
        for (int i = 0; i < list.size(); i++) {
            if (Item.itemNamesToString(list)[i].equals(item))
                matchCounter++;
        }
        if (matchCounter > 0) {
            isNew = false;
        }
        return isNew;
    }

    // checks the priority of an item in a given list
    public static boolean priorityInList(ArrayList<Item> list, int item) {
        int matchCounter = 0;
        boolean isNew = true;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getItemPriority() == item)
                matchCounter++;
        }
        if (matchCounter > 0) {
            isNew = false;
        }
        return isNew;
    }

    public void printString(String s) {
        System.out.println(s);

    }

    public void printDouble(Double d) {
        System.out.println(d);

    }


    public static void writeItems(ArrayList<Item> list, boolean purchased, boolean ignorePurchased) { //

        String fileName = null;

        if (ignorePurchased) {
            fileName = "shoppingList.txt"; // set the name of the output file
        } else if (!ignorePurchased) {
            if (purchased) {
                fileName = "purchased.txt"; // set the name of the output file
            } else if (!purchased) {
                fileName = "notPurchased.txt"; // set the name of the output file
            }
        }
        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " +
                    fileName);
            System.exit(0);
        }

        if (ignorePurchased) {
            for (int i = 0; i < list.size(); i++) {
                outputStream.println("Item: " + list.get(i).getItemName()
                        + " | Price: $" + list.get(i).getItemPrice()
                        + " | Purchased: " + list.get(i).getNumItemsPurchased());
            }
            outputStream.close();
        } else if (!ignorePurchased) {
            for (int i = 0; i < list.size(); i++)
                if (purchased == (list.get(i).getPurchased())) {
                    outputStream.println("Item: " + list.get(i).getItemName()
                            + " | Price: $" + list.get(i).getItemPrice()
                            + " | Purchased: " + list.get(i).getNumItemsPurchased());
                }
            outputStream.close();
        }
    }

    public static void readItems(ArrayList<Item> list, boolean purchased, boolean ignorePurchased) { //

        String fileName = null;
        Scanner inputStream = null;

        if (ignorePurchased) {
            fileName = "shoppingList.txt"; // set the name of the output file
        } else if (!ignorePurchased) {
            if (purchased) {
                fileName = "purchased.txt"; // set the name of the output file
            } else if (!purchased) {
                fileName = "notPurchased.txt"; // set the name of the output file
            }
        }

        try {
            inputStream = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " +
                    fileName);
            System.exit(0);
        }
        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            System.out.println(line);
        }


    }


    public static String printShoppingList(ArrayList<Item> list, boolean purchased, boolean ignorePurchased) { //

        String output = "";


        if (ignorePurchased) {

            for (int i = 0; i < list.size(); i++) {
                output = output.concat("Item: " + list.get(i).getItemName()
                        + " | Price: $" + list.get(i).getItemPrice() +
                        " | Count: " + list.get(i).getnumItems() + "\n");
            }

        } else if (!ignorePurchased) {
            output = ("You Purchased..." + "\n");
            for (int i = 0; i < list.size(); i++)
                if (purchased == (list.get(i).getPurchased())) {
                    output = output.concat("Item: " + list.get(i).getItemName()
                            + " | Price: $" + list.get(i).getItemPrice()
                            + " | Purchased: " + list.get(i).getNumItemsPurchased());
                }

        }
        return output;
    }
}




