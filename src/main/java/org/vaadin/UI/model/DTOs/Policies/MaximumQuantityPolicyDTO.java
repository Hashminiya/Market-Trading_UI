package org.vaadin.UI.model.DTOs.Policies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("MaximumQuantityPurchasePolicy")
public class MaximumQuantityPolicyDTO extends PolicyDTO{
    @JsonProperty("maxAmount")
    private int maxAmount;
    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int max) {
        this.maxAmount = max;
    }
}
