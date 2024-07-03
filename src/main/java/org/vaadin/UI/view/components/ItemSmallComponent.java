package org.vaadin.UI.view.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.presenter.ItemPresenter;
import org.vaadin.UI.service.ImageService;
import org.vaadin.UI.view.ItemView;

import java.util.Collections;

public class ItemSmallComponent extends VerticalLayout {
    private final ItemPresenter presenter;

    public ItemSmallComponent(ItemDTO item) {
        this.presenter = new ItemPresenter(new ItemView());
        setWidth("200px");
        setPadding(false);
        setSpacing(false);

        // Retrieve or generate the image URL
        String imageUrl = item.getImageURL();
        if (imageUrl == null || imageUrl.isEmpty()) {
            imageUrl = ImageService.getImageUrl(item.getItemName());
        }

        // Create a RouterLink for the image
        RouterLink imageLink = new RouterLink();
        imageLink.setRoute(ItemView.class);
        imageLink.setQueryParameters(new QueryParameters(Collections.singletonMap("itemId", Collections.singletonList(String.valueOf(item.getItemId())))));

        // Add the item image
        Image itemImage = new Image(imageUrl, "Item Image");
        itemImage.setWidth("150px");
        itemImage.setHeight("150px");
        itemImage.getStyle().set("object-fit", "cover");
        imageLink.add(itemImage);  // Add the image to the RouterLink
        add(imageLink);  // Add the RouterLink to the layout

        // Create a RouterLink for the item name
        RouterLink itemLink = new RouterLink();
        itemLink.setRoute(ItemView.class);
        itemLink.setText(item.getItemName());
        itemLink.setQueryParameters(new QueryParameters(Collections.singletonMap("itemId", Collections.singletonList(String.valueOf(item.getItemId())))));
        itemLink.getStyle().set("cursor", "pointer");
        add(itemLink);

        Paragraph price = new Paragraph("Price: " + item.getTotalPrice());
        Button addToCartButton = new Button(new Icon(VaadinIcon.CART));
        addToCartButton.getElement().setProperty("title", "Add to Cart");
        addToCartButton.addClickListener(event -> presenter.addItemToCart(item, 1));

        HorizontalLayout priceAndButtonLayout = new HorizontalLayout(price, addToCartButton);
        priceAndButtonLayout.setAlignItems(Alignment.CENTER);
        priceAndButtonLayout.setWidthFull();
        add(priceAndButtonLayout);
    }
}
