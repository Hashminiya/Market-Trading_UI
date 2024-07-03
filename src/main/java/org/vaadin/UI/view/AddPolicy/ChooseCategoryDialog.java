package org.vaadin.UI.view.AddPolicy;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;
import org.vaadin.UI.presenter.PolicyPresenter;

public class ChooseCategoryDialog extends Dialog {
    private final List<String> categoriesChosen;
    private final PolicyPresenter presenter;
    private final Grid<String> categoriesGrid;
    private AddPolicyDialog parent;

    public ChooseCategoryDialog(List<String> categoriesChosen, PolicyPresenter presenter,AddPolicyDialog parent) {
        this.categoriesChosen = categoriesChosen;
        this.presenter = presenter;
        this.parent = parent;
        setWidth("800px");
        setHeight("500px");

        VerticalLayout layout = new VerticalLayout();
        categoriesGrid = new Grid<>(String.class, false); // Disable auto-columns

        // Configure the column
        categoriesGrid.addColumn(category -> category).setHeader("Category");

        // Enable multi-selection mode
        categoriesGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        categoriesGrid.setItems(presenter.getCategories());

        Button addButton = new Button("Add Selected Categories", event -> addSelectedCategories());

        layout.add(categoriesGrid, addButton);
        add(layout);
    }

    private void addSelectedCategories() {
        categoriesGrid.getSelectedItems().forEach(categoriesChosen::add);
        parent.ItemsOrCategories();
        close();
    }
}

