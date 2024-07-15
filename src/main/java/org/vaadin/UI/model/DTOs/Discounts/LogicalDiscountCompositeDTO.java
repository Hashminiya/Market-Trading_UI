package org.vaadin.UI.model.DTOs.Discounts;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName("LogicalDiscountComposite")
public class LogicalDiscountCompositeDTO extends DiscountCompositeDTO{
    private int decision;
    private String logicalRole;
    private String name;
    private List<DiscountDTO> discounts;
    public void setLogicalRole(String logicalRole) {
        this.logicalRole = logicalRole;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDiscounts(List<DiscountDTO> discountDTOList) {
        this.discounts = discountDTOList;
    }
}
