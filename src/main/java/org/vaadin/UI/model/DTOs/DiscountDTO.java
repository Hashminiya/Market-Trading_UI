package org.vaadin.UI.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

public class DiscountDTO implements Serializable {
    @JsonProperty("id")
    private long id;

    @JsonProperty("percent")
    private double percent;

    @JsonProperty("expirationDate")
    private int expirationDate;

    @JsonProperty("storeId")
    private long storeId;

    @JsonProperty("items")
    private List<ItemDTO> items;

    @JsonProperty("categories")
    private List<String> categories;

    @JsonProperty("isStore")
    private boolean isStore;

    // Add an empty constructor if you want Jackson to be able to instantiate DiscountDTO without parameters
    public DiscountDTO() {
    }

    public DiscountDTO(long id, double percent, int expirationDate, long storeId, List<ItemDTO> items, List<String> categories, boolean isStore) {
        this.id = id;
        this.percent = percent;
        this.expirationDate = expirationDate;
        this.storeId = storeId;
        this.items = items;
        this.categories = categories;
        this.isStore = isStore;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public boolean isStore() {
        return isStore;
    }

    public void setStore(boolean isStore) {
        this.isStore = isStore;
    }
}
