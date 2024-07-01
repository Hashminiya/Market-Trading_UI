package org.vaadin.UI.model.DTOs.Policies;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
@JsonTypeName("PurchasePolicyComposite")
public class ComplexPolicyDto extends PolicyDTO {
    private String logicalRole;
    private List<PolicyDTO> policies;

    public String getLogicalRole() {
        return logicalRole;
    }

    public void setLogicalRole(String logicalRole) {
        this.logicalRole = logicalRole;
    }

    public void setPolicies(List<PolicyDTO> policies) {
        this.policies = policies;
    }

    public List<PolicyDTO> getPolicies() {
        return policies;
    }
}
