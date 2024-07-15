package org.vaadin.UI.presenter;

import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.models.StoreOwnerSettingModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.StoreOwnerSettingView;

import java.util.List;
import java.util.Set;

public class StoreOwnerSettingPresenter implements IPresenter {
    private final StoreOwnerSettingView view;
    private final StoreOwnerSettingModel model;

    public StoreOwnerSettingPresenter(StoreOwnerSettingView view) {
        this.view = view;
        this.model = new StoreOwnerSettingModel();
    }

    public void onCreateStore(String storeName, String storeDescription) {
        String result = model.showStoreOwnerSetting(Credentials.getToken(), storeName, storeDescription);
        view.showNotification(result);
    }

    public List<String> getUserStoreOwnerships() {
        return model.viewUserStoresOwnership(Credentials.getToken());
    }

    public Set<String> getStoreManagementInfo(String storeName) {
        return model.viewManagementInfo(Credentials.getToken(), storeName);
    }

    @Override
    public void onViewLoaded() {
        // Implement if needed
    }

    @Override
    public void onViewStopped() {
        // Implement if needed
    }
}