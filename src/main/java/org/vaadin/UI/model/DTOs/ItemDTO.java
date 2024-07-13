package org.vaadin.UI.model.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.vaadin.UI.service.ImageService;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)  // This will ignore any unknown properties
public class ItemDTO implements Serializable {

    @JsonProperty("itemId")
    private long itemId;

    @JsonProperty("itemName")
    private String itemName;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("storeId")
    private long storeId;

    @JsonProperty("totalPrice")
    private double totalPrice;

    @JsonProperty("categories")
    private List<String> categories;

    @JsonProperty("description")
    private String description;

    private String imageURL = ImageService.getImageUrl("item");


    public ItemDTO() {
    }

    public ItemDTO(long itemId, String itemName, int quantity, long storeId, double totalPrice, List<String> categories, String description) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.storeId = storeId;
        this.totalPrice = totalPrice;
        this.categories = categories;
        this.description = description;
        this.imageURL = ImageService.getImageUrl(itemName);
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imgURL) {
        this.imageURL = imgURL;
    }
}
