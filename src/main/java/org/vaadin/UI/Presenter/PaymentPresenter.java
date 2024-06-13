package org.vaadin.UI.Presenter;

import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.PaymentView;

public class PaymentPresenter implements IPresenter{

    private PaymentView paymentView;
    public PaymentPresenter(PaymentView paymentView){
        this.paymentView = paymentView;
    }
    @Override
    public void onViewLoaded() {
    }

    @Override
    public void onViewStopped() {
    }

    public void onPay(){

    }
}
