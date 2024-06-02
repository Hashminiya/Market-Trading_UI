package org.vaadin.UI.presenter;

import org.vaadin.UI.model.StoreService;
import org.vaadin.UI.model.StoreService.Store;
import org.vaadin.UI.view.StoreDetailView;

public class StoreDetailPresenter {
    private StoreDetailView view;
    private StoreService storeService;

    public StoreDetailPresenter(StoreDetailView view, StoreService storeService) {
        this.view = view;
        this.storeService = storeService;
    }

    public void loadStore(String storeId) {
        storeService.getStoreById(storeId).ifPresent(view::setStore);
    }
}
