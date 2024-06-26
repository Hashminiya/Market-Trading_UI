package org.vaadin.UI.model.DTOs;

import java.util.List;

public class ShoppingCartDTO {
    private List<ShoppingBasketDTO> baskets;
    private double totalPrice;

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(List<ShoppingBasketDTO> baskets, double totalPrice) {
        this.baskets = baskets;
        this.totalPrice = totalPrice;
    }

    public List<ShoppingBasketDTO> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<ShoppingBasketDTO> baskets) {
        this.baskets = baskets;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
