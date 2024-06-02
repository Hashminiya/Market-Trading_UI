package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
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

        // Store Name Button
        Button storeNameButton = new Button(store.getName());
        storeNameButton.addClickListener(e -> presenter.onStoreNameClick(store));

        // Search Bar
        TextField searchBar = new TextField();
        searchBar.setPlaceholder("Search products...");
        searchBar.addValueChangeListener(e -> presenter.searchProducts(store, e.getValue()));

        // Products Horizontal Scroll
        Div scrollContainer = new Div();
        scrollContainer.getStyle().set("overflow-x", "auto");
        scrollContainer.getStyle().set("display", "flex");
        HorizontalLayout productsLayout = new HorizontalLayout();
        productsLayout.setSpacing(true);

        for (Product product : store.getProducts()) {
            productsLayout.add(createProductCard(product));
        }

        scrollContainer.add(productsLayout);
        storeRow.add(storeNameButton, searchBar, scrollContainer);

        return storeRow;
    }

    private VerticalLayout createProductCard(Product product) {
        VerticalLayout productLayout = new VerticalLayout();
        Div productImage = new Div();
        productImage.getStyle().set("background-image", "url(" + product.getPicture() + ")");
        productImage.getStyle().set("width", "100px");
        productImage.getStyle().set("height", "100px");
        productImage.getStyle().set("background-size", "cover");
        productLayout.add(productImage);
        productLayout.add(product.getName());
        productLayout.add(product.getDescription());
        productLayout.add("$" + product.getPrice());
        productLayout.add(product.getCategory());

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
                HorizontalLayout productsLayout = (HorizontalLayout) ((Div) storeRow.getComponentAt(2)).getComponentAt(0);
                productsLayout.removeAll();
                for (Product product : products) {
                    productsLayout.add(createProductCard(product));
                }
            }
        }
    }
}
