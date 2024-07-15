package org.vaadin.UI.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.PurchaseDTO;
import org.vaadin.UI.presenter.MarketHistoryPurchasesPresenter;

import java.util.List;

@Route("settings/market-history")
public class MarketHistoryPurchasesView extends MainSettingView {

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

        presenter.onInitGrid();
    }

    private Grid<PurchaseDTO> initGrid() {
        grid = new Grid<>(PurchaseDTO.class, false);
        grid.addColumn(PurchaseDTO::getPurchaseId).setHeader("Purchase ID").setSortable(true);
        grid.addColumn(purchase -> purchase.getPurchasedItemsList().stream()
                        .map(item -> item.getItemName())
                        .reduce((item1, item2) -> item1 + ", " + item2).orElse("No items"))
                .setHeader("Items").setSortable(true);
        grid.addColumn(PurchaseDTO::getTotalAmount).setHeader("Total Amount").setSortable(true);
        grid.addColumn(PurchaseDTO::getUserId).setHeader("User ID").setSortable(true);
        grid.addColumn(PurchaseDTO::getPurchaseDate).setHeader("Purchase Date").setSortable(true);
        return grid;
    }

    public void showNotification(String message) {
        Notification.show(message);
    }

    public void setGrid(List<PurchaseDTO> listOfPurchases) {
        grid.setItems(listOfPurchases);
    }
}
