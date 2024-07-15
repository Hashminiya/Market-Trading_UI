package org.vaadin.UI.model.DTOs.Discounts;

import java.util.List;

public class ConditionCompositeDTO extends ConditionDTO{
    private List<ConditionDTO> conditions;
    private String logicalRole;
    private String name;
    public String getLogicalRole() {
        return logicalRole;
    }

    public void setLogicalRole(String logicalRole) {
        this.logicalRole = logicalRole;
    }

    public void setConditions(List<ConditionDTO> conditions) {
        this.conditions = conditions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ConditionDTO> getConditions() {
        return conditions;
    }
}
