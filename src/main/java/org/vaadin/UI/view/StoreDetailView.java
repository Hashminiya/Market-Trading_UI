package org.vaadin.UI.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.UI.model.StoreService;
import org.vaadin.UI.model.StoreService.Product;
import org.vaadin.UI.model.StoreService.Store;
import org.vaadin.UI.presenter.StoreDetailPresenter;

@Route("store/:storeId")
public class StoreDetailView extends VerticalLayout implements BeforeEnterObserver {
    private StoreDetailPresenter presenter;
    private Grid<Product> productGrid;

    public StoreDetailView() {
        presenter = new StoreDetailPresenter(this, new StoreService());

        // Back to store list link
        RouterLink backLink = new RouterLink("Back to Store List", StorePage.class);
        add(backLink);

        productGrid = new Grid<>(Product.class);
        productGrid.setColumns("picture", "name", "price");
        productGrid.getColumnByKey("picture").setHeader("Picture").setAutoWidth(true);
        productGrid.getColumnByKey("name").setHeader("Name").setAutoWidth(true);
        productGrid.getColumnByKey("price").setHeader("Price").setAutoWidth(true);

        add(productGrid);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        RouteParameters parameters = event.getRouteParameters();
        String storeId = parameters.get("storeId").orElse(null);
        if (storeId != null) {
            presenter.loadStore(storeId);
        }
    }

    public void setStore(Store store) {
        setTitle(store.getName() + " - " + store.getDescription());
        productGrid.setItems(store.getProducts());
    }

    private void setTitle(String title) {
        getElement().setProperty("title", title);
    }
}
