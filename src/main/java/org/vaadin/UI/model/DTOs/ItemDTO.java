package org.vaadin.UI.model.DTOs;

import java.io.Serializable;
import java.util.List;

public class ItemDTO implements Serializable {
    private final long itemStoreId;
    private final double itemPrice;
    private final int itemQuantity;
    private final String itemName;
    private final long itemId;
    private final List<String> itemCategories;
    private final String itemDescription;
    public ItemDTO() {
        this.itemId = 0;
        this.itemName = "";
        this.itemQuantity = 0;
        this.itemStoreId = 0;
        this.itemPrice = 0;
        this.itemCategories = List.of();
        this.itemDescription = "";
    }
    public ItemDTO(long itemId, String itemName, int quantity, long storeId, double totalPrice, List<String> categories, String description) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQuantity = quantity;
        this.itemStoreId = storeId;
        this.itemPrice = totalPrice;
        this.itemCategories = categories;
        this.itemDescription = description;
    }

    public long getItemStoreId() {
        return itemStoreId;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public List<String> getItemCategories() {
        return itemCategories;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}
