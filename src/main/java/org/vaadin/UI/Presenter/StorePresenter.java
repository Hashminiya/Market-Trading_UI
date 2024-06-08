package org.vaadin.UI.Presenter;


import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.model.models.StoreModel;
import org.vaadin.UI.view.StoreView;

import java.util.List;
import org.vaadin.UI.presenter.Interfaces.IPresenter;

public class StorePresenter implements IPresenter {
    private StoreView view;
    private StoreModel storeModel;

    public StorePresenter() {
        this.storeModel = new StoreModel();
    }

    public void setView(StoreView view) {
        this.view = view;
    }

    public void loadStores() {
        // Fetch data from the model
        List<StoreDTO> stores = storeModel.getStores();
        // Update the view
        view.setStores(stores);
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}
