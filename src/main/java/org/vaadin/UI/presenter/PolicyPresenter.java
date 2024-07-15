package org.vaadin.UI.presenter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.Policies.PolicyDTO;
import org.vaadin.UI.model.DTOs.PolicyViewDTO;
import org.vaadin.UI.model.models.InventoryModel;
import org.vaadin.UI.model.models.PolicyModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.PolicyView;

import java.io.IOException;
import java.util.List;

public class PolicyPresenter implements IPresenter {

    private final PolicyView view;
    private final InventoryModel inventoryModel;
    private PolicyModel policyModel;
    private String currentStoreName;
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

    public void onSavingPolicy() {

    }

    public void onDeletingPolicy() {

    }

    public void onClickingAddNewItemButton() {
//        view.showForm(true, emptyItem);
    }

    public void savePolicy(PolicyDTO policyDTO) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);

        mapper.registerModule(new SimpleModule().addDeserializer(PolicyDTO.class, new JsonDeserializer<PolicyDTO>() {
            @Override
            public PolicyDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                return mapper.readValue(p, PolicyDTO.class);
            }
        }));

        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(policyDTO);
            System.out.println(json);
            String res = policyModel.savePolicy(Credentials.getToken(), currentStoreName, json);
            view.showNotification(res);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ItemDTO> getItems() {
        return inventoryModel.getStoreItems(currentStoreName, Credentials.getToken());

    }

    public List<String> getCategories() {
        return inventoryModel.getStoreCategories(currentStoreName, Credentials.getToken());
    }

}