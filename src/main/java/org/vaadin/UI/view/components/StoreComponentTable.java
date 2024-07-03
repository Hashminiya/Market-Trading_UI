package org.vaadin.UI.view.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.presenter.StorePresenter;
import org.vaadin.UI.view.ItemView; // Ensure this is imported
import org.vaadin.UI.view.StoreView;

import java.util.Collections;

public class StoreComponentTable extends VerticalLayout {
    private StorePresenter presenter;

    public StoreComponentTable(StoreDTO store) {
        this.presenter = new StorePresenter(new StoreView());

        RouterLink storeLink = new RouterLink();
        storeLink.setRoute(StoreView.class);
        storeLink.setText(store.getName());
        storeLink.setQueryParameters(new QueryParameters(Collections.singletonMap("storeName", Collections.singletonList(store.getName()))));
        storeLink.getStyle().set("cursor", "pointer");
        add(storeLink);

        Grid<ItemDTO> itemsGrid = new Grid<>(ItemDTO.class, false);
        itemsGrid.setItems(store.getItems());

        itemsGrid.addComponentColumn(item -> {
            RouterLink itemLink = new RouterLink();
            itemLink.setRoute(ItemView.class);
            itemLink.setText(item.getItemName());
            itemLink.setQueryParameters(new QueryParameters(Collections.singletonMap("itemId", Collections.singletonList(String.valueOf(item.getItemId())))));
            itemLink.getStyle().set("cursor", "pointer");
            return itemLink;
        }).setHeader("Name");

        itemsGrid.addColumn(ItemDTO::getTotalPrice).setHeader("Price");
        itemsGrid.addColumn(ItemDTO::getDescription).setHeader("Description");
        itemsGrid.addColumn(item -> String.join(", ", item.getCategories())).setHeader("Category");

        itemsGrid.addComponentColumn(item -> {
            Button addToCartButton = new Button("Add to Cart");
            addToCartButton.addClickListener(click -> {
                presenter.addItemToCart(item,  1);
            });
            return addToCartButton;
        }).setHeader("Actions");

        add(itemsGrid);
    }
}
