package org.vaadin.UI.view.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.UI.model.DTOs.BasketItemDTO;
import org.vaadin.UI.model.DTOs.ShoppingBasketDTO;

public class ShoppingBasketComponent extends VerticalLayout {

    private Grid<BasketItemDTO> basketGrid;
    private ShoppingBasketDTO shoppingBasket;

    public ShoppingBasketComponent(ShoppingBasketDTO shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
        setPadding(true);
        setSpacing(true);
        setWidthFull();

        basketGrid = new Grid<>(BasketItemDTO.class, false);
        basketGrid.addColumn(BasketItemDTO::getItemName).setHeader("Item Name");
        basketGrid.addColumn(BasketItemDTO::getQuantity).setHeader("Quantity");
        basketGrid.addColumn(BasketItemDTO::getCategories).setHeader("Categories");
        basketGrid.addColumn(BasketItemDTO::getDescription).setHeader("Description");
        basketGrid.addColumn(BasketItemDTO::getTotalPrice).setHeader("Item Price");
        basketGrid.addColumn(BasketItemDTO::getPriceAfterDiscount).setHeader("Item Price After Discount");
        basketGrid.addColumn(basketItem -> basketItem.getQuantity() * basketItem.getPriceAfterDiscount()).setHeader("Total Price");
        basketGrid.setItems(shoppingBasket.getItems());

        add(basketGrid);
        addBasketTotalPrice();
    }

    private void addBasketTotalPrice() {
        double totalPrice = shoppingBasket.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getPriceAfterDiscount())
                .sum();
        Div totalPriceDiv = new Div(new Text("Basket Total Price: " + totalPrice));
        totalPriceDiv.addClassName("basket-total-price");
        add(totalPriceDiv);
    }
}