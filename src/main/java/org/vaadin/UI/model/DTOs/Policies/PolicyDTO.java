package org.vaadin.UI.model.DTOs.Policies;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.vaadin.UI.model.DTOs.ItemDTO;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MaximumQuantityPolicyDTO.class, name = "MaximumQuantityPolicyDTO"),
        @JsonSubTypes.Type(value = ComplexPolicyDto.class, name = "ComplexPolicyDto"),
        @JsonSubTypes.Type(value = AgeRestrictedPolicyDTO.class, name = "AgeRestrictedPurchasePolicy")
})
public class PolicyDTO {
    private String name;
    private String type;
    @JsonProperty("isStore")
    private boolean allStorePolicy;
    private List<Long> items;
    private List<String> categories;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setAllStorePolicy(boolean allStorePolicy) {
        this.allStorePolicy = allStorePolicy;
    }
    public boolean isAllStorePolicy() {
        return allStorePolicy;
    }
}
