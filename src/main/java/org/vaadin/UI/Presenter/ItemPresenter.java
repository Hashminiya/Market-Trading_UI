package org.vaadin.UI.presenter;

import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.model.models.StoreModel;
import org.vaadin.UI.view.ItemView;

import java.util.Optional;

public class ItemPresenter {
    private final ItemView view;
    private final StoreModel model;

    public ItemPresenter(ItemView view) {
        this.view = view;
        this.model = new StoreModel();
    }

    public void onViewLoaded(String itemIdStr) {
        try {
            long itemId = Long.parseLong(itemIdStr);
            Optional<StoreDTO> store = model.getStores().stream()
                    .filter(s -> s.getItems().stream().anyMatch(item -> item.getItemId() == itemId))
                    .findFirst();
            if (store.isPresent()) {
                ItemDTO item = store.get().getItems().stream()
                        .filter(i -> i.getItemId() == itemId)
                        .findFirst()
                        .orElse(null);
                if (item != null) {
                    view.displayItemDetails(item, store.get());
                } else {
                    System.err.println("Item not found: " + itemId);
                }
            } else {
                System.err.println("Store not found for item: " + itemId);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid item ID: " + itemIdStr);
    }

  }

}