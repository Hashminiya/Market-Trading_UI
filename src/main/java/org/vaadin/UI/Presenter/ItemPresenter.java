package org.vaadin.UI.presenter;

import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.model.models.StoreModel;
import org.vaadin.UI.view.ItemView;

import java.util.Optional;
import java.util.logging.Logger;

public class ItemPresenter {
    private final ItemView view;
    private final StoreModel model;
    private static final Logger logger = Logger.getLogger(ItemPresenter.class.getName());

    public ItemPresenter(ItemView view) {
        this.view = view;
        this.model = new StoreModel();
    }

    public void onViewLoaded(String itemId) {
        Optional<ItemDTO> itemOpt = model.getStores().stream()
                .flatMap(store -> store.getItems().stream())
                .filter(i -> String.valueOf(i.getItemId()).equals(itemId))
                .findFirst();

        if (itemOpt.isPresent()) {
            ItemDTO item = itemOpt.get();
            logger.info("Found item: " + item.getItemName() + " with storeId: " + item.getStoreId());
            Optional<StoreDTO> storeOpt = model.getStoreById(String.valueOf(item.getStoreId()));
            if (storeOpt.isPresent()) {
                StoreDTO store = storeOpt.get();
                view.displayItemDetails(item, store);
            } else {
                logger.severe("Store not found for item: " + itemId);
            }
        } else {
            logger.severe("Item not found: " + itemId);
        }
    }
}
