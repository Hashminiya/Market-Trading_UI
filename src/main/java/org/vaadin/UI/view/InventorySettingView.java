package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Presenter.InventoryPresenter;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.view.components.ItemForm;

import java.util.ArrayList;
import java.util.List;

@Route("settings/inventory")
public class InventorySettingView extends MainSettingView {
    private ComboBox<String> chooseStoreComboBox;
    private Grid<ItemDTO> inventoryGrid;
    private Button addNewItemButton;
    private InventoryPresenter presenter;
    private VerticalLayout drawer;
    private ItemForm form;

    public InventorySettingView() {
        presenter = new InventoryPresenter(this);

        VerticalLayout rightContent = getRightContent();
        rightContent.removeAll();

        HorizontalLayout topLayout = new HorizontalLayout();
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

        form = new ItemForm(presenter);
        form.setVisible(false);

        drawer = createDrawer();
        rightContent.add(drawer);

        chooseStoreComboBox.addValueChangeListener(event -> {
            String selectedOptionStoreName = event.getValue();
            presenter.onSelectStore(selectedOptionStoreName);
        });

        inventoryGrid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                showForm(true, event.getValue());
            } else {
                showForm(false, null);
            }
        });

//        addNewItemButton.addClickListener(event -> {
//            showForm(true, new ItemDTO());
//        });
    }

    public void fillChooseStoreComboBox(ArrayList<String> storeList) {
        chooseStoreComboBox.setItems(storeList);
    }

    public void fillUpInventory(ArrayList<ItemDTO> storeItemsList) {
        inventoryGrid.setItems(storeItemsList);
    }

    private Grid<ItemDTO> createInventoryGrid() {
        Grid<ItemDTO> grid = new Grid<>(ItemDTO.class);
        grid.setColumns("itemId", "itemName", "quantity", "storeId", "totalPrice");
        grid.getColumnByKey("itemId").setHeader("Item ID");
        grid.getColumnByKey("itemName").setHeader("Item Name");
        grid.getColumnByKey("quantity").setHeader("Quantity Available");
        grid.getColumnByKey("storeId").setHeader("Store ID");
        grid.getColumnByKey("totalPrice").setHeader("Price");
        return grid;
    }

    private VerticalLayout createDrawer() {
        VerticalLayout drawerLayout = new VerticalLayout();
        drawerLayout.setWidth("300px");
        drawerLayout.setHeightFull();
        drawerLayout.getStyle().set("position", "fixed");
        drawerLayout.getStyle().set("right", "0");
        drawerLayout.getStyle().set("top", "0");
        drawerLayout.getStyle().set("background", "white");
        drawerLayout.setVisible(false);
        drawerLayout.add(form);
        return drawerLayout;
    }

    public void showForm(boolean show, ItemDTO itemDTO) {
        form.setVisible(show);
        form.setEnabled(show);
        drawer.setVisible(show);

        if (show && itemDTO != null) {
            form.setItem(itemDTO);
        }
    }
    public void showNotification(String message) {
        Notification.show(message);
    }
}
