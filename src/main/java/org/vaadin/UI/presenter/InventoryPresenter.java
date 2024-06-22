
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
        inventoryModel = new InventoryModel();
        this.emptyItem = new ItemDTO(0, "", 0, 0, 0);
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }

    public void onSelectStore(String storeName) {
        storeItems = inventoryModel.getStoreItems(storeName,Credentials.getToken());
        //storeItems = getDemoItems();
        view.fillUpInventory(storeItems);
    }

    public void onSavingItem(ItemDTO item) {
        String response = inventoryModel.saveItem(item, Credentials.getToken());
        if (response != null) {
            view.showNotification(response);
        } else {
            view.showNotification(response);
        }
    }

    public void onEditingItem(ItemDTO item) {
        String response = inventoryModel.updateItem(item, Credentials.getToken());
        if (response != null) {
            view.showNotification(response);
        } else {
            view.showNotification(response);
        }
    }

    public void onCancleItem() {
        view.showForm(false,emptyItem);
    }

    public void onDeleteItem(ItemDTO item) {
        String response = inventoryModel.deleteItem(item, Credentials.getToken());
        if (response != null) {
            view.showNotification(response);
        } else {
            view.showNotification(response);
        }
    }
    private ArrayList<ItemDTO> getDemoItems() {
        ArrayList<ItemDTO> items = new ArrayList<>();
        items.add(new ItemDTO(1, "Apple", 100, 12,0.99));
        items.add(new ItemDTO(2, "Banana", 150,23, 0.59));
        items.add(new ItemDTO(3, "Orange", 80, 12,0.79));
        items.add(new ItemDTO(4, "Strawberry", 60, 23,2.99));
        items.add(new ItemDTO(5, "Grapes", 200, 12,2.49));
        return items;
    }

    public void onChoosingStore() {
        List<String> stores = inventoryModel.getStores(Credentials.getToken());
        if (stores != null) {
            view.fillChooseStoreComboBox(stores);
        } else {
            view.showNotification("enable to retrieve stores from server");

        }
    }

    public void onClickingAddNewItemButton() {
        view.showForm(true,emptyItem);
    }
}

