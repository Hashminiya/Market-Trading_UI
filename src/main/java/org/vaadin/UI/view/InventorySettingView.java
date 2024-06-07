package org.vaadin.UI.view;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("settings/inventory")
public class InventorySettingView extends MainSettingView {
    private ComboBox chooseStoreComboBox;
    private ItemGrid itemGrid;
    private List<String> storesList = new ArrayList<>();
    public InventorySettingView() {
        HorizontalLayout restOfPage = new HorizontalLayout();
        restOfPage.add(createChooseStoreComboBox());

        restOfPage.add(createItemGrid());
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

    private ItemGrid createItemGrid () {
        itemGrid = new ItemGrid();
        return itemGrid;
    }

}
