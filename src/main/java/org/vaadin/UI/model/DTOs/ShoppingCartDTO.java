package org.vaadin.UI.model.DTOs;

import java.util.List;

public class ShoppingCartDTO {
    private List<CartItemDTO> items;
    private double totalPrice;

    // Getters and Setters
    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
