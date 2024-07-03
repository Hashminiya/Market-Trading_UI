package org.vaadin.UI.view.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.service.ImageService;

public class ItemComponent extends VerticalLayout {
    private final Image itemImage;
    private final VerticalLayout itemDetails;
    private final TextField itemName;
    private final TextField itemPrice;
    private final TextField itemQuantity;
    private final TextArea itemDescription;
    private final TextField itemCategories;
    private final NumberField quantityField;
    private final Button addToCartButton;

    public ItemComponent(ItemDTO item) {
        setWidthFull();
        setPadding(true);
        setSpacing(true);
        setMargin(true);
        addClassName("item-component");

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setWidthFull();
        mainLayout.setSpacing(true);
        mainLayout.setPadding(true);

        itemImage = new Image("https://via.placeholder.com/200", "Item Image");
        itemImage.setWidth("300px");
        itemImage.setHeight("300px");
        itemImage.getStyle().set("object-fit", "cover");
        generateImage(item);

        itemDetails = new VerticalLayout();
        itemDetails.setSpacing(true);
        itemDetails.setPadding(true);
        itemDetails.setMargin(false);
        itemDetails.setWidthFull();

        itemName = new TextField("Name");
        itemName.setValue(item.getItemName());
        itemName.setReadOnly(true);
        itemName.setWidthFull();

        itemDescription = new TextArea("Description");
        itemDescription.setValue(item.getDescription());
        itemDescription.setReadOnly(true);
        itemDescription.setWidthFull();

        HorizontalLayout nameAndDescLayout = new HorizontalLayout(itemName, itemDescription);
        nameAndDescLayout.setWidthFull();
        nameAndDescLayout.setSpacing(true);

        itemQuantity = new TextField("Available Quantity");
        itemQuantity.setValue(String.valueOf(item.getQuantity()));
        itemQuantity.setReadOnly(true);
        itemQuantity.setWidthFull();

        itemCategories = new TextField("Categories");
        itemCategories.setValue(String.join(", ", item.getCategories()));
        itemCategories.setReadOnly(true);
        itemCategories.setWidthFull();

        HorizontalLayout quantityAndCategoriesLayout = new HorizontalLayout(itemQuantity, itemCategories);
        quantityAndCategoriesLayout.setWidthFull();
        quantityAndCategoriesLayout.setSpacing(true);

        itemPrice = new TextField("Price");
        itemPrice.setValue(String.valueOf(item.getTotalPrice()));
        itemPrice.setReadOnly(true);
        itemPrice.setWidth("33%");

        quantityField = new NumberField("Quantity to Add");
        quantityField.setValue(1.0);
        quantityField.setStep(1);
        quantityField.setMin(1.0);
        quantityField.setWidth("33%");

        addToCartButton = new Button("Add to Cart");
        addToCartButton.setWidth("33%");

        HorizontalLayout priceAndActionsLayout = new HorizontalLayout(itemPrice, quantityField, addToCartButton);
        priceAndActionsLayout.setWidthFull();
        priceAndActionsLayout.setSpacing(true);
        priceAndActionsLayout.setAlignItems(Alignment.END);

        itemDetails.add(nameAndDescLayout, quantityAndCategoriesLayout, priceAndActionsLayout);
        mainLayout.add(itemImage, itemDetails);
        add(mainLayout);
    }

    private void generateImage(ItemDTO item) {
        String imageUrl = item.getImageURL();
        if (imageUrl == null || imageUrl.isEmpty()) {
            imageUrl = ImageService.getImageUrl(item.getItemName());
        }
        itemImage.setSrc(imageUrl);
    }

    public Button getAddToCartButton() {
        return addToCartButton;
    }

    public NumberField getQuantityField() {
        return quantityField;
    }
}
