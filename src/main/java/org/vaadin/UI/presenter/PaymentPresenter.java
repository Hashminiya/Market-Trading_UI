package org.vaadin.UI.presenter;

import org.vaadin.UI.Util.Credentials;
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

    public void onPay(String creditCard, String expiryDate, String cvv){
        paymentView.showNotification( model.checkoutShoppingCart(Credentials.getToken(),creditCard,expiryDate,cvv));
    }
}
