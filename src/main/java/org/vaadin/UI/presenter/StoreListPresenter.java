package org.vaadin.UI.presenter;

import com.vaadin.flow.component.UI;
import org.vaadin.UI.model.StoreService;
import org.vaadin.UI.model.StoreService.Product;
import org.vaadin.UI.model.StoreService.Store;
import org.vaadin.UI.view.StoreListView;

import java.util.List;
import java.util.stream.Collectors;

public class StoreListPresenter {
    private StoreListView view;
    private StoreService storeService;

    public StoreListPresenter() {
        this.storeService = new StoreService();
    }

    public void setView(StoreListView view) {
        this.view = view;
    }

    public void loadStores() {
        List<Store> stores = storeService.getStores();
        view.setStores(stores);
    }

    public void onStoreNameClick(Store store) {
        // Navigate to the store detail page
        UI.getCurrent().navigate("store/" + store.getName());
    }

    public void searchProducts(Store store, String query) {
        List<Product> filteredProducts = store.getProducts().stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        view.updateProductList(store, filteredProducts);
    }

    public void addToCart(Product product) {
        // Logic to add product to the cart
        System.out.println("Added to cart: " + product.getName());
    }
}
