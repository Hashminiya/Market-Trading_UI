package org.vaadin.UI.presenter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.Discounts.ConditionDTO;
import org.vaadin.UI.model.DTOs.Discounts.DiscountDTO;
import org.vaadin.UI.model.models.DiscountModel;
import org.vaadin.UI.model.models.InventoryModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.DiscountView;

import java.io.IOException;
import java.util.List;

public class DiscountPresenter implements IPresenter {

    private final DiscountView view;
    private DiscountModel discountModel;
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

    public void onSavingDiscount(){

    }

    public void onDeletingDiscount(){

    }

    public void onClickingAddNewItemButton() {
//        view.showForm(true, emptyItem);
    }

    public void saveCondition(ConditionDTO conditionDTO) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);

        mapper.registerModule(new SimpleModule().addDeserializer(ConditionDTO.class, new JsonDeserializer<ConditionDTO>() {
            @Override
            public ConditionDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
                return mapper.readValue(p, ConditionDTO.class);
            }
        }));

        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(conditionDTO);
            System.out.println(json);
            String res = discountModel.savePolicy(Credentials.getToken(), currentStoreName, json);
            view.showNotification(res);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDiscount(ConditionDTO conditionDTO) {
    }

    public void onSavingHiddenDiscount() {
    }
}
