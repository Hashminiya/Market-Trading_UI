package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.UI.model.StoreService;
import org.vaadin.UI.model.StoreService.Product;
import org.vaadin.UI.model.StoreService.Store;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Route("product")
public class ProductDetailView extends VerticalLayout implements BeforeEnterObserver {
    private Div productImage;
    private Div productName;
    private Div productDescription;
    private Div productPrice;
    private Div productCategory;
    private Div productRating;

    private VerticalLayout productInfoLayout;
    private Div relatedProductsContainer;
    private StoreService storeService;

    public ProductDetailView() {
        storeService = new StoreService();

        // Menu Bar
        HorizontalLayout menuBar = createMenuBar();
        add(menuBar);

        // Product Info Layout
        productInfoLayout = new VerticalLayout();
        productInfoLayout.setAlignItems(Alignment.CENTER);
        productInfoLayout.setWidth("50%");
        productInfoLayout.setMargin(true);
        productInfoLayout.setPadding(true);
        productInfoLayout.getStyle().set("margin", "auto");

        productImage = new Div();
        productImage.setWidth("200px");
        productImage.setHeight("200px");
        productImage.getStyle().set("background-size", "cover");

        productName = new Div();
        productName.getStyle().set("font-weight", "bold");
        productName.getStyle().set("font-size", "24px");

        productDescription = new Div();
        productDescription.getStyle().set("color", "grey");

        productPrice = new Div();
        productPrice.getStyle().set("font-size", "18px");

        productCategory = new Div();
        productCategory.getStyle().set("color", "black");

        productRating = new Div();
        productRating.getStyle().set("color", "black");

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.addClickListener(e -> System.out.println("Added to cart: " + productName.getText()));

        productInfoLayout.add(productImage, productName, productDescription, productPrice, productCategory, productRating, addToCartButton);
        add(productInfoLayout);

        // Related Products
        relatedProductsContainer = new Div();
        relatedProductsContainer.getStyle().set("overflow-x", "auto");
        relatedProductsContainer.getStyle().set("white-space", "nowrap");
        relatedProductsContainer.setWidthFull();
        relatedProductsContainer.setHeight("250px");
        add(relatedProductsContainer);
    }

    private HorizontalLayout createMenuBar() {
        HorizontalLayout menuBar = new HorizontalLayout();
        menuBar.setWidthFull();
        menuBar.setSpacing(true);
        menuBar.setAlignItems(Alignment.CENTER);

        Button logo = new Button("Logo");
        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign Up");
        Button logoutButton = new Button("Logout");
        Button manageStoresButton = new Button("Manage Stores");
        Button createNewStoreButton = new Button("Create New Store");
        Button systemManagerButton = new Button("System Manager");

        menuBar.add(logo, loginButton, signUpButton, logoutButton, manageStoresButton, createNewStoreButton, systemManagerButton);
        return menuBar;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Map<String, List<String>> parameters = event.getLocation().getQueryParameters().getParameters();
        String productId = parameters.getOrDefault("productId", List.of()).stream().findFirst().orElse(null);

        if (productId != null) {
            Optional<Store> store = storeService.getStores().stream()
                    .flatMap(s -> s.getProducts().stream().map(p -> new Object[]{s, p}))
                    .filter(arr -> ((Product) arr[1]).getName().equals(productId))
                    .map(arr -> (Store) arr[0])
                    .findFirst();

            if (store.isPresent()) {
                Optional<Product> product = store.get().getProducts().stream()
                        .filter(p -> p.getName().equals(productId))
                        .findFirst();
                product.ifPresent(p -> {
                    displayProductDetails(p);
                    displayRelatedProducts(store.get());
                });
            } else {
                productName.setText("Product not found");
            }
        }
    }

    private void displayProductDetails(Product product) {
        productImage.getStyle().set("background-image", "url(" + product.getPicture() + ")");
        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText("$" + product.getPrice());
        productCategory.setText("Category: " + product.getCategory());
        productRating.setText("Rating: ★★★★☆");  // Example rating, replace with actual rating if available
    }

    private void displayRelatedProducts(Store store) {
        relatedProductsContainer.removeAll();
        List<Product> products = store.getProducts();
        for (Product product : products) {
            relatedProductsContainer.add(createRelatedProductCard(product));
        }
    }

    private VerticalLayout createRelatedProductCard(Product product) {
        VerticalLayout productLayout = new VerticalLayout();
        productLayout.setAlignItems(Alignment.CENTER);
        productLayout.getStyle().set("display", "inline-block");
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

        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.addClickListener(e -> System.out.println("Added to cart: " + product.getName()));

        productLayout.add(imageLink, nameLink, productPrice, addToCartButton);
        return productLayout;
    }
}
