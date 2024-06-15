package org.vaadin.UI.view.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.presenter.ItemPresenter;
import org.vaadin.UI.view.ItemView;

import java.util.Collections;

public class ItemComponent extends VerticalLayout {
    private ItemPresenter presenter;

    public ItemComponent(ItemDTO item) {
        this.presenter = new ItemPresenter(new ItemView()); // Assuming ItemView is the view associated with this presenter
        setWidth("200px"); // Set a fixed width for consistent alignment

        RouterLink itemLink = new RouterLink();
        itemLink.setRoute(ItemView.class);
        itemLink.setText(item.getItemName());
        itemLink.setQueryParameters(new QueryParameters(Collections.singletonMap("itemId", Collections.singletonList(String.valueOf(item.getItemId())))));
        itemLink.getStyle().set("cursor", "pointer");
        add(itemLink);

        Paragraph price = new Paragraph("Price: " + item.getTotalPrice());
        Button addToCartButton = new Button(new Icon(VaadinIcon.CART));
        addToCartButton.getElement().setProperty("title", "Add to Cart");
        addToCartButton.addClickListener(event -> {
            presenter.addItemToCart(item);
        });

        HorizontalLayout priceAndButtonLayout = new HorizontalLayout(price, addToCartButton);
        priceAndButtonLayout.setAlignItems(Alignment.CENTER);
        priceAndButtonLayout.setWidthFull();
        add(priceAndButtonLayout);
    }
}
