package org.vaadin.UI.view;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.presenter.StorePresenter;
import org.vaadin.UI.view.components.ItemComponent;

import java.util.List;

@Route("store")
public class StoreView extends ViewTemplate implements BeforeEnterObserver {
    private final StorePresenter presenter;
    private final VerticalLayout itemList;

    public StoreView() {
        this.presenter = new StorePresenter(this);
        this.itemList = new VerticalLayout();
        add(itemList);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String storeName = event.getLocation().getQueryParameters().getParameters().getOrDefault("storeName", List.of("")).get(0);
        presenter.onViewLoaded(storeName);
    }

    public void displayItems(List<ItemDTO> items) {
        itemList.removeAll();
        HorizontalLayout itemLayout = new HorizontalLayout();
        for (ItemDTO item : items) {
            itemLayout.add(new ItemComponent(item));
        }
        itemList.add(itemLayout);
    }
}
