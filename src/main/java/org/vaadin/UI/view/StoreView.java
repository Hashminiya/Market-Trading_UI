package org.vaadin.UI.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.model.models.StoreModel;
import org.vaadin.UI.Presenter.StorePresenter;

import java.util.List;

public class StoreView extends VerticalLayout {
    private Grid<StoreDTO> storeGrid;
    private StorePresenter presenter;

    public StoreView(StorePresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);

        storeGrid = new Grid<>(StoreDTO.class);
        storeGrid.setColumns("name", "description");

        add(storeGrid);
        presenter.loadStores();
    }

    public void setStores(List<StoreDTO> stores) {
        storeGrid.setItems(stores);
    }
}
