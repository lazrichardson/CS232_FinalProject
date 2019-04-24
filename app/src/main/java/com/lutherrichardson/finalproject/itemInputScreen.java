package com.lutherrichardson.finalproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.ArrayList;

public class itemInputScreen extends AppCompatActivity {

    private TextView itemsList;
    private Button addItemButton;
    private Button goShoppingButton;
    private double userBudget;
    private String userName;
    private EditText itemNameInput;
    private EditText countItemsInput;
    private EditText itemPriceInput;
    private EditText priorityInput;
    private int itemCounter = 0;
    ArrayList<Item> itemArray = new ArrayList<Item>(0);
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_input_screen);

        // force portrait
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        // set the view
        itemsList = findViewById(R.id.itemListView);



        // assign the views from the layout file to the corresponding vars
        addItemButton = findViewById(R.id.addItemButton);
        goShoppingButton = findViewById(R.id.goShoppingButton);

        itemNameInput = findViewById(R.id.itemNameInput);
        countItemsInput = findViewById(R.id.countInput);
        itemPriceInput = findViewById(R.id.priceInput);
        priorityInput = findViewById(R.id.priorityInput);



        // pull in the variables from the last activity
        Intent intent = getIntent();

       // intent.getStringArrayExtra("userName");
         user.setBankAccount(intent.getDoubleExtra("userBudget", 0));


        View.OnClickListener addItemListener = new View.OnClickListener() {
            @Override
            public void onClick(View v){

                String output = "";

                // on click, update the text view
                String itemName = itemNameInput.getText().toString();
                String itemCount = countItemsInput.getText().toString();
                String itemPrice = itemPriceInput.getText().toString();
                String itemPriority = priorityInput.getText().toString();

                // initialize the objects
                    itemArray.add(itemCounter, new Item());

                    // shortcut reference variable
                    Item item = itemArray.get(itemCounter);

                    // assign the value to the item object
                    item.setItemName(itemName);
                    item.setNumItems(Integer.parseInt(itemCount));
                    item.setItemPrice(Double.parseDouble(itemPrice));
                    item.setItemPriority(Integer.parseInt(itemPriority));

                    for(int i =0; i < itemArray.size(); i++){
                        output = output.concat("Item: " + itemArray.get(i).getItemName()
                                + " | Price: $" + itemArray.get(i).getItemPrice() +
                                " | Count: " + itemArray.get(i).getnumItems() + "\n");

                        itemNameInput.setText("");
                        countItemsInput.setText("");
                        itemPriceInput.setText("");
                        priorityInput.setText("");
                    }

                itemsList.setText(output);
            }
        };
        addItemButton.setOnClickListener(addItemListener);

        View.OnClickListener goShoppinglistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.goShopping(itemArray); // go shopping

                // print the receipt
                String output = ("Your Shopping List Before Shopping:" + "\n");

                for(int i =0; i < itemArray.size(); i++){
                        output = output.concat("Item: " + itemArray.get(i).getItemName()
                                + " | Price: $" + itemArray.get(i).getItemPrice() +
                                " | Count: " + itemArray.get(i).getNumItemsPurchased() + "\n");
                    }
                 output = output.concat("\nYou Purchased:" + "\n");

                for(int i =0; i < itemArray.size(); i++){
                    if(itemArray.get(i).getPurchased()) {
                        output = output.concat("Item: " + itemArray.get(i).getItemName()
                                + " | Price: $" + itemArray.get(i).getItemPrice() +
                                " | Count: " + itemArray.get(i).getNumItemsPurchased() + "\n");
                    }
                }
                output = output.concat("\n"+"You didn't purchase:\n");

                for(int i =0; i < itemArray.size(); i++){
                    if(!itemArray.get(i).getPurchased()) {
                        output = output.concat("Item: " + itemArray.get(i).getItemName()
                                + " | Price: $" + itemArray.get(i).getItemPrice() +
                                " | Count: " + itemArray.get(i).getNumItemsPurchased() + "\n");
                    }
                }
                itemsList.setText(output);
            }
        };
        goShoppingButton.setOnClickListener(goShoppinglistener);







    }
}






