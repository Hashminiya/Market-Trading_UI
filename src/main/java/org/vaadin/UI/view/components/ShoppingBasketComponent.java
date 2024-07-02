package org.vaadin.UI.view.components;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.UI.model.DTOs.BasketItemDTO;
import org.vaadin.UI.model.DTOs.ShoppingBasketDTO;

public class ShoppingBasketComponent extends VerticalLayout {

    private Grid<BasketItemDTO> basketGrid;

    public ShoppingBasketComponent(ShoppingBasketDTO shoppingBasket) {
        setPadding(true);
        setSpacing(true);
        setSizeFull();

        basketGrid = new Grid<>(BasketItemDTO.class);
        basketGrid.setColumns("itemName", "quantity", "totalPrice", "categories", "description");
        basketGrid.addColumn(basketItem -> basketItem.getQuantity() * basketItem.getPriceAfterDiscount()).setHeader("Total After Discount");
        basketGrid.setItems(shoppingBasket.getItems());

        add(basketGrid);
    }
}
