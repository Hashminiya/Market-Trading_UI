package org.vaadin.UI.presenter;

import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.model.models.CartModel;
import org.vaadin.UI.model.models.StoreModel;
import org.vaadin.UI.view.ItemView;
import com.vaadin.flow.component.notification.Notification;

import java.util.Optional;

public class ItemPresenter {
    private final ItemView view;
    private final StoreModel storeModel;
    private final CartModel cartModel;

    public ItemPresenter(ItemView view) {
        this.view = view;
        this.storeModel = new StoreModel();
        this.cartModel = new CartModel();
    }

    public void onViewLoaded(String itemIdStr) {
        try {
            long itemId = Long.parseLong(itemIdStr);
            Optional<StoreDTO> store = storeModel.getStores().stream()
                    .filter(s -> s.getItems().stream().anyMatch(item -> item.getId() == itemId))
                    .findFirst();
            if (store.isPresent()) {
                ItemDTO item = store.get().getItems().stream()
                        .filter(i -> i.getId() == itemId)
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

    public void addItemToCart(ItemDTO item, int amount) {
        String token = Credentials.getToken();
        if (token != null && !token.isEmpty()) {
            String result = cartModel.addItemToCart(token,item.getStoreId(), item.getId(), amount); // Assuming quantity of 1 for simplicity
            Notification.show(result);
            // Refresh the cart view to show the newly added item
            view.getUI().ifPresent(ui -> ui.navigate("cart"));
        } else {
            Notification.show("You need to log in to add items to the cart.");
        }
    }
}
