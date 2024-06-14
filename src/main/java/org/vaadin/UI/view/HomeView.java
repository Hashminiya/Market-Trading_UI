package org.vaadin.UI.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.presenter.HomePresenter;
import org.vaadin.UI.view.components.StoreComponent;

import java.util.List;

@Route("")
public class HomeView extends ViewTemplate {
    private final HomePresenter presenter;

    public HomeView() {
        this.presenter = new HomePresenter(this);
        setSpacing(true);
        setPadding(true);

        H1 title = new H1("Welcome to Our Online Store");
        title.getStyle().set("text-align", "center");
        add(title);

        presenter.onViewLoaded();
    }

    public void displayStores(List<StoreDTO> stores) {
        VerticalLayout storesLayout = new VerticalLayout();
        storesLayout.setSpacing(true);

        for (StoreDTO store : stores) {
            Div storeDiv = new Div();
            storeDiv.add(new StoreComponent(store));
            storeDiv.getStyle().set("margin-bottom", "20px");
            storesLayout.add(storeDiv);
        }

        add(storesLayout);
    }
}
