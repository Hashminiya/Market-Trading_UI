package org.vaadin.UI.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingBasketDTO implements Serializable {

    @JsonProperty("items")
    private List<BasketItemDTO> items;

    @JsonProperty("price")
    private double price;

    @JsonProperty("storeName")
    private String storeName;

    @JsonProperty("storeId")
    private long storeId;

    public ShoppingBasketDTO() {
    }

    public ShoppingBasketDTO(List<BasketItemDTO> items, double price, String storeName, long storeId) {
        this.items = items;
        this.price = price;
        this.storeName = storeName;
        this.storeId = storeId;
    }

    public List<BasketItemDTO> getItems() {
        return items;
    }

    public void setItems(List<BasketItemDTO> items) {
        this.items = items;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }


    private void test() {
        ShoppingBasketDTO shoppingBasketDTO = new ShoppingBasketDTO();
        shoppingBasketDTO.setPrice(10.0);
        shoppingBasketDTO.setStoreId(1);
        shoppingBasketDTO.setStoreName("store");
        shoppingBasketDTO.setItems(new ArrayList<>());
        shoppingBasketDTO.getItems().add(new BasketItemDTO());
    }

    public String toString() {
        return "ShoppingBasketDTO{" + "items=" + items + ", price=" + price + ", storeName=";
    }
}
