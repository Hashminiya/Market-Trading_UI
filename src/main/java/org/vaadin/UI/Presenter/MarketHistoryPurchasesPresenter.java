package org.vaadin.UI.Presenter;
import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.PurchaseDTO;
import org.vaadin.UI.model.models.MarketHistoryPurchasesModel;
import org.vaadin.UI.Presenter.Interfaces.IPresenter;
import org.vaadin.UI.view.MarketHistoryPurchasesView;

import java.util.ArrayList;
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
//        List<PurchaseDTO> resultListOfPurchases = model.getListOfPurchases(Credentials.getToken());
//
//        if(resultListOfPurchases!= null){
//            view.setGrid(resultListOfPurchases);
//        }
//        else view.showNotification("Failed to fetch purchases history");
        view.setGrid(getDemoPurchases());
    }

    public ArrayList<PurchaseDTO> getDemoPurchases() {
        ArrayList<PurchaseDTO> purchases = new ArrayList<>();

        purchases.add(new PurchaseDTO(1L, "Laptop", 1, 999.99, "Alice"));
        purchases.add(new PurchaseDTO(2L, "Smartphone", 2, 499.99, "Bob"));
        purchases.add(new PurchaseDTO(3L, "Headphones", 5, 79.99, "Charlie"));
        purchases.add(new PurchaseDTO(4L, "Monitor", 3, 199.99, "Dave"));
        purchases.add(new PurchaseDTO(5L, "Keyboard", 4, 49.99, "Eve"));

        return purchases;
    }
}
