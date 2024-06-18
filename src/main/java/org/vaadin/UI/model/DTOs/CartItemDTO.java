package org.vaadin.UI.model.DTOs;

import java.io.Serializable;
import java.util.List;

public class CartItemDTO implements Serializable {
    private long itemId;
    private String itemName;
    private int quantity;
    private double price;
    private List<String> categories;
    private String description;

    // No-argument constructor
    public CartItemDTO() {
    }

    public CartItemDTO(long itemId, String itemName, int quantity, double price, List<String> categories, String description) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.categories = categories;
        this.description = description;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

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

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
