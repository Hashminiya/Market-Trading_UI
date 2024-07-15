package org.vaadin.UI.view.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.presenter.ItemPresenter;
import org.vaadin.UI.service.ImageService;
import org.vaadin.UI.view.ItemView;

import java.util.Collections;
import java.util.List;

public class ItemListComponent extends VerticalLayout {
    private ItemPresenter itemPresenter;
    public ItemListComponent(List<ItemDTO> items) {
        this.itemPresenter = new ItemPresenter(new ItemView());
        Grid<ItemDTO> itemsGrid = new Grid<>(ItemDTO.class, false);
        itemsGrid.setItems(items);

        itemsGrid.addComponentColumn(item -> {
            String imageUrl = ImageService.getImageUrl(item.getItemName());
            Image itemImage = new Image(imageUrl, "Item Image");
            itemImage.setWidth("100px");
            itemImage.setHeight("100px");
            return itemImage;
        }).setHeader("Image");

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
        itemsGrid.addColumn(item -> item.getCategories() != null ? String.join(", ", item.getCategories()) : "No Categories").setHeader("Category");

        itemsGrid.addComponentColumn(item -> {
            Button addToCartButton = new Button("Add to Cart");
            addToCartButton.addClickListener(click -> {
                itemPresenter.addItemToCart(item, 1);

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
