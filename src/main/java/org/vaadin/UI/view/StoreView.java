package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.presenter.StorePresenter;
import org.vaadin.UI.view.components.ItemComponent;

import java.util.List;

@Route("store")
public class StoreView extends ViewTemplate implements BeforeEnterObserver {
    private final StorePresenter presenter;
    private final VerticalLayout itemList;
    private final H1 storeNameTitle;
    private final Paragraph storeDescription;

    public StoreView() {
        this.presenter = new StorePresenter(this);
        this.itemList = new VerticalLayout();
        this.storeNameTitle = new H1();
        this.storeDescription = new Paragraph();

        storeNameTitle.getStyle().set("margin-top", "20px");
        storeDescription.getStyle().set("margin-bottom", "20px");

        add(storeNameTitle);
        add(storeDescription);
        add(itemList);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String storeName = event.getLocation().getQueryParameters().getParameters().getOrDefault("storeName", List.of("")).get(0);
        presenter.onViewLoaded(storeName);
    }

    public void displayStoreDetails(StoreDTO store) {
        storeNameTitle.setText(store.getName());
        storeDescription.setText(store.getDescription());

        itemList.removeAll();
        HorizontalLayout itemLayout = new HorizontalLayout();
        itemLayout.setSpacing(true);
        for (ItemDTO item : store.getItems()) {
            itemLayout.add(new ItemComponent(item));
        }
        itemList.add(itemLayout);
    }
}
