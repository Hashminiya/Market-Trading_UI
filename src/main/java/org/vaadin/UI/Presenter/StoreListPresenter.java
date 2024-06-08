package org.vaadin.UI.Presenter;
import org.vaadin.UI.model.DTOs.ProductDTO;
import org.vaadin.UI.model.models.StoreModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import com.vaadin.flow.component.UI;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.view.StoreListView;

import java.util.List;
import java.util.stream.Collectors;

public class StoreListPresenter implements IPresenter {
    private StoreListView view;
    private StoreModel StoreModle;

    public StoreListPresenter() {
        this.StoreModle = new StoreModel();
    }

    public void setView(StoreListView view) {
        this.view = view;
    }

    public void loadStores() {
        List<StoreDTO> stores = StoreModel.getStores();
        view.setStores(stores);
    }

    public void onStoreNameClick(StoreDTO store) {
        // Navigate to the store detail page
        UI.getCurrent().navigate("store/" + store.getName());
    }

    public void searchProducts(StoreDTO store, String query) {
        List<ProductDTO> filteredProducts = store.getProducts().stream()
                .filter(product -> product.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        view.updateProductList(store, filteredProducts);
    }

    public void addToCart(ProductDTO product) {
        // Logic to add product to the cart
        System.out.println("Added to cart: " + product.getName());
    }

    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }
}
