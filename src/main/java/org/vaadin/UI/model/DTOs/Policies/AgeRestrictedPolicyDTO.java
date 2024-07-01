package org.vaadin.UI.model.DTOs.Policies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("AgeRestrictedPurchasePolicy")
public class AgeRestrictedPolicyDTO extends PolicyDTO {
    @JsonProperty("minAge")
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
