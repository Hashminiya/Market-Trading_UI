package org.vaadin.UI.model.DTOs;

import java.util.List;
import java.util.Map;

public class ShoppingBasketDTO {
    private long basketId;
    private Map<Long, Integer> items;
    private Map<Long, Double> itemsPrice;
    private List<ItemDTO> itemsDetails;
    private long storeId;
    private double basketTotalPrice;

    public ShoppingBasketDTO() {
    }

    public ShoppingBasketDTO(long basketId, Map<Long, Integer> items, Map<Long, Double> itemsPrice, List<ItemDTO> itemsDetails, long storeId, double basketTotalPrice) {
        this.basketId = basketId;
        this.items = items;
        this.itemsPrice = itemsPrice;
        this.itemsDetails = itemsDetails;
        this.storeId = storeId;
        this.basketTotalPrice = basketTotalPrice;
    }

    // Getters and Setters
    public long getBasketId() {
        return basketId;
    }

    public void setBasketId(long basketId) {
        this.basketId = basketId;
    }

    public Map<Long, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Long, Integer> items) {
        this.items = items;
    }

    public Map<Long, Double> getItemsPrice() {
        return itemsPrice;
    }

    public void setItemsPrice(Map<Long, Double> itemsPrice) {
        this.itemsPrice = itemsPrice;
    }

    public List<ItemDTO> getItemsDetails() {
        return itemsDetails;
    }

    public void setItemsDetails(List<ItemDTO> itemsDetails) {
        this.itemsDetails = itemsDetails;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public double getBasketTotalPrice() {
        return basketTotalPrice;
    }

    public void setBasketTotalPrice(double basketTotalPrice) {
        this.basketTotalPrice = basketTotalPrice;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Long, Integer> entry : items.entrySet()) {
            totalPrice += itemsPrice.get(entry.getKey()) * entry.getValue();
        }
        return totalPrice;
    }
}
