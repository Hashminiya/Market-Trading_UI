package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Presenter.InventoryPresenter;
import org.vaadin.UI.model.DTOs.ItemDTO;

import java.util.ArrayList;
import java.util.List;

@Route("settings/inventory")
public class InventorySettingView extends MainSettingView {
    private ComboBox<String> chooseStoreComboBox;
    private Grid<ItemDTO> inventoryGrid;
    private List<String> storesList = new ArrayList<>();
    private ItemForm form;
    private Button addNewItemButton;

    private InventoryPresenter presenter;

    public InventorySettingView() {
        presenter = new InventoryPresenter(this);

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        HorizontalLayout restOfPage = new HorizontalLayout();
        restOfPage.setSizeFull();

        chooseStoreComboBox = createChooseStoreComboBox();
        restOfPage.add(chooseStoreComboBox);

        addNewItemButton = new Button("Add New Item",new Icon(VaadinIcon.PLUS));

        form = new ItemForm(presenter);

        inventoryGrid = createInventoryGrid();
        inventoryGrid.setSizeFull();


        chooseStoreComboBox.addValueChangeListener(event -> {
            String selectedOptionStoreName = event.getValue();

            presenter.onSelectStore(selectedOptionStoreName);
            inventoryGrid.asSingleSelect().addValueChangeListener(
                    event2 -> showForm(true,event2.getValue()));
        });

        restOfPage.add(addNewItemButton);
        restOfPage.add(inventoryGrid);
        restOfPage.setFlexGrow(1, inventoryGrid);

        mainLayout.add(restOfPage);
        mainLayout.setFlexGrow(1, restOfPage);

        add(mainLayout);

    }

    private ComboBox<String> createChooseStoreComboBox() {
        List<String> storesList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            storesList.add("example store " + i);
        }
        chooseStoreComboBox = new ComboBox<>("Select your store", storesList);
        chooseStoreComboBox.setPlaceholder("No store selected yet");
        chooseStoreComboBox.setWidth("300px");
        return chooseStoreComboBox;
    }

    private Grid<ItemDTO> createInventoryGrid() {
        Grid<ItemDTO> grid = new Grid<>(ItemDTO.class);
        grid.setColumns("id", "name", "quantity", "price");
        grid.getColumnByKey("id").setHeader("Item ID");
        grid.getColumnByKey("name").setHeader("Item Name");
        grid.getColumnByKey("quantity").setHeader("Quantity Available");
        grid.getColumnByKey("price").setHeader("Price");
        return grid;
    }

    public void fillUpInventory(ArrayList<ItemDTO> storeItemsList){
        inventoryGrid.setItems(storeItemsList);
    }

    public void showForm(boolean show, ItemDTO itemDTO) {
        form.setVisible(show);
        form.setEnabled(show);
        if (show) {
            form.setItem(itemDTO);
        }
    }

}
