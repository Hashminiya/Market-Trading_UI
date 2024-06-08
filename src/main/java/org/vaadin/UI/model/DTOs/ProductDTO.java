package org.vaadin.UI.model.DTOs;
import java.io.Serializable;

public class ProductDTO implements Serializable {
    private String picture;
    private String name;
    private String description;
    private double price;
    private String category;

    public ProductDTO(String picture, String name, String description, double price, String category) {
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    // Getters and setters
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

