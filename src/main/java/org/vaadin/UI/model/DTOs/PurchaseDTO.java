package org.vaadin.UI.model.DTOs;
public class PurchaseDTO {
    private Long id;
    private String productName;
    private Integer quantity;
    private Double price;
    private String buyer;

    public PurchaseDTO(Long id, String productName, Integer quantity, Double price, String buyer) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.buyer = buyer;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
}