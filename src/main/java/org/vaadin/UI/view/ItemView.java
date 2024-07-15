package org.vaadin.UI.view;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.presenter.ItemPresenter;
import org.vaadin.UI.view.components.ItemComponent;
import org.vaadin.UI.view.components.ItemSmallComponent;

import java.util.List;
import java.util.stream.Collectors;

@Route("item")
public class ItemView extends ViewTemplate implements BeforeEnterObserver {
    private final ItemPresenter presenter;
    private final VerticalLayout itemDetails;
    private final VerticalLayout relatedItemsLayout;
    private final VerticalLayout storeLinkLayout;
    private ItemDTO item;
    private ItemComponent itemComponent;

    public ItemView() {
        this.item = new ItemDTO();
        this.presenter = new ItemPresenter(this);
        this.itemDetails = new VerticalLayout();
        this.relatedItemsLayout = new VerticalLayout();
        this.storeLinkLayout = new VerticalLayout();

        add(itemDetails);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // Clear itemDetails and re-add the necessary components
        itemDetails.removeAll();
        relatedItemsLayout.removeAll();
        storeLinkLayout.removeAll();

        String itemId = event.getLocation().getQueryParameters().getParameters().getOrDefault("itemId", List.of("")).get(0);
        presenter.onViewLoaded(itemId);
    }

    public void displayItemDetails(ItemDTO item, StoreDTO store) {
        this.item = item;
        itemComponent = new ItemComponent(item);
        itemComponent.getAddToCartButton().addClickListener(event -> {
            presenter.addItemToCart(item, itemComponent.getQuantityField().getValue().intValue());
        });

        // Link to the store
        storeLinkLayout.add(new Anchor("store?storeName=" + store.getName(), "Back to Store: " + store.getName()));

        // Related items from the same store
        relatedItemsLayout.add(new Paragraph("Related Items"));
        HorizontalLayout relatedItemsLayoutInner = new HorizontalLayout();
        relatedItemsLayoutInner.setSpacing(true);
        List<ItemDTO> relatedItems = store.getItems().stream()
                .filter(i -> i.getItemId() != item.getItemId())
                .collect(Collectors.toList());
        for (ItemDTO relatedItem : relatedItems) {
            relatedItemsLayoutInner.add(new ItemSmallComponent(relatedItem));
        }
        relatedItemsLayout.add(relatedItemsLayoutInner);

        // Re-add components to itemDetails
        itemDetails.addComponentAsFirst(itemComponent);
        itemDetails.add(storeLinkLayout);
        itemDetails.add(relatedItemsLayout);
    }
}
