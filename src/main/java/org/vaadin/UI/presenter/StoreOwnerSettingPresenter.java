package org.vaadin.UI.presenter;

import org.vaadin.UI.model.models.StoreOwnerSettingModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.StoreOwnerSettingView;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class StoreOwnerSettingPresenter implements IPresenter {
    private final StoreOwnerSettingView view;
    private final StoreOwnerSettingModel model;
    private String token;

    public StoreOwnerSettingPresenter(StoreOwnerSettingView view) {
        this.view = view;
        this.model = new StoreOwnerSettingModel();
    }

    public void onCreateStore(String storeName, String storeDescription) {
        String result = model.showStoreOwnerSetting(token, storeName, storeDescription);
        view.showNotification(result);
    }

    public List<Long> getUserStoreOwnerships(String token) {
        this.token = token; // assuming token is provided/set here
        return model.viewUserStoresOwnership(token);
    }

    public Set<String> getStoreManagementInfo(String token, long storeId) {
        return model.viewManagementInfo(token, storeId);
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
