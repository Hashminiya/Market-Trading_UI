package org.vaadin.UI.model.DTOs;

import java.io.Serializable;
import java.util.List;

public class ShoppingCartDTO implements Serializable {
    private List<CartItemDTO> items;
    private double totalPrice;

    // No-argument constructor
    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(List<CartItemDTO> items, double totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

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