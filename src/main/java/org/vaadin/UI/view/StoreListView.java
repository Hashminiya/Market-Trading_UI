package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
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

        setWidthFull();
        setHeightFull();
        getStyle().set("overflow", "hidden");

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

        // Products Horizontal Scroll Container
        Div scrollContainer = new Div();
        scrollContainer.getStyle().set("overflow-x", "auto");
        scrollContainer.getStyle().set("white-space", "nowrap");
        scrollContainer.getStyle().set("width", "100%");
        scrollContainer.getStyle().set("display", "flex");
        scrollContainer.getStyle().set("align-items", "center");

        // Products Layout
        Div productsLayout = new Div();
        productsLayout.getStyle().set("display", "flex");
        productsLayout.getStyle().set("flex-wrap", "nowrap");
        productsLayout.getStyle().set("align-items", "center");

        // Populate Products
        updateProductsLayout(productsLayout, store.getProducts());

        scrollContainer.add(productsLayout);
        storeRow.add(storeNameButton, searchBar, scrollContainer);

        return storeRow;
    }

    private void updateProductsLayout(Div productsLayout, List<Product> products) {
        productsLayout.removeAll();
        for (Product product : products) {
            productsLayout.add(createProductCard(product));
        }
    }

    private VerticalLayout createProductCard(Product product) {
        VerticalLayout productLayout = new VerticalLayout();
        productLayout.setAlignItems(Alignment.CENTER);
        productLayout.getStyle().set("min-width", "150px");
        productLayout.getStyle().set("max-width", "150px");

        // Product Image Link
        Anchor imageLink = new Anchor("product?productId=" + product.getName(), "");
        imageLink.getElement().getStyle().set("display", "block");
        imageLink.getElement().getStyle().set("width", "100px");
        imageLink.getElement().getStyle().set("height", "100px");
        imageLink.getElement().getStyle().set("background-image", "url(" + product.getPicture() + ")");
        imageLink.getElement().getStyle().set("background-size", "cover");

        // Product Name Link
        Anchor nameLink = new Anchor("product?productId=" + product.getName(), product.getName());
        nameLink.getElement().getStyle().set("font-weight", "bold");
        nameLink.getElement().getStyle().set("color", "black");

        Div productPrice = new Div();
        productPrice.setText("$" + product.getPrice());
        productPrice.getStyle().set("color", "black");

        // Add to Cart Button
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.addClickListener(e -> presenter.addToCart(product));

        productLayout.add(imageLink, nameLink, productPrice, addToCartButton);

        return productLayout;
    }

    public void updateProductList(Store store, List<Product> products) {
        // Find the store row and update the product list
        for (int i = 0; i < getComponentCount(); i++) {
            VerticalLayout storeRow = (VerticalLayout) getComponentAt(i);
            Button storeNameButton = (Button) storeRow.getComponentAt(0);
            if (storeNameButton.getText().equals(store.getName())) {
                Div scrollContainer = (Div) storeRow.getComponentAt(2);
                Div productsLayout = (Div) scrollContainer.getComponentAt(0);
                updateProductsLayout(productsLayout, products);
            }
        }
    }
}
