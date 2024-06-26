package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.ShoppingCartDTO;
import org.vaadin.UI.presenter.CartPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Shopping Cart")
@Route("cart")
public class CartView extends ViewTemplate {

    private CartPresenter presenter;
    private Grid<ItemDTO> cartGrid;

    public CartView() {
        presenter = new CartPresenter(this);
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        Div title = new Div();
        title.setText("Shopping Cart");
        title.getStyle().set("font-size", "24px");

        cartGrid = new Grid<>(ItemDTO.class);
        cartGrid.addColumn(ItemDTO::getItemId).setHeader("ID");
        cartGrid.addColumn(ItemDTO::getItemName).setHeader("Name");
        cartGrid.addColumn(ItemDTO::getItemQuantity).setHeader("Quantity");
        cartGrid.addColumn(ItemDTO::getItemPrice).setHeader("Price");
        cartGrid.addColumn(item -> String.join(", ", item.getItemCategories() != null ? item.getItemCategories() : Collections.emptyList())).setHeader("Categories");
        cartGrid.addColumn(ItemDTO::getItemDescription).setHeader("Description");
        cartGrid.addColumn(cartItem -> cartItem.getItemQuantity() * cartItem.getItemPrice()).setHeader("Total");

        Button checkoutButton = new Button("Checkout");
        checkoutButton.addClickListener(event -> {
            presenter.checkout("1234567812345678", "12/24", "123", "DISCOUNT10");
        });

        add(title, cartGrid, checkoutButton);

        // Load the cart items when the view is initialized
        presenter.onViewLoaded();
    }

    public void displayShoppingCart(ShoppingCartDTO shoppingCart) {
        if (shoppingCart != null && shoppingCart.getBaskets() != null) {
            List<ItemDTO> items = new ArrayList<>();
            shoppingCart.getBaskets().forEach(basket -> {
                if (basket.getItemsDetails() != null) {
                    items.addAll(basket.getItemsDetails());
                }
            });
            cartGrid.setItems(items);
        } else {
            showNotification("No items found in the cart.");
        }
    }

    public void showNotification(String message) {
        Notification.show(message);
    }

    public void navigateToPaymentView() {
        getUI().ifPresent(ui -> ui.navigate("payment"));
    }
}
