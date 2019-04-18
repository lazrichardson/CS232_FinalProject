package com.lutherrichardson.finalproject;

abstract class StandardItem implements Information {

    private String itemName;

    public StandardItem(){
        itemName = "no input";
    }

    public StandardItem(String itemName) {
        this.itemName = itemName;
    }

    public void setItemName(String itemNameInput) {
        itemName = itemNameInput;
    }

    public String getItemName()
    {
        return itemName;
    }

    public String getName() {
        return itemName;
    }
}
