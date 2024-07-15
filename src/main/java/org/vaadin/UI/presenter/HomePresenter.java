package org.vaadin.UI.presenter;

import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.model.models.StoreModel;
import org.vaadin.UI.view.HomeView;
import org.vaadin.UI.model.DTOs.ItemDTO;

import java.util.List;

public class HomePresenter {
    private final HomeView view;
    private final StoreModel model;

    public HomePresenter(HomeView view) {
        this.view = view;
        this.model = new StoreModel();
    }

    public void onViewLoaded() {
        List<StoreDTO> allStores = model.getStores();
        view.displayStores(allStores);
    }

    public void onSearchRequested(String query) {
        List<ItemDTO> searchedItems = model.searchItemsByKeyWord(query);
        view.displayItems(searchedItems);
    }

    // If you also want to support category searches, here's an additional method
    public void onSearchRequested(String query, String category) {
        List<ItemDTO> searchedItems = model.searchItemsByKeyWordAndCategory(query, category);
        view.displayItems(searchedItems);
    }
}
