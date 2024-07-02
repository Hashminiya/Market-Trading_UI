package org.vaadin.UI.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Presenter.MarketHistoryPurchasesPresenter;
import org.vaadin.UI.model.DTOs.PurchaseDTO;

import java.util.List;

@Route("settings/market-history")
public class MarketHistoryPurchasesView extends MainSettingView{

    private MarketHistoryPurchasesPresenter presenter;
    private Grid<PurchaseDTO> grid;
    public MarketHistoryPurchasesView() {
        presenter = new MarketHistoryPurchasesPresenter(this);

        VerticalLayout rightContent = getRightContent();
        rightContent.removeAll();
        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.add(new H1("Market History"));

        topLayout.setWidthFull();
        topLayout.setAlignItems(FlexComponent.Alignment.END);
        rightContent.add(topLayout);

        // Initialize the grid
        grid = initGrid();

        rightContent.add(grid);
    }
    private Grid initGrid() {
        grid = new Grid<>(PurchaseDTO.class);
        grid.setColumns("id", "productName", "quantity", "price", "buyer");
        presenter.onInitGrid();
        return grid;
    }
    public void showNotification(String message) {
        Notification.show(message);
    }

    public void setGrid(List<PurchaseDTO> listOfPurchases) {
        grid.setItems(listOfPurchases);
    }
}
