package org.vaadin.UI.model.DTOs.Discounts;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.vaadin.UI.model.DTOs.Policies.AgeRestrictedPolicyDTO;
import org.vaadin.UI.model.DTOs.Policies.ComplexPolicyDto;
import org.vaadin.UI.model.DTOs.Policies.MaximumQuantityPolicyDTO;

import java.util.Date;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MaximumQuantityPolicyDTO.class, name = "MaximumQuantityPurchasePolicy"),
        @JsonSubTypes.Type(value = ComplexPolicyDto.class, name = "PurchasePolicyComposite"),
        @JsonSubTypes.Type(value = AgeRestrictedPolicyDTO.class, name = "AgeRestrictedPurchasePolicy")
})

public class DiscountDTO extends BaseDiscountDTO{

    private double percent;
    private Date expirationDate;
    private List<Long> items;
    private List<String> categories;
    private boolean isStore;

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public Date getExpirationDate(){return expirationDate;}

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
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

}
