package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.presenter.ItemPresenter;
import org.vaadin.UI.view.components.ItemComponent;

import java.util.List;
import java.util.stream.Collectors;

@Route("item")
public class ItemView extends VerticalLayout implements BeforeEnterObserver {
    private final ItemPresenter presenter;
    private final VerticalLayout itemDetails;
    private final Paragraph itemName;
    private final Paragraph itemPrice;
    private final Paragraph itemQuantity;
    private final Button addToCartButton;
    private final VerticalLayout relatedItemsLayout;
    private final VerticalLayout storeLinkLayout;

    public ItemView() {
        this.presenter = new ItemPresenter(this);
        this.itemDetails = new VerticalLayout();
        this.itemName = new Paragraph();
        this.itemPrice = new Paragraph();
        this.itemQuantity = new Paragraph();
        this.addToCartButton = new Button(new Icon(VaadinIcon.CART));
        this.relatedItemsLayout = new VerticalLayout();
        this.storeLinkLayout = new VerticalLayout();

        addToCartButton.getElement().setProperty("title", "Add to Cart");
        addToCartButton.addClickListener(event -> {
            Notification.show(itemName.getText() + " added to cart");
        });

        itemDetails.add(itemName, itemPrice, itemQuantity, addToCartButton, storeLinkLayout, relatedItemsLayout);
        add(itemDetails);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String itemId = event.getLocation().getQueryParameters().getParameters().getOrDefault("itemId", List.of("")).get(0);
        presenter.onViewLoaded(itemId);
    }

    public void displayItemDetails(ItemDTO item, StoreDTO store) {
        itemName.setText("Name: " + item.getName());
        itemPrice.setText("Price: " + item.getTotalPrice());
        itemQuantity.setText("Quantity: " + item.getQuantity());

        // Link to the store
        storeLinkLayout.removeAll();
        Anchor storeLink = new Anchor("store?storeName=" + store.getName(), "Back to Store: " + store.getName());
        storeLinkLayout.add(storeLink);

        // Related items from the same store
        relatedItemsLayout.removeAll();
        List<ItemDTO> relatedItems = store.getItems().stream()
                .filter(i -> i.getItemId() != item.getItemId())
                .collect(Collectors.toList());
        for (ItemDTO relatedItem : relatedItems) {
            relatedItemsLayout.add(new ItemComponent(relatedItem));
        }
    }
}
