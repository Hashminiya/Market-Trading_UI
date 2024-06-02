package org.vaadin.UI.presenter;

import org.vaadin.UI.model.StoreService;
import org.vaadin.UI.model.StoreService.Store;
import org.vaadin.UI.view.StoreView;

import java.util.List;

public class StorePresenter {
    private StoreView view;
    private StoreService storeService;

    public StorePresenter() {
        this.storeService = new StoreService();
    }

    public void setView(StoreView view) {
        this.view = view;
    }

    public void loadStores() {
        // Fetch data from the model
        List<Store> stores = storeService.getStores();
        // Update the view
        view.setStores(stores);
    }
}
