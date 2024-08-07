package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.StoreDTO;
import org.vaadin.UI.presenter.HomePresenter;
import org.vaadin.UI.view.components.ItemListComponent;
import org.vaadin.UI.view.components.StoreComponentList;

import java.util.List;

@Route("")
public class HomeView extends ViewTemplate {
    private final HomePresenter presenter;
    private final VerticalLayout storesLayout;
    private final VerticalLayout mainLayout;
    private ItemListComponent itemListComponent;

    public HomeView() {
        this.itemListComponent = new ItemListComponent();
        this.presenter = new HomePresenter(this);
        setSpacing(true);
        setPadding(true);

        H1 title = new H1("Welcome to Our Online Store");
        title.getStyle().set("text-align", "center");
        add(title);

        TextField searchField = new TextField();
        searchField.setPlaceholder("Search products...");
        searchField.setWidthFull();

        Button searchButton = new Button("Search");
        searchButton.addClickListener(e -> presenter.onSearchRequested(searchField.getValue()));

        Button clearButton = new Button(new Icon(VaadinIcon.CLOSE));
        clearButton.addClickListener(e -> {
            searchField.clear();
            presenter.onViewLoaded();
        });

        HorizontalLayout searchLayout = new HorizontalLayout(searchField, searchButton, clearButton);
        searchLayout.setWidthFull();
        searchLayout.setFlexGrow(1, searchField);

        add(searchLayout);

        storesLayout = new VerticalLayout();
        storesLayout.setSpacing(true);
        add(storesLayout);

        mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        add(mainLayout);

        presenter.onViewLoaded();
    }

    public void displayStores(List<StoreDTO> stores) {
        mainLayout.removeAll();  // Clear mainLayout content before adding new stores
        storesLayout.removeAll();  // Clear existing content before adding new stores
        for (StoreDTO store : stores) {
            StoreComponentList storeComponent = new StoreComponentList(store);
            storesLayout.add(storeComponent);
        }
        mainLayout.add(storesLayout);  // Add the storesLayout to the mainLayout
    }

    public void displayItems(List<ItemDTO> items) {
        mainLayout.removeAll();  // Clear mainLayout content before adding new items
        this.itemListComponent = new ItemListComponent(items);
        mainLayout.add(itemListComponent);
    }
}
