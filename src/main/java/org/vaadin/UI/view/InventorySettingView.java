package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Presenter.InventoryPresenter;
import org.vaadin.UI.model.DTOs.ItemDTO;

import java.util.ArrayList;
import java.util.List;

@Route("settings/inventory")
public class InventorySettingView extends MainSettingView {
    private ComboBox chooseStoreComboBox;
    private Grid<ItemDTO> inventoryGrid;
    private List<String> storesList = new ArrayList<>();
    private Button addNewItemButton;
    private InventoryPresenter presenter;
    private VerticalLayout restOfPage;
    public InventorySettingView() {
        presenter = new InventoryPresenter(this);
        restOfPage = new VerticalLayout();
        inventoryGrid = createInventoryGrid();
//        inventoryGrid.setSizeFull();
        chooseStoreComboBox = createChooseStoreComboBox();
        chooseStoreComboBox.addValueChangeListener(event -> {
            String selectedOptionStoreName = (String) event.getValue();
            presenter.onSelectStore(selectedOptionStoreName);
        });
        addNewItemButton = new Button("Add New Item",new Icon(VaadinIcon.PLUS));
        restOfPage.add(chooseStoreComboBox);
        restOfPage.add(addNewItemButton);
        add(restOfPage);
    }

    private ComboBox createChooseStoreComboBox() {

        List<String> storesList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            storesList.add("example store " + i);
        }
        chooseStoreComboBox = new ComboBox<>("Select your store",storesList);
        chooseStoreComboBox.setPlaceholder("No store selected yet");
        return chooseStoreComboBox;
    }

    public void fillUpInventory(ArrayList<ItemDTO> storeItemsList){
        inventoryGrid.setItems(storeItemsList);
        add(inventoryGrid);
    }

    private Grid<ItemDTO> createInventoryGrid() {
        Grid<ItemDTO> grid = new Grid<>(ItemDTO.class);
        grid.setColumns("itemId", "itemName", "quantity", "storeId", "totalPrice");
        grid.getColumnByKey("itemId").setHeader("Item ID");
        grid.getColumnByKey("itemName").setHeader("Item Name");
        grid.getColumnByKey("quantity").setHeader("Quantity Available");
        grid.getColumnByKey("storeId").setHeader("StoreID");
        grid.getColumnByKey("totalPrice").setHeader("Price");
        return grid;
    }
}
