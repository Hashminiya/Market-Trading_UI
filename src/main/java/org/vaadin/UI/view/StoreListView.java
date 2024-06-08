package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.vaadin.UI.model.DTOs.ProductDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.Presenter.StoreListPresenter;

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

    public void setStores(List<StoreDTO> stores) {
        removeAll();
        for (StoreDTO store : stores) {
            add(createStoreRow(store));
        }
    }

    private VerticalLayout createStoreRow(StoreDTO store) {
        VerticalLayout storeRow = new VerticalLayout();
        storeRow.setWidthFull();

        // Store Name Button
        Button storeNameButton = new Button(store.getName());
        storeNameButton.addClickListener(e -> presenter.onStoreNameClick(store));

        // Search Bar
        TextField searchBar = new TextField();
        searchBar.setPlaceholder("Search ProductDTOs...");
        searchBar.setWidthFull();
        searchBar.addValueChangeListener(e -> presenter.searchProducts(store, e.getValue()));

        // ProductDTOs Horizontal Scroll Container
        Div scrollContainer = new Div();
        scrollContainer.getStyle().set("overflow-x", "auto");
        scrollContainer.getStyle().set("white-space", "nowrap");
        scrollContainer.getStyle().set("width", "100%");
        scrollContainer.getStyle().set("display", "flex");
        scrollContainer.getStyle().set("align-items", "center");

        // ProductDTOs Layout
        Div ProductDTOsLayout = new Div();
        ProductDTOsLayout.getStyle().set("display", "flex");
        ProductDTOsLayout.getStyle().set("flex-wrap", "nowrap");
        ProductDTOsLayout.getStyle().set("align-items", "center");

        // Populate ProductDTOs
        updateProductDTOsLayout(ProductDTOsLayout, store.getProducts());

        scrollContainer.add(ProductDTOsLayout);
        storeRow.add(storeNameButton, searchBar, scrollContainer);

        return storeRow;
    }

    private void updateProductDTOsLayout(Div ProductDTOsLayout, List<ProductDTO> ProductDTOs) {
        ProductDTOsLayout.removeAll();
        for (ProductDTO ProductDTO : ProductDTOs) {
            ProductDTOsLayout.add(createProductDTOCard(ProductDTO));
        }
    }

    private VerticalLayout createProductDTOCard(ProductDTO product) {
        VerticalLayout ProductDTOLayout = new VerticalLayout();
        ProductDTOLayout.setAlignItems(Alignment.CENTER);
        ProductDTOLayout.getStyle().set("min-width", "150px");
        ProductDTOLayout.getStyle().set("max-width", "150px");

        // ProductDTO Image Link
        Anchor imageLink = new Anchor("ProductDTO?ProductDTOId=" + product.getName(), "");
        imageLink.getElement().getStyle().set("display", "block");
        imageLink.getElement().getStyle().set("width", "100px");
        imageLink.getElement().getStyle().set("height", "100px");
        imageLink.getElement().getStyle().set("background-image", "url(" + product.getPicture() + ")");
        imageLink.getElement().getStyle().set("background-size", "cover");

        // ProductDTO Name Link
        Anchor nameLink = new Anchor("ProductDTO?ProductDTOId=" + product.getName(), product.getName());
        nameLink.getElement().getStyle().set("font-weight", "bold");
        nameLink.getElement().getStyle().set("color", "black");

        Div ProductDTOPrice = new Div();
        ProductDTOPrice.setText("$" + product.getPrice());
        ProductDTOPrice.getStyle().set("color", "black");

        // Add to Cart Button
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.addClickListener(e -> presenter.addToCart(product));

        ProductDTOLayout.add(imageLink, nameLink, ProductDTOPrice, addToCartButton);

        return ProductDTOLayout;
    }

    public void updateProductList(StoreDTO store, List<ProductDTO> ProductDTOs) {
        // Find the store row and update the ProductDTO list
        for (int i = 0; i < getComponentCount(); i++) {
            VerticalLayout storeRow = (VerticalLayout) getComponentAt(i);
            Button storeNameButton = (Button) storeRow.getComponentAt(0);
            if (storeNameButton.getText().equals(store.getName())) {
                Div scrollContainer = (Div) storeRow.getComponentAt(2);
                Div ProductDTOsLayout = (Div) scrollContainer.getComponentAt(0);
                updateProductDTOsLayout(ProductDTOsLayout, ProductDTOs);
            }
        }
    }
}
