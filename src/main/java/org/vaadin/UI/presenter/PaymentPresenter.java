package org.vaadin.UI.presenter;

import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.models.PaymentModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.PaymentView;

public class PaymentPresenter implements IPresenter {

    private PaymentView paymentView;
    private PaymentModel model;
    private double totalPrice;

    public PaymentPresenter(PaymentView paymentView) {
        this.paymentView = paymentView;
        model = new PaymentModel();
        totalPrice = 0;
    }

    @Override
    public void onViewLoaded() {
    }

    @Override
    public void onViewStopped() {
    }

    private void updateTotalPrice(double price) {
        totalPrice = price;
    }

    public double getTotalPrice() {
        return model.getCartTotalPrice(Credentials.getToken());
    }

    public void checkout(String cardNumber, String cardholderName, String expiration, String cvv, String discountCode) {
        String result = model.checkoutShoppingCart(Credentials.getToken(), cardNumber, expiration, cvv, discountCode);
        paymentView.showNotification(result);
    }
}
