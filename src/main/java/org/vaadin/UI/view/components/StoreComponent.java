package org.vaadin.UI.view.components;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.view.StoreView;

import java.util.Collections;

public class StoreComponent extends VerticalLayout {
    public StoreComponent(StoreDTO store) {
        RouterLink storeLink = new RouterLink();
        storeLink.setRoute(StoreView.class);
        storeLink.setText(store.getName());
        storeLink.setQueryParameters(new QueryParameters(Collections.singletonMap("storeName", Collections.singletonList(store.getName()))));
        storeLink.getStyle().set("cursor", "pointer");
        add(storeLink);

        HorizontalLayout itemsLayout = new HorizontalLayout();
        store.getItems().forEach(item -> itemsLayout.add(new ItemComponent(item)));
        add(itemsLayout);
    }
}
