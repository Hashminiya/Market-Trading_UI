
package org.vaadin.UI.presenter;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.PurchaseDTO;
import org.vaadin.UI.model.models.InventoryModel;
import org.vaadin.UI.model.models.MarketHistoryPurchasesModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.MarketHistoryPurchasesView;

import java.util.ArrayList;

public class MarketHistoryPurchasesPresenter implements IPresenter {

    private final MarketHistoryPurchasesView view;
    private MarketHistoryPurchasesModel marketHistoryModel;

    private ArrayList<PurchaseDTO> purchases;

    public MarketHistoryPurchasesPresenter(MarketHistoryPurchasesView view) {
        this.view = view;
        this.marketHistoryModel = new MarketHistoryPurchasesModel();
    }

    @Override
    public void onViewLoaded() {
        purchases = marketHistoryModel.getMarketHistory(Credentials.getToken());
        view.fillUpMarketHistory(purchases);
    }

    @Override
    public void onViewStopped() {
        // Implement any cleanup logic here
    }
}
