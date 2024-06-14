package org.vaadin.UI.Presenter;

import org.vaadin.UI.model.models.PaymentModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.PaymentView;


public class PaymentPresenter implements IPresenter{

    private PaymentView paymentView;
    private PaymentModel model;
    public PaymentPresenter(PaymentView paymentView){
        this.paymentView = paymentView;
        model = new PaymentModel();
    }
    @Override
    public void onViewLoaded() {
    }

    @Override
    public void onViewStopped() {
    }

    public void onPay(String token, String creditCard, String expiryDate, String cvv){
        paymentView.showNotification( model.checkoutShoppingCart(token,creditCard,expiryDate,cvv));
    }
}
