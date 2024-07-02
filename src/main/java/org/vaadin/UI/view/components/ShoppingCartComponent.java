package org.vaadin.UI.view.components;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.UI.model.DTOs.ShoppingBasketDTO;

import java.util.List;

public class ShoppingCartComponent extends VerticalLayout {

    private List<ShoppingBasketDTO> baskets;

    public ShoppingCartComponent() {
        setPadding(true);
        setSpacing(true);
        setSizeFull();
    }

    public void setShoppingBaskets(List<ShoppingBasketDTO> baskets) {
        this.baskets = baskets;
        removeAll();
        if (baskets != null) {
            baskets.forEach(basket -> {
                ShoppingBasketComponent basketComponent = new ShoppingBasketComponent(basket);
                basketComponent.addClassName("shopping-basket-card");
                add(basketComponent);
            });
            addCartTotalPrice();
        }
    }

    private void addCartTotalPrice() {
        double totalCartPrice = baskets.stream()
                .flatMap(basket -> basket.getItems().stream())
                .mapToDouble(item -> item.getQuantity() * item.getPriceAfterDiscount())
                .sum();
        Div totalPriceDiv = new Div(new Text("Total Cart Price: " + totalCartPrice));
        totalPriceDiv.addClassName("cart-total-price");
        add(totalPriceDiv);
    }
}
