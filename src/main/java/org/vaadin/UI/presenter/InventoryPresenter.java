package org.vaadin.UI.presenter;

import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.models.InventoryModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.InventorySettingView;

import java.util.ArrayList;
import java.util.List;

public class InventoryPresenter implements IPresenter {

    private final InventorySettingView view;
    private InventoryModel inventoryModel;
    private ArrayList<ItemDTO> storeItems;

    private String currenStore;
    private long currentStoreId;

    public InventoryPresenter(InventorySettingView view) {
        this.view = view;
        this.inventoryModel = new InventoryModel();
    }

    @Override
    public void onViewLoaded() {
        // Implement any initialization logic here
    }

    @Override
    public void onViewStopped() {
        // Implement any cleanup logic here
    }

    public void onSelectStore(String storeName) {
        storeItems = inventoryModel.getStoreItems(storeName, Credentials.getToken());
        view.fillUpInventory(storeItems);
        currenStore = storeName;
        this.currentStoreId = inventoryModel.getStoreIdByName(storeName, Credentials.getToken());
    }

    public void onSavingItem(ItemDTO item) {
        String response = inventoryModel.saveItem(Credentials.getToken(), this.currentStoreId, item.getItemName(), item.getDescription(), item.getTotalPrice(), item.getQuantity(), item.getCategories());
        if (response != null) {
            view.showNotification(response);
            // Refresh the inventory grid
            onSelectStore(currenStore);
        } else {
            view.showNotification("Operation failed");
        }
    }

    public void onUpdatingItem(long itemId, long storeId, String newName, double newPrice, int newAmount) {
        String response = inventoryModel.updateItem(itemId, storeId, newName, newPrice, newAmount, Credentials.getToken());
        if (response != null) {
            view.showNotification(response);
            // Refresh the inventory grid
            onSelectStore(currenStore);
        } else {
            view.showNotification("Operation failed");
        }
    }

    public void onCancleItem() {
        view.closeItemDialog();
    }

    public void onDeleteItem(ItemDTO item) {
        String response = inventoryModel.deleteItem(item, Credentials.getToken());
        if (response != null) {
            view.showNotification(response);
            onSelectStore(currenStore);
        } else {
            view.showNotification("Item deletion failed");
        }
    }

    public void onChoosingStore() {
        List<String> stores = inventoryModel.getStores(Credentials.getToken());
        if (stores != null) {
            view.fillChooseStoreComboBox(stores);
        } else {
            view.showNotification("Unable to retrieve stores from server");
        }
    }
}
