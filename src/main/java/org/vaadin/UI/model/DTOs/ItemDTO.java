package org.vaadin.UI.model.DTOs;

import java.io.Serializable;
import java.util.List;

public class ItemDTO implements Serializable {
    private final long storeId;
    private final double totalPrice;
    private final int quantity;
    private final String itemName;
    private final long itemId;
    private final List<String> categories;
    private final String description;
    public ItemDTO() {
        this.itemId = 0;
        this.itemName = "";
        this.quantity = 0;
        this.storeId = 0;
        this.totalPrice = 0;
        this.categories = List.of();
        this.description = "";
    }
    public ItemDTO(long itemId, String itemName, int quantity, long storeId, double totalPrice, List<String> categories, String description) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.storeId = storeId;
        this.totalPrice = totalPrice;
        this.categories = categories;
        this.description = description;
    }

    public long getStoreId() {
        return storeId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getDescription() {
        return description;
    }
}
