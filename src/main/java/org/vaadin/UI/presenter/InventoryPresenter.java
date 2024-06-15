
package org.vaadin.UI.presenter;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.models.InventoryModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.InventorySettingView;

import java.util.ArrayList;

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
        //storeItems = inventoryModel.getStoreItems(storeName);
        storeItems = getDemoItems();
        view.fillUpInventory(storeItems);
    }

    public void onSavingItem(ItemDTO item) {

    }

    public void onEditingItem(ItemDTO item) {

    }

    public void onCancleItem() {

    }

    public void onDeleteItem(ItemDTO item) {

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
        ArrayList<String> stores = inventoryModel.getStores();
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

