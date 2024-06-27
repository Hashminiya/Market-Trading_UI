package org.vaadin.UI.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.ItemDTO;
import org.vaadin.UI.model.DTOs.PurchaseDTO;
import org.vaadin.UI.presenter.MarketHistoryPurchasesPresenter;
import org.vaadin.UI.view.components.ItemForm;

import java.util.ArrayList;
import java.util.List;

@Route("settings/market-history")
public class MarketHistoryPurchasesView extends MainSettingView {
    private Grid<PurchaseDTO> marketHistoryGrid;
    private MarketHistoryPurchasesPresenter presenter;


    public MarketHistoryPurchasesView() {
        presenter = new MarketHistoryPurchasesPresenter(this);

        VerticalLayout rightContent = getRightContent();
        rightContent.removeAll();

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setWidthFull();
        topLayout.setAlignItems(FlexComponent.Alignment.END);
        rightContent.add(topLayout);

        marketHistoryGrid = createMarketHistoryGrid();
        marketHistoryGrid.setWidthFull();
        rightContent.add(marketHistoryGrid);

        presenter.onViewLoaded();


//        marketHistoryGrid.asSingleSelect().addValueChangeListener(event -> {
//            if (event.getValue() != null) {
//                showForm(true, event.getValue());
//            } else {
//                showForm(false, null);
//            }
//        });

    }


    public void fillUpMarketHistory(ArrayList<PurchaseDTO> marketHistoryList) {
        marketHistoryGrid.setItems(marketHistoryList);
    }

    private Grid<PurchaseDTO> createMarketHistoryGrid() {
        Grid<PurchaseDTO> grid = new Grid<>(PurchaseDTO.class);
        grid.setColumns("id", "totalAmount", "userId","purchaseDate");
        grid.getColumnByKey("id").setHeader("Purchase ID");
        grid.getColumnByKey("totalAmount").setHeader("Total-Amount");
        grid.getColumnByKey("userId").setHeader("User ID");
        grid.getColumnByKey("purchaseDate").setHeader("Purchase-Date");
        return grid;
    }

}
