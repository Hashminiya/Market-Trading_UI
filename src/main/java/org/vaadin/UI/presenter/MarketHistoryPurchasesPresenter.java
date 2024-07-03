package org.vaadin.UI.presenter;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.PurchaseDTO;
import org.vaadin.UI.model.models.MarketHistoryPurchasesModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.MarketHistoryPurchasesView;

import java.util.List;

public class MarketHistoryPurchasesPresenter implements IPresenter {

    private MarketHistoryPurchasesView view;
    private MarketHistoryPurchasesModel model;

    public MarketHistoryPurchasesPresenter(MarketHistoryPurchasesView view){
        this.view = view;
        model = new MarketHistoryPurchasesModel();
    }
    @Override
    public void onViewLoaded() {

    }

    @Override
    public void onViewStopped() {

    }

    public void onInitGrid(){
        List<PurchaseDTO> resultListOfPurchases = model.getListOfPurchases(Credentials.getToken());

        if(resultListOfPurchases!= null){
            view.setGrid(resultListOfPurchases);
        }
        else view.showNotification("Failed to fetch purchases history");
    }
}
