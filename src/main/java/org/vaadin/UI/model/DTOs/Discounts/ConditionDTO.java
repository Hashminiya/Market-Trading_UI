package org.vaadin.UI.model.DTOs.Discounts;

import java.util.List;

public class ConditionDTO {
    private Long itemId;
    private int count;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setAllStoreCondition(boolean isAllStoreDiscount) {

    }

    public void setItems(List<Long> list) {
    }

    public void setCategories(List<String> categoriesChosen) {
    }
}
