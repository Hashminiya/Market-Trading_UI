package org.vaadin.UI.view;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.UI.model.DTOs.ProductDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.model.models.StoreModel;

import org.vaadin.UI.Presenter.StoreDetailPresenter;

@Route("store/:storeId")
public class StoreDetailView extends VerticalLayout implements BeforeEnterObserver {
    private StoreDetailPresenter presenter;
    private Grid<ProductDTO> productGrid;

    public StoreDetailView() {
        presenter = new StoreDetailPresenter(this, new StoreModel());

        // Back to store list link
        RouterLink backLink = new RouterLink("Back to Store List", StoreView.class);
        add(backLink);

        productGrid = new Grid<>(ProductDTO.class);
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

    public void setStore(StoreDTO store) {
        setTitle(store.getName() + " - " + store.getDescription());
        productGrid.setItems(store.getProducts());
    }

    private void setTitle(String title) {
        getElement().setProperty("title", title);
    }
}
