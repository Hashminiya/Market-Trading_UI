package org.vaadin.UI.presenter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.PolicyViewDTO;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.Policies.AgeRestrictedPolicyDTO;
import org.vaadin.UI.model.DTOs.Policies.ComplexPolicyDto;
import org.vaadin.UI.model.DTOs.Policies.MaximumQuantityPolicyDTO;
import org.vaadin.UI.model.DTOs.Policies.PolicyDTO;
import org.vaadin.UI.model.models.InventoryModel;
import org.vaadin.UI.model.models.PolicyModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.PolicyView;

import java.util.List;

public class PolicyPresenter implements IPresenter {

    private final PolicyView view;
    private PolicyModel policyModel;
    private String currentStoreName;
    private final InventoryModel inventoryModel;

    private List<PolicyViewDTO> storePolicies;
    public PolicyPresenter(PolicyView view) {
        this.view = view;
        this.policyModel = new PolicyModel();
        this.inventoryModel = new InventoryModel();
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
        this.currentStoreName = storeName;
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

    public void savePolicy(PolicyDTO policyDTO) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(policyDTO);
            System.out.println(json);
            policyModel.savePolicy(Credentials.getToken(), currentStoreName, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ItemDTO> getItems(){
        return inventoryModel.getStoreItems(currentStoreName,Credentials.getToken());

    }

    public List<String> getCategories() {
        return inventoryModel.getStoreCategories(currentStoreName,Credentials.getToken());
    }

}
