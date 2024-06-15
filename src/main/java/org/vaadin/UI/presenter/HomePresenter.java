package org.vaadin.UI.presenter;

import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.model.models.StoreModel;
import org.vaadin.UI.view.HomeView;

import java.util.List;

public class HomePresenter {
    private final HomeView view;
    private final StoreModel model;

    public HomePresenter(HomeView view) {
        this.view = view;
        this.model = new StoreModel();
    }

    public void onViewLoaded() {
        List<StoreDTO> stores = model.getStores();
        view.displayStores(stores);
    }
}
