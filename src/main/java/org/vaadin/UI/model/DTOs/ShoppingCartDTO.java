package org.vaadin.UI.model.DTOs;

import java.io.Serializable;
import java.util.List;

public class ShoppingCartDTO implements Serializable {
    private List<ItemDTO> items;
    private double totalPrice;

    // Getters and Setters
    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
