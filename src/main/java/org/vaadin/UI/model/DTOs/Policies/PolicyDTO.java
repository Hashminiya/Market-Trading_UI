package org.vaadin.UI.model.DTOs.Policies;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MaximumQuantityPolicyDTO.class, name = "MaximumQuantityPurchasePolicy"),
        @JsonSubTypes.Type(value = ComplexPolicyDto.class, name = "PurchasePolicyComposite"),
        @JsonSubTypes.Type(value = AgeRestrictedPolicyDTO.class, name = "AgeRestrictedPurchasePolicy")
})
public class PolicyDTO {
    private Long id = 0L;
    private String name;
    private String type;

    private List<Long> items;
    private List<String> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
