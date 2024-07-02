package org.vaadin.UI.view.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.UI.model.DTOs.ShoppingBasketDTO;

import java.util.List;

public class ShoppingCartComponent extends VerticalLayout {

    public ShoppingCartComponent() {
        setPadding(true);
        setSpacing(true);
        setSizeFull();
    }

    public void setShoppingBaskets(List<ShoppingBasketDTO> baskets) {
        removeAll();
        if (baskets != null) {
            baskets.forEach(basket -> add(new ShoppingBasketComponent(basket)));
        }
    }
}
