package org.vaadin.UI.presenter;

import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.PolicyDTO;
import org.vaadin.UI.model.models.InventoryModel;
import org.vaadin.UI.model.models.PolicyModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.PolicyView;

import java.util.ArrayList;
import java.util.List;

public class PolicyPresenter implements IPresenter {

    private final PolicyView view;
    private PolicyModel policyModel;

    private List<PolicyDTO> storePolicies;
    public PolicyPresenter(PolicyView view) {
        this.view = view;
        this.policyModel = new PolicyModel();
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }

    public void onChoosingStore() {
        List<String> stores = policyModel.getStores(Credentials.getToken());
        if (stores != null) {
            view.fillChooseStoreComboBox(stores);
        } else {
            view.showNotification("Unable to retrieve stores from server");
        }
    }

    public void onSelectStore(String storeName) {
        storePolicies = policyModel.getPolicies(storeName, Credentials.getToken());
        view.fillUpPolicies(storePolicies);
    }

    public void onSavingPolicy(){

    }

    public void onDeletingPolicy(){

    }

    public void onClickingAddNewItemButton() {
//        view.showForm(true, emptyItem);
    }
}
