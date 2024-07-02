package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.BasketItemDTO;
import org.vaadin.UI.model.DTOs.ShoppingCartDTO;
import org.vaadin.UI.presenter.CartPresenter;

@PageTitle("Shopping Cart")
@Route("cart")
public class CartView extends ViewTemplate {

    private CartPresenter presenter;
    private Grid<BasketItemDTO> cartGrid;

    public CartView() {
        presenter = new CartPresenter(this);
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        Div title = new Div();
        title.setText("Shopping Cart");
        title.getStyle().set("font-size", "24px");

        cartGrid = new Grid<>(BasketItemDTO.class);
        cartGrid.setColumns("itemName", "quantity", "totalPrice", "categories", "description");
        cartGrid.addColumn(cartItem -> cartItem.getQuantity() * cartItem.getPriceAfterDiscount()).setHeader("Total After Discount");

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
            shoppingCart.getBaskets().forEach(basket -> cartGrid.setItems(basket.getItems()));
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
