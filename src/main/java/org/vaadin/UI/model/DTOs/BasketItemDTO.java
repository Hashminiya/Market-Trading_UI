package org.vaadin.UI.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

public class BasketItemDTO extends ItemDTO implements Serializable {

    @JsonProperty("priceAfterDiscount")
    private double priceAfterDiscount;

    public BasketItemDTO() {
        super();
    }

    public BasketItemDTO(long itemId, String itemName, int quantity, long storeId, double totalPrice, List<String> categories, String description, double priceAfterDiscount) {
        super(itemId, itemName, quantity, storeId, totalPrice, categories, description);
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }
}
