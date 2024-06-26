package org.vaadin.UI.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

public class ItemDTO implements Serializable {
    @JsonProperty("id")
    private long id;

    @JsonProperty("price")
    private double itemPrice;

    @JsonProperty("quantity")
    private int itemQuantity;

    @JsonProperty("name")
    private String itemName;

    @JsonProperty("categories")
    private List<String> itemCategories;

    @JsonProperty("description")
    private String itemDescription;

    @JsonProperty("storeId")
    private long storeId;

    // Add an empty constructor if you want Jackson to be able to instantiate ItemDTO without parameters
    public ItemDTO() {
    }

    public ItemDTO(long id, String itemName, int quantity, long storeId, double price, List<String> categories, String description) {
        this.id = id;
        this.itemName = itemName;
        this.itemQuantity = quantity;
        this.itemPrice = price;
        this.itemCategories = categories;
        this.itemDescription = description;
        this.storeId = storeId;
    }

    // Getters and Setters
    public long getItemId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<String> getItemCategories() {
        return itemCategories;
    }

    public void setItemCategories(List<String> itemCategories) {
        this.itemCategories = itemCategories;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Long getItemStoreId() {
        return storeId;
    }
}
