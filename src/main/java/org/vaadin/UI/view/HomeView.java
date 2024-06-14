package org.vaadin.UI.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.presenter.HomePresenter;
import org.vaadin.UI.view.components.StoreComponent;

import java.util.List;

@Route("")
public class HomeView extends ViewTemplate {
    private final HomePresenter presenter;
    private final VerticalLayout storeList;

    public HomeView() {
        this.presenter = new HomePresenter(this);
        this.storeList = new VerticalLayout();
        add(storeList);
        presenter.onViewLoaded();
    }

    public void displayStores(List<StoreDTO> stores) {
        storeList.removeAll();
        for (StoreDTO store : stores) {
            storeList.add(new StoreComponent(store));
        }
    }
}
