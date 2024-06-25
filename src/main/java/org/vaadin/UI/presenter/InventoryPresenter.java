
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
    private int storeId;
    private ItemDTO emptyItem;

    public InventoryPresenter(InventorySettingView view) {
        this.view = view;
        this.inventoryModel = new InventoryModel();
        this.emptyItem = new ItemDTO(0, "", 0, 0, 0, new ArrayList<>(), "");
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
        storeItems = inventoryModel.getStoreItems(storeName,Credentials.getToken());
        //storeItems = getDemoItems();
        // storeItems = inventoryModel.getStoreItems(storeName);
        // storeItems = inventoryModel.getStoreItems(storeName);
        storeItems = getDemoItems();
        view.fillUpInventory(storeItems);
    }

    public void onSavingItem(ItemDTO item) {
        String response = inventoryModel.saveItem(item, Credentials.getToken());
        if (response != null) {
            view.showNotification(response);
        } else {
            view.showNotification("Item saved successfully");
        }
    }

    public void onEditingItem(ItemDTO item) {
        String response = inventoryModel.updateItem(item, Credentials.getToken());
        if (response != null) {
            view.showNotification(response);
        } else {
            view.showNotification("Item updated successfully");
        }
    }

    public void onCancleItem() {
        view.showForm(false, emptyItem);
    }

    public void onDeleteItem(ItemDTO item) {
        String response = inventoryModel.deleteItem(item, Credentials.getToken());
        if (response != null) {
            view.showNotification(response);
        } else {
            view.showNotification("Item deleted successfully");
        }
    }

    private ArrayList<ItemDTO> getDemoItems() {
        ArrayList<ItemDTO> items = new ArrayList<>();
        items.add(new ItemDTO(1, "Apple", 100, 12, 0.99, List.of("Fruit", "Food"), "A juicy apple"));
        items.add(new ItemDTO(2, "Banana", 150, 23, 0.59, List.of("Fruit", "Food"), "A ripe banana"));
        items.add(new ItemDTO(3, "Orange", 80, 12, 0.79, List.of("Fruit", "Food"), "A sweet orange"));
        items.add(new ItemDTO(4, "Strawberry", 60, 23, 2.99, List.of("Fruit", "Food"), "Fresh strawberries"));
        items.add(new ItemDTO(5, "Grapes", 200, 12, 2.49, List.of("Fruit", "Food"), "Delicious grapes"));
        return items;
    }

    public void onChoosingStore() {
        List<String> stores = inventoryModel.getStores(Credentials.getToken());
        if (stores != null) {
            view.fillChooseStoreComboBox(stores);
        } else {
            view.showNotification("Unable to retrieve stores from server");
        }
    }

    public void onClickingAddNewItemButton() {
        view.showForm(true, emptyItem);
    }
}
