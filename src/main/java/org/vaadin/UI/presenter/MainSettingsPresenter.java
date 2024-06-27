package org.vaadin.UI.presenter;

import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.MainSettingView;

public class MainSettingsPresenter implements IPresenter {
    private MainSettingView mainSettingView;

    public MainSettingsPresenter(MainSettingView mainSettingView){
        this.mainSettingView = mainSettingView;

    }
    @Override
    public void onViewLoaded() {
        mainSettingView.setUp();
    }

    @Override
    public void onViewStopped() {

    }

    public void onInventoryButtonClick(){

    }

    public void onStoreManagementButtonClick() {
    }

    public void onMarketHistoryButtonClick() {
    }

    public void onPurchaseHistoryButton() {
    }

    public void onPolicyButtonClick() {
    }

    public void onDiscountButtonClick() {
    }
}
