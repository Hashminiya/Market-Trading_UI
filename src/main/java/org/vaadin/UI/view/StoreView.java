package org.vaadin.UI.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.UI.model.StoreService.Store;
import org.vaadin.UI.presenter.StorePresenter;

import java.util.List;

public class StoreView extends VerticalLayout {
    private Grid<Store> storeGrid;
    private StorePresenter presenter;

    public StoreView(StorePresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);

        storeGrid = new Grid<>(Store.class);
        storeGrid.setColumns("name", "description");

        add(storeGrid);
        presenter.loadStores();
    }

    public void setStores(List<Store> stores) {
        storeGrid.setItems(stores);
    }
}
