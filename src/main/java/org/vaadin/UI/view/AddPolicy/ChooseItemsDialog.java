package org.vaadin.UI.view.AddPolicy;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.presenter.PolicyPresenter;
import org.vaadin.UI.view.Abstracts.IDialog;

import java.util.List;

public class ChooseItemsDialog extends Dialog {
    private List<ItemDTO> itemsChosen;
    private PolicyPresenter presenter;
    private Grid<ItemDTO> itemsGrid;
    private IDialog parent;


    public ChooseItemsDialog(List<ItemDTO> itemsChosen, PolicyPresenter presenter, IDialog parent) {
        this.itemsChosen = itemsChosen;
        this.presenter = presenter;
        this.parent = parent;
        setWidth("800px");
        setHeight("500px");

        VerticalLayout layout = new VerticalLayout();
        itemsGrid = new Grid<>(ItemDTO.class, false); // Disable auto-columns

        // Configure the columns
        itemsGrid.addColumn(ItemDTO::getItemName).setHeader("Name");
        itemsGrid.addColumn(ItemDTO::getItemId).setHeader("ID");
        itemsGrid.addColumn(item -> String.join(", ", item.getCategories())).setHeader("Categories");

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
