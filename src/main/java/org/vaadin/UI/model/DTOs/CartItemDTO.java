package org.vaadin.UI.model.DTOs;

import java.util.List;

import java.io.Serializable;

public class CartItemDTO implements Serializable {
    private long itemId;
    private String itemName;
    private int quantity;
    private double price;
    private List<String> categories;



    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories (List<String> categories){
        this.categories = categories;
    }
}
