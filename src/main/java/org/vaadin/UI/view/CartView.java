package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.ShoppingCartDTO;
import org.vaadin.UI.presenter.CartPresenter;
import org.vaadin.UI.view.components.ShoppingCartComponent;

@PageTitle("Shopping Cart")
@Route("cart")
public class CartView extends ViewTemplate {

    private CartPresenter presenter;
    private ShoppingCartComponent shoppingCartComponent;
    private Button checkoutButton;

    public CartView() {
        presenter = new CartPresenter(this);
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        Div title = new Div();
        title.setText("Shopping Cart");
        title.getStyle().set("font-size", "24px");

        shoppingCartComponent = new ShoppingCartComponent();

        checkoutButton = new Button("Checkout");
        checkoutButton.addClickListener(event -> {
            presenter.checkout("1234567812345678", "12/24", "123", "DISCOUNT10");
        });

        add(title, shoppingCartComponent);

        // Load the cart items when the view is initialized
        presenter.onViewLoaded();
    }

    public void displayShoppingCart(ShoppingCartDTO shoppingCart) {
        if (shoppingCart != null && shoppingCart.getBaskets() != null) {
            shoppingCartComponent.setShoppingBaskets(shoppingCart.getBaskets());
            add(checkoutButton);
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
