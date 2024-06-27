package org.vaadin.UI.model.DTOs;

import java.io.Serializable;
import java.util.List;

public class PurchaseDTO implements Serializable {
    private Long id;
    private List<ItemDTO> purchasedItemsList;
    private double totalAmount;
    private String userId;
    private String purchaseDate;

    // Add an empty constructor if needed
    public PurchaseDTO() {
    }

    public PurchaseDTO(Long id, List<ItemDTO> purchasedItemsList, double totalAmount, String userId, String purchaseDate) {
        this.id = id;
        this.purchasedItemsList = purchasedItemsList;
        this.totalAmount = totalAmount;
        this.userId = userId;
        this.purchaseDate = purchaseDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemDTO> getPurchasedItemsList() {
        return purchasedItemsList;
    }

    public void setPurchasedItemsList(List<ItemDTO> purchasedItemsList) {
        this.purchasedItemsList = purchasedItemsList;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
