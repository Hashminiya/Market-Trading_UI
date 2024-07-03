package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Presenter.InventoryPresenter;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.view.components.ItemDialog;

import java.util.ArrayList;
import java.util.List;

@Route("settings/inventory")
public class InventorySettingView extends MainSettingView {
    private ComboBox<String> chooseStoreComboBox;
    private Grid<ItemDTO> inventoryGrid;
    private Button addNewItemButton;
    private InventoryPresenter presenter;
    private ItemDialog itemDialog;

    public InventorySettingView() {
        presenter = new InventoryPresenter(this);

        VerticalLayout rightContent = getRightContent();
        rightContent.removeAll();

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.add(new H1("Inventory"));
        chooseStoreComboBox = new ComboBox<>("Select your store");
        chooseStoreComboBox.setPlaceholder("No store selected yet");
        presenter.onChoosingStore();
        addNewItemButton = new Button("Add New Item", new Icon(VaadinIcon.PLUS));
        topLayout.add(chooseStoreComboBox);
        topLayout.add(addNewItemButton);
        topLayout.setWidthFull();
        topLayout.setAlignItems(FlexComponent.Alignment.END);
        rightContent.add(topLayout);

        inventoryGrid = createInventoryGrid();
        inventoryGrid.setWidthFull();
        rightContent.add(inventoryGrid);

        itemDialog = new ItemDialog(presenter);

        chooseStoreComboBox.addValueChangeListener(event -> {
            String selectedOptionStoreName = event.getValue();
            presenter.onSelectStore(selectedOptionStoreName);
        });

        inventoryGrid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                openItemDialog(event.getValue());
            }
            itemDialog.setIsUpdate(true);
        });

        addNewItemButton.addClickListener(event -> {
            openItemDialog(new ItemDTO());
            itemDialog.setIsUpdate(false);
        });
    }
    private void openItemDialog(ItemDTO item) {
        itemDialog.setItem(item);
        itemDialog.open();
    }
    public void fillChooseStoreComboBox(List<String> storeList) {
        chooseStoreComboBox.setItems(storeList);
    }

    public void fillUpInventory(ArrayList<ItemDTO> storeItemsList) {
        inventoryGrid.setItems(storeItemsList);
    }

    private Grid<ItemDTO> createInventoryGrid() {
        Grid<ItemDTO> grid = new Grid<>(ItemDTO.class);
        grid.setColumns("itemId", "itemName", "quantity", "totalPrice");
        grid.getColumnByKey("itemId").setHeader("Item ID");
        grid.getColumnByKey("itemName").setHeader("Item Name");
        grid.getColumnByKey("quantity").setHeader("Quantity Available");
        grid.getColumnByKey("totalPrice").setHeader("Price");
        return grid;
    }

    public void closeItemDialog() {
        itemDialog.close();
    }

    public void showNotification(String message) {
        Notification.show(message);
    }
}
