package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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
public class ItemView extends ViewTemplate implements BeforeEnterObserver {
    private ItemDTO item;
    private final ItemPresenter presenter;
    private final VerticalLayout itemDetails;
    private final H1 itemName;
    private final Paragraph itemPrice;
    private final Paragraph itemQuantity;
    private final Paragraph itemDescription; // Add description field
    private final Paragraph itemCategories; // Add categories field
    private final Button addToCartButton;
    private final VerticalLayout relatedItemsLayout;
    private final VerticalLayout storeLinkLayout;

    public ItemView() {
        this.item = new ItemDTO();
        this.presenter = new ItemPresenter(this);
        this.itemDetails = new VerticalLayout();
        this.itemName = new H1();
        this.itemPrice = new Paragraph();
        this.itemQuantity = new Paragraph();
        this.itemDescription = new Paragraph(); // Initialize description field
        this.itemCategories = new Paragraph(); // Initialize categories field
        this.addToCartButton = new Button(new Icon(VaadinIcon.CART));
        this.relatedItemsLayout = new VerticalLayout();
        this.storeLinkLayout = new VerticalLayout();

        addToCartButton.getElement().setProperty("title", "Add to Cart");
        addToCartButton.addClickListener(event -> {
            presenter.addItemToCart(item,1);
        });

        itemDetails.add(itemName, itemPrice, itemQuantity, itemDescription, itemCategories, addToCartButton, storeLinkLayout, relatedItemsLayout);
        add(itemDetails);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String itemId = event.getLocation().getQueryParameters().getParameters().getOrDefault("itemId", List.of("")).get(0);
        presenter.onViewLoaded(itemId);
    }

    public void displayItemDetails(ItemDTO item, StoreDTO store) {
        this.item = item;
        itemName.setText(item.getItemName());
        itemPrice.setText("Price: " + item.getItemPrice());
        itemQuantity.setText("Quantity: " + item.getItemQuantity());
        itemDescription.setText("Description: " + item.getItemDescription()); // Set description
        itemCategories.setText("Categories: " + String.join(", ", item.getItemCategories())); // Set categories

        // Link to the store
        storeLinkLayout.removeAll();
        Anchor storeLink = new Anchor("store?storeName=" + store.getName(), "Back to Store: " + store.getName());
        storeLinkLayout.add(storeLink);

        // Related items from the same store
        relatedItemsLayout.removeAll();
        relatedItemsLayout.add(new Paragraph("Related Items"));
        HorizontalLayout relatedItemsLayoutInner = new HorizontalLayout();
        relatedItemsLayoutInner.setSpacing(true);
        List<ItemDTO> relatedItems = store.getItems().stream()
                .filter(i -> i.getId() != item.getId())
                .collect(Collectors.toList());
        for (ItemDTO relatedItem : relatedItems) {
            relatedItemsLayoutInner.add(new ItemComponent(relatedItem));
        }
        relatedItemsLayout.add(relatedItemsLayoutInner);
    }
}
