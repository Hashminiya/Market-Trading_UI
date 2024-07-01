package org.vaadin.UI.view.AddPolicy;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.UI.Util.SuccessCallBack;
import org.vaadin.UI.model.DTOs.ItemDTO;

import java.util.List;

public class ChooseItemsDialog extends Dialog {
    private List<ItemDTO> itemsChosen;
    private org.vaadin.UI.presenter.PolicyPresenter presenter;
    private Grid<ItemDTO> itemsGrid;
    private AddPolicyDialog parent;


    public ChooseItemsDialog(List<ItemDTO> itemsChosen, org.vaadin.UI.presenter.PolicyPresenter presenter, AddPolicyDialog parent) {
        this.itemsChosen = itemsChosen;
        this.presenter = presenter;
        this.parent = parent;
        setWidth("800px");
        setHeight("500px");

        VerticalLayout layout = new VerticalLayout();
        itemsGrid = new Grid<>(ItemDTO.class, false); // Disable auto-columns

        // Configure the columns
        itemsGrid.addColumn(ItemDTO::getItemName).setHeader("Name");
        itemsGrid.addColumn(ItemDTO::getId).setHeader("ID");
        itemsGrid.addColumn(item -> String.join(", ", item.getItemCategories())).setHeader("Categories");

        // Enable multi-selection mode
        itemsGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        itemsGrid.setItems(presenter.getItems());

        Button addButton = new Button("Add Selected Items", event -> addSelectedItems());

        layout.add(itemsGrid, addButton);
        add(layout);
    }

    private void addSelectedItems() {
        itemsGrid.getSelectedItems().forEach(itemsChosen::add);
        parent.ItemsOrCategories();
        close();
    }
}
