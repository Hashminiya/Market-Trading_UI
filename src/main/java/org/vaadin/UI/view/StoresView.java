package org.vaadin.UI.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Presenter.StoreListPresenter;

@Route("stores")
public class StoresView extends ViewTemplate {

    public StoresView() {
        super(); // Call the constructor of ViewTemplate to add the header

        StoreListPresenter presenter = new StoreListPresenter();
        StoreListView view = new StoreListView(presenter);

        // Add main content to the layout
        add(view);
    }
}
