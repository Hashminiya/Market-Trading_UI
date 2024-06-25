package org.vaadin.UI.presenter;

import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.model.models.CartModel;
import org.vaadin.UI.model.models.StoreModel;
import org.vaadin.UI.view.ItemView;
import org.vaadin.UI.view.StoreView;

import java.util.Optional;
import java.util.logging.Logger;

public class StorePresenter {
    private final StoreView view;
    private final StoreModel model;
    private final ItemPresenter itemPresenter;
    private static final Logger logger = Logger.getLogger(StorePresenter.class.getName());
    private StoreDTO storeDTO;
    private Long storeId;

    public StorePresenter(StoreView view) {
        this.view = view;
        this.model = new StoreModel();
        this.itemPresenter = new ItemPresenter(new ItemView());

 ;
    }

    public void onViewLoaded(String storeName) {
        Optional<StoreDTO> storeOpt = model.getStores().stream()
                .filter(store -> store.getName().equals(storeName))
                .findFirst();

        if (storeOpt.isPresent()) {
            StoreDTO store = storeOpt.get();
            view.displayStoreDetails(store);
        } else {
            logger.severe("Store not found: " + storeName);
        }
    }

    public void addItemToCart(ItemDTO item, int amount) {
        // Add the item to the cart in the model
        itemPresenter.addItemToCart(item, amount);
        logger.info("Item added to cart: " + item.getItemName());
    }
}
