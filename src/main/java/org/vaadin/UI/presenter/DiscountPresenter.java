package org.vaadin.UI.presenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.Discounts.ConditionDTO;
import org.vaadin.UI.model.DTOs.Discounts.DiscountDTO;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.models.DiscountModel;
import org.vaadin.UI.model.models.InventoryModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.DiscountView;

import java.util.List;
import java.util.stream.Collectors;

public class DiscountPresenter implements IPresenter {

    private static final Logger logger = LoggerFactory.getLogger(DiscountPresenter.class);
    private final DiscountView view;
    private final DiscountModel discountModel;
    private String currentStoreName;
    private final InventoryModel inventoryModel;
    private List<DiscountDTO> storeDiscounts;

    public DiscountPresenter(DiscountView view) {
        this.view = view;
        this.discountModel = new DiscountModel();
        this.inventoryModel = new InventoryModel();
    }

    @Override
    public void onViewLoaded() {
    }

    @Override
    public void onViewStopped() {
    }

    public void onChoosingStore() {
        List<String> stores = discountModel.getStores(Credentials.getToken());
        if (stores != null) {
            view.fillChooseStoreComboBox(stores);
        } else {
            view.showNotification("Unable to retrieve stores from server");
        }
    }

    public void onSelectStore(String storeName) {
        this.currentStoreName = storeName;
        storeDiscounts = discountModel.getDiscounts(storeName, Credentials.getToken());
        view.fillUpDiscounts(storeDiscounts);
    }

    public void onSavingDiscount() {
    }

    public void onDeletingDiscount() {
    }

    public void onClickingAddNewItemButton() {
        // view.showForm(true, emptyItem);
    }

    public void saveCondition(ConditionDTO conditionDTO) {
    }

    public void saveDiscount(ConditionDTO conditionDTO) {
    }

    public void onSavingHiddenDiscount(String code, String percent, String expirationDate, boolean isStore, List<String> categories, List<String> items, long storeId) {
        // Manually create the discount JSON string with newlines and indentation
        String discountJson = "{\n" +
                "    \"@type\": \"HiddenDiscount\",\n" +
                "    \"id\": 50,\n" +
                "    \"name\": \"Hidden Discount\",\n" +
                "    \"percent\": " + percent + ",\n" +
                "    \"code\": \"" + code + "\",\n" +
                "    \"expirationDate\": \"" + expirationDate + "T23:59:59Z\",\n" +
                "    \"storeId\": " + storeId + ",\n" +
                "    \"items\": [" + items.stream().map(i -> "\"" + i + "\"").collect(Collectors.joining(",")) + "],\n" +
                "    \"isStore\": " + isStore + ",\n" +
                "    \"categories\": [" + categories.stream().map(c -> "\"" + c + "\"").collect(Collectors.joining(",")) + "]\n" +
                "}";

        // Log the discount JSON
        logger.info("Sending discount JSON: " + discountJson);

        // Send the discount JSON to the server
        String response = discountModel.saveHiddenDiscount(Credentials.getToken(), storeId, discountJson);
        view.showNotification(response);
    }

    public List<ItemDTO> getItems() {
        return inventoryModel.getStoreItems(currentStoreName, Credentials.getToken());
    }
}

