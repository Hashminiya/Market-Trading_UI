package org.vaadin.UI.model.DTOs;

import java.io.Serializable;

public class ItemDTO implements Serializable {
    private final long id;
    private final String name;
    private final double price;
    private final int quantity;


    public ItemDTO(long id, String name, int quantity, double price){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
