package org.vaadin.UI.presenter;

import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.DiscountDTO;
import org.vaadin.UI.model.models.DiscountModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.DiscountView;

import java.util.List;

public class DiscountPresenter implements IPresenter {

    private final DiscountView view;
    private DiscountModel discountModel;

    private List<DiscountDTO> storeDiscounts;

    public DiscountPresenter(DiscountView view) {
        this.view = view;
        this.discountModel = new DiscountModel();
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
        storeDiscounts = discountModel.getDiscounts(storeName, Credentials.getToken());
        view.fillUpDiscounts(storeDiscounts);
    }

    public void onSavingDiscount() {

    }

    public void onDeletingDiscount() {

    }

    public void onClickingAddNewItemButton() {
//        view.showForm(true, emptyItem);
    }
}
