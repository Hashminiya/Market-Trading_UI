package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import org.vaadin.UI.presenter.CartPresenter;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Shopping Cart")
@Route("cart")
public class CartView extends ViewTemplate {

    private CartPresenter presenter;

    public CartView() {
        presenter = new CartPresenter(this);
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        Div title = new Div();
        title.setText("Shopping Cart");
        title.getStyle().set("font-size", "24px");

        //cartGrid = new Grid<>(CartItemDTO.class);


        Button checkoutButton = new Button("Checkout");
        checkoutButton.addClickListener(event -> {
            presenter.checkout("1234567812345678", "12/24", "123", "DISCOUNT10");
        });



        // Load the cart items when the view is initialized
        presenter.onViewLoaded();
    }

    public void displayShoppingCart(String s) {

       add(s);
    }

//    private List<CartItemDTO> parseItems(String s) {
//        List<CartItemDTO> items = new ArrayList<>();
//        if (s != null && !s.isEmpty()) {
//            String[] itemStrings = s.split("\\], \\[");
//            for (String itemString : itemStrings) {
//                itemString = itemString.replace("[", "").replace("]", "");
//                String[] parts = itemString.split(", ");
//                Long id = Long.valueOf(parts[0].split(": ")[1]);
//                int quantity = Integer.valueOf(parts[1].split(": ")[1]);
//                items.add(new CartItemDTO(id, null, quantity, 0.0));
//            }
//        }
//        return items;
//    }

    public void showNotification(String message) {
        Notification.show(message);
    }

    public void navigateToPaymentView() {
        getUI().ifPresent(ui -> ui.navigate("payment"));
    }
}
