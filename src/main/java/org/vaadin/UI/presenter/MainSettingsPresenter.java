package org.vaadin.UI.presenter;

import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.MainSettingPage;

public class MainSettingsPresenter implements IPresenter {
    private MainSettingPage mainSettingPage;

    public MainSettingsPresenter(MainSettingPage mainSettingPage){
        this.mainSettingPage = mainSettingPage;

    }
    @Override
    public void onViewLoaded() {
        mainSettingPage.setUp();
    }

    @Override
    public void onViewStoped() {

    }

    @Override
    public void onUserAction() {

    }

    public void onInventoryButtonClick(){

    }

    public void onStoreManagementButtonClick() {
    }

    public void onMarketHistoryButtonClick() {
    }
}
