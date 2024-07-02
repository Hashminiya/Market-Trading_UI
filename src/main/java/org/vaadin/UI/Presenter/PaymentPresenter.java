package org.vaadin.UI.Presenter;

import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.models.PaymentModel;
import org.vaadin.UI.Presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.PaymentView;


public class PaymentPresenter implements IPresenter{

    private PaymentView paymentView;
    private PaymentModel model;
    private double totalPrice;
    public PaymentPresenter(PaymentView paymentView){
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

    public void onPay(String creditCard, String expiryDate, String cvv){
        paymentView.showNotification( model.checkoutShoppingCart(Credentials.getToken()));
    }

    private void updateTotalPrice(double price){
        totalPrice = price;

    }

    public double getTotalPrice() {
        return model.getCartTotalPrice(Credentials.getToken());
    }
// presenter.checkout(cardNumber.getValue(), cardholderName.getValue(), expiration.getValue(), cvv.getValue());
//        });
    public void checkout(String cardNumber, String cardholderName, String expiration, String cvv) {
        model.checkoutShoppingCart(Credentials.getToken());

    }
}
