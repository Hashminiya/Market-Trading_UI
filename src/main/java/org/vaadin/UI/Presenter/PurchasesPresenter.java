
package org.vaadin.UI.presenter;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.PurchaseDTO;
import org.vaadin.UI.model.models.InventoryModel;
import org.vaadin.UI.model.models.PurchasesModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.InventorySettingView;
import org.vaadin.UI.view.PurchasesView;

import java.util.ArrayList;
import java.util.List;

public class PurchasesPresenter implements IPresenter {

    private final PurchasesView view;
    private PurchasesModel model;
    private ArrayList<PurchaseDTO> purchases;
    private String currentStore;

    public PurchasesPresenter(PurchasesView view) {
        this.view = view;
        this.model = new PurchasesModel();
    }

    @Override
    public void onViewLoaded() {
        // Implement any initialization logic here
    }

    @Override
    public void onViewStopped() {
        // Implement any cleanup logic here
    }

    public void onChoosingStore() {
        List<String> stores = model.getStores(Credentials.getToken());
        if (stores != null) {
            view.fillChooseStoreComboBox(stores);
        } else {
            view.showNotification("Unable to retrieve stores from server");
        }
    }

    public ArrayList<PurchaseDTO> getDemoPurchases() {
        ArrayList<PurchaseDTO> purchases = new ArrayList<>();

        purchases.add(new PurchaseDTO(1L, "Laptop", 1, 999.99, "Alice"));
        purchases.add(new PurchaseDTO(2L, "Smartphone", 2, 499.99, "Bob"));
        purchases.add(new PurchaseDTO(3L, "Headphones", 5, 79.99, "Charlie"));
        purchases.add(new PurchaseDTO(4L, "Monitor", 3, 199.99, "Dave"));
        purchases.add(new PurchaseDTO(5L, "Keyboard", 4, 49.99, "Eve"));

        return purchases;
    }

    public void onSelectStore(String storeName) {
        //purchases = model.getStorePurchases(storeName,Credentials.getToken());
        view.fillUpInventory(getDemoPurchases());
        currentStore = storeName;
    }

}
