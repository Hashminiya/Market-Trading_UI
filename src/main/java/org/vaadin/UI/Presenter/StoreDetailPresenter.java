package org.vaadin.UI.Presenter;


import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.model.models.StoreModel;
import org.vaadin.UI.view.StoreDetailView;

public class StoreDetailPresenter implements IPresenter{
    private StoreDetailView view;
    private StoreModel storeModel;

    public StoreDetailPresenter(StoreDetailView view, StoreModel storeModel) {
        this.view = view;
        this.storeModel = storeModel;
    }

    public void loadStore(String storeId) {
        storeModel.getStoreById(storeId).ifPresent(view::setStore);
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}
