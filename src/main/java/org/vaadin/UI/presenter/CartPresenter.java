package org.vaadin.UI.presenter;

import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.ShoppingCartDTO;
import org.vaadin.UI.model.models.CartModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.CartView;

public class CartPresenter implements IPresenter {

    private final CartView cartView;
    private final CartModel cartModel;

    public CartPresenter(CartView cartView) {
        this.cartView = cartView;
        this.cartModel = new CartModel();
    }


    @Override
    public void onViewLoaded() {
        String token = Credentials.getToken();
        if (token != null && !token.isEmpty()) {
            ShoppingCartDTO shoppingCart = cartModel.getShoppingCart(token);
            cartView.displayShoppingCart(shoppingCart);
        } else {
            cartView.showNotification("No items found in the cart.");
        }
    }


    @Override
    public void onViewStopped() {
    }

    public void addItemToCart(long storeId, long itemId, int quantity) {
        String token = Credentials.getToken();
        String result = cartModel.addItemToCart(token, storeId, itemId, quantity);
        cartView.showNotification(result);
        onViewLoaded(); // Reload cart items
    }

    public void updateCartItem(long basketId, long itemId, int newQuantity) {
        String token = Credentials.getToken();
        String result = cartModel.updateCartItem(token, basketId, itemId, newQuantity);
        cartView.showNotification(result);
        onViewLoaded(); // Reload cart items
    }

    public void checkout(String creditCard, String expiryDate, String cvv, String discountCode) {
        String token = Credentials.getToken();
        String result = cartModel.checkout(token, creditCard, expiryDate, cvv, discountCode);
        cartView.showNotification(result);
        if (result.contains("successful")) {
            cartView.navigateToPaymentView();
        }
    }
}
