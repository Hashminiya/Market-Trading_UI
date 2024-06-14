package org.vaadin.UI.presenter;

import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.model.models.StoreModel;
import org.vaadin.UI.view.StoreView;

import java.util.List;
import java.util.Optional;

public class StorePresenter {
    private final StoreView view;
    private final StoreModel model;

    public StorePresenter(StoreView view) {
        this.view = view;
        this.model = new StoreModel();
    }

    public void onViewLoaded(String storeName) {
        Optional<StoreDTO> store = model.getStores().stream()
                .filter(s -> s.getName().equals(storeName))
                .findFirst();
        if (store.isPresent()) {
            List<ItemDTO> items = store.get().getItems();
            view.displayItems(items);
        } else {
            view.displayItems(List.of()); // or some error handling
        }
    }
}
