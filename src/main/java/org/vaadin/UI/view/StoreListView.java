package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.UI.model.StoreService;
import org.vaadin.UI.model.StoreService.Product;
import org.vaadin.UI.model.StoreService.Store;
import org.vaadin.UI.presenter.StoreListPresenter;

import java.util.List;

public class StoreListView extends VerticalLayout {
    private StoreListPresenter presenter;

    public StoreListView(StoreListPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);

        presenter.loadStores();
    }

    public void setStores(List<Store> stores) {
        removeAll();
        for (Store store : stores) {
            add(createStoreRow(store));
        }
    }

    private VerticalLayout createStoreRow(Store store) {
        VerticalLayout storeRow = new VerticalLayout();
        storeRow.setWidthFull();

        // Store Name Button
        Button storeNameButton = new Button(store.getName());
        storeNameButton.addClickListener(e -> presenter.onStoreNameClick(store));

        // Search Bar
        TextField searchBar = new TextField();
        searchBar.setPlaceholder("Search products...");
        searchBar.setWidthFull();
        searchBar.addValueChangeListener(e -> presenter.searchProducts(store, e.getValue()));

        // Products Horizontal Scroll with Navigation Arrows
        HorizontalLayout productsLayoutWrapper = new HorizontalLayout();
        productsLayoutWrapper.setWidthFull();
        productsLayoutWrapper.setAlignItems(Alignment.CENTER);
        productsLayoutWrapper.getStyle().set("overflow-x", "auto");

        Div productsLayout = new Div();
        productsLayout.getStyle().set("display", "flex");
        productsLayout.getStyle().set("flex-wrap", "nowrap");
        productsLayout.getStyle().set("align-items", "center");

        final int[] start = {0};
        final int[] end = {Math.min(8, store.getProducts().size())};
        updateProductsLayout(productsLayout, store, start[0], end[0]);

        // Navigation Buttons
        Button leftArrow = new Button(new Icon(VaadinIcon.ARROW_LEFT));
        leftArrow.addClickListener(e -> {
            if (start[0] > 0) {
                start[0] -= 8;
                end[0] = start[0] + 8;
                updateProductsLayout(productsLayout, store, start[0], end[0]);
            }
        });

        Button rightArrow = new Button(new Icon(VaadinIcon.ARROW_RIGHT));
        rightArrow.addClickListener(e -> {
            if (end[0] < store.getProducts().size()) {
                start[0] += 8;
                end[0] = start[0] + 8;
                updateProductsLayout(productsLayout, store, start[0], end[0]);
            }
        });

        productsLayoutWrapper.add(leftArrow, productsLayout, rightArrow);
        storeRow.add(storeNameButton, searchBar, productsLayoutWrapper);

        return storeRow;
    }

    private void updateProductsLayout(Div productsLayout, Store store, int start, int end) {
        productsLayout.removeAll();
        List<Product> products = store.getProducts().subList(start, end);
        for (Product product : products) {
            productsLayout.add(createProductCard(product));
        }
    }

    private VerticalLayout createProductCard(Product product) {
        VerticalLayout productLayout = new VerticalLayout();
        productLayout.setAlignItems(Alignment.CENTER);
        productLayout.getStyle().set("min-width", "150px");
        productLayout.getStyle().set("max-width", "150px");

        Div productImage = new Div();
        productImage.getStyle().set("background-image", "url(" + product.getPicture() + ")");
        productImage.getStyle().set("width", "100px");
        productImage.getStyle().set("height", "100px");
        productImage.getStyle().set("background-size", "cover");
        productLayout.add(productImage);

        Div productName = new Div();
        productName.setText(product.getName());
        productName.getStyle().set("font-weight", "bold");
        productName.getStyle().set("color", "black");
        productLayout.add(productName);

        Div productDescription = new Div();
        productDescription.setText(product.getDescription());
        productDescription.getStyle().set("color", "grey");
        productLayout.add(productDescription);

        Div productPrice = new Div();
        productPrice.setText("$" + product.getPrice());
        productPrice.getStyle().set("color", "black");
        productLayout.add(productPrice);

        // Add to Cart Button
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.addClickListener(e -> presenter.addToCart(product));
        productLayout.add(addToCartButton);

        return productLayout;
    }

    public void updateProductList(Store store, List<Product> products) {
        // Find the store row and update the product list
        for (int i = 0; i < getComponentCount(); i++) {
            VerticalLayout storeRow = (VerticalLayout) getComponentAt(i);
            Button storeNameButton = (Button) storeRow.getComponentAt(0);
            if (storeNameButton.getText().equals(store.getName())) {
                HorizontalLayout productsLayoutWrapper = (HorizontalLayout) storeRow.getComponentAt(2);
                Div productsLayout = (Div) productsLayoutWrapper.getComponentAt(1);
                productsLayout.removeAll();
                for (Product product : products) {
                    productsLayout.add(createProductCard(product));
                }
            }
        }
    }
}
