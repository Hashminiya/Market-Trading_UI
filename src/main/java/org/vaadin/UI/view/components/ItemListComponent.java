package org.vaadin.UI.view.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.view.ItemView;

import java.util.Collections;
import java.util.List;

public class ItemListComponent extends VerticalLayout {

    public ItemListComponent(List<ItemDTO> items) {
        Grid<ItemDTO> itemsGrid = new Grid<>(ItemDTO.class, false);
        itemsGrid.setItems(items);

        itemsGrid.addComponentColumn(item -> {
            RouterLink itemLink = new RouterLink();
            itemLink.setRoute(ItemView.class);
            itemLink.setText(item.getItemName());
            itemLink.setQueryParameters(new QueryParameters(Collections.singletonMap("itemId", Collections.singletonList(String.valueOf(item.getId())))));
            itemLink.getStyle().set("cursor", "pointer");
            return itemLink;
        }).setHeader("Name");

        itemsGrid.addColumn(ItemDTO::getItemPrice).setHeader("Price");
        itemsGrid.addColumn(ItemDTO::getItemDescription).setHeader("Description");
        itemsGrid.addColumn(item -> item.getItemCategories() != null ? String.join(", ", item.getItemCategories()) : "No Categories").setHeader("Category");

        itemsGrid.addComponentColumn(item -> {
            Button addToCartButton = new Button("Add to Cart");
            addToCartButton.addClickListener(click -> {
                Notification.show(item.getItemName() + " added to cart.");
            });
            return addToCartButton;
        }).setHeader("Actions");

        add(itemsGrid);
    }

    public ItemListComponent() {
        //text with no items found
        add("No items found.");
    }
}
