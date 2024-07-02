package org.vaadin.UI.view.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.presenter.InventoryPresenter;

public class ItemDialog extends Dialog {

    private final InventoryPresenter presenter;
    private final Binder<ItemDTO> binder;
    private ItemDTO currentItem;

    private TextField itemName;
    private TextField description;
    private TextField categories;
    private TextField price;
    private TextField quantity;
    private boolean isUpdate;

    public ItemDialog(InventoryPresenter presenter) {
        this.presenter = presenter;
        this.binder = new Binder<>(ItemDTO.class);
        this.isUpdate = true;

        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        setWidth("400px");
        setHeight("500px");

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();
        mainLayout.setPadding(false);
        mainLayout.setSpacing(false);

        VerticalLayout dialogLayout = createDialogLayout();
        VerticalLayout buttonLayout = createButtonLayout();

        mainLayout.add(dialogLayout, buttonLayout);
        mainLayout.setFlexGrow(1, dialogLayout);
        mainLayout.setFlexGrow(0, buttonLayout);

        add(mainLayout);
    }

    public void setIsUpdate(boolean isUpdate){
        this.isUpdate = isUpdate;
    }

    private VerticalLayout createButtonLayout() {
        VerticalLayout buttonLayout = new VerticalLayout();
        buttonLayout.setPadding(true);
        buttonLayout.setSpacing(true);
        buttonLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        Button saveButton = new Button("Save", e -> save());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        saveButton.setWidthFull();
        saveButton.setVisible(!isUpdate);

        Button updateButton = new Button("Update", e -> update());
        updateButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        updateButton.setWidthFull();
        updateButton.setVisible(isUpdate);

        Button cancelButton = new Button("Cancel", e -> cancel());
        cancelButton.setWidthFull();

        Button deleteButton = new Button("Delete", e -> delete());
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteButton.setWidthFull();

        buttonLayout.add(saveButton,updateButton, cancelButton, deleteButton);

        return buttonLayout;
    }

    private VerticalLayout createDialogLayout() {
        VerticalLayout dialogLayout = new VerticalLayout();
        dialogLayout.setPadding(true);
        dialogLayout.setSpacing(true);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);

        itemName = new TextField("Product name");
        itemName.setWidthFull();
        itemName.setRequired(true);

        price = new TextField("Price");
        price.setWidthFull();

        quantity = new TextField("In stock");
        quantity.setWidthFull();

        description = new TextField("Description");
        description.setWidthFull();

//        categories = new TextField("Categories");
//        categories.setWidthFull();

        dialogLayout.add(itemName, price, quantity, description);

        binder.forField(itemName)
                .asRequired("Product name is required")
                .bind(ItemDTO::getItemName, ItemDTO::setItemName);

        binder.forField(price)
                .withConverter(new StringToDoubleConverter("Invalid price"))
                .bind(ItemDTO::getItemPrice, ItemDTO::setItemPrice);

        binder.forField(quantity)
                .withConverter(new StringToIntegerConverter("Invalid quantity"))
                .bind(ItemDTO::getItemQuantity, ItemDTO::setItemQuantity);

        binder.forField(description)
                .bind(ItemDTO::getItemDescription, ItemDTO::setItemDescription);

//        binder.forField(categories)
//                .withConverter(
//                        new StringToListConverter(),
//                        "Invalid format. Please separate categories with commas.")
//                .bind(ItemDTO::getItemCategories, ItemDTO::setItemCategories);

        return dialogLayout;
    }

    private void save() {
        if (currentItem != null && binder.writeBeanIfValid(currentItem) && !isUpdate) {
            presenter.onSavingItem(currentItem);
            close();
        }
    }

    private void delete() {
        if (currentItem != null && currentItem.getId() != 0) {
            presenter.onDeleteItem(currentItem);
            close();
        }
    }

    private void update(){
        if (currentItem != null && currentItem.getId() != 0 && isUpdate) {
            presenter.onUpdatingItem(currentItem);
            close();
        }
    }

    public void setItem(ItemDTO item) {
        this.currentItem = item;
        binder.readBean(item);
    }
    private void cancel() {
        presenter.onCancleItem();
        close();
    }
}