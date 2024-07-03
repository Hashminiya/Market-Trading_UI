package org.vaadin.UI.model.DTOs.Policies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("MaximumQuantityPurchasePolicy")
public class MaximumQuantityPolicyDTO extends PolicyDTO{
    @JsonProperty("maxAmount")
    private int maxAmount;
    @JsonProperty("isStore")
    private boolean allStorePolicy;
    public int getMaxAmount() {
        return maxAmount;
    }
    public void setAllStorePolicy(boolean allStorePolicy) {
        this.allStorePolicy = allStorePolicy;
    }
    public boolean isAllStorePolicy() {
        return allStorePolicy;
    }

    public void setMaxAmount(int max) {
        this.maxAmount = max;
    }
}
