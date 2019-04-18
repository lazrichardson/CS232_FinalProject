package com.lutherrichardson.finalproject;


import java.util.ArrayList;
import java.util.Scanner;

public class AssignmentFive {
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        String input;
        int intInput;
        int shoppingListLength = 0;
        ArrayList<Item> itemArray = new ArrayList<Item>(shoppingListLength);
        User user = new User();

        // request name
        System.out.println("What's your name?");
        user.setNameOfUser(User.removeSpecialCharsSpaces(keyboard.nextLine()));
        System.out.println("Hello " + user.getNameOfUser());

        // request + set the user's budget
        user.printString("Please input your bank balance" + "\n");
        user.setBankAccount(keyboard.nextDouble());

        // display budget
        user.printString("You have $");
        user.printDouble(user.getBankAccount());
        user.printString(" in your bank account \n");

        // set the number of items
        System.out.println("How many items do you want to add to the shopping list?");
        shoppingListLength = keyboard.nextInt();

        // initialize the objects
        for (int i = 0; i < shoppingListLength; i++) {
            // create item objects
            itemArray.add(i, new Item());
        }

        // set prices + set the name + set the number of each to add
        for (int i = 0; i < shoppingListLength; i++) {

            Item item = itemArray.get(i);
            item.setItemNumber(i + 1);

            // set names
            System.out.println("Input an item name (" + (i + 1) + " of " + (shoppingListLength) + "): ");
            item.setItemName(keyboard.next());

            // set prices
            item.setItemPrice(5.25 + (i * 4)); // set prices for each item

            // set num items
            System.out.println("How many?");
            item.setNumItems(keyboard.nextInt());

            // user input for purchase priorities + rejections of too high or low
            do {
                System.out.println("\nInput an purchase priority for: " + item.getItemName() +
                        "\nThis number should be between 1 and " + shoppingListLength);

                intInput = keyboard.nextInt();

                if (intInput > shoppingListLength) {
                    System.out.println("That's too high!\nNumbers 1 to " + shoppingListLength
                            + " only.\nTry again.\n");
                } else if (intInput <= 0) {
                    System.out.println("That's too low!\nNumbers 1 to " + shoppingListLength
                            + " only.\nTry again.\n");
                } else {
                    item.setItemPriority(intInput);
                    break;
                }
            } while (item.getItemPriority() == -1);
        }

        // going shopping
        user.goShopping(itemArray); // go shopping

        // show shopping list
        user.printString("\nHere is the shopping list before shopping....\n");
        Item.writeItems(itemArray, true, true);
        Item.readItems(itemArray, true, true);

        // show purchases
        System.out.println("\nYou purchased....\n");
        // cart.getPurchasedItems(cart);
        Item.writeItems(itemArray, true, false);
        Item.readItems(itemArray, true, false);

        // show not purchased
        System.out.println("\nHere's the shopping list after shopping \na.k.a you didn't have enough money to purchase...\n");
        //  cart.getNotPurchasedItems(cart);
        Item.writeItems(itemArray, false, false);
        Item.readItems(itemArray, false, false);

        // show remainder of bank account
        user.printString("\nHere is your remaining bank balance: $");
        System.out.format("%.2f%n", user.getBankAccount());
    }
}



