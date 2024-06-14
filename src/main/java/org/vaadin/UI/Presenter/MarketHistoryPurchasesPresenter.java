package org.vaadin.UI.Presenter;
import org.vaadin.UI.model.DTOs.PurchaseDTO;
import org.vaadin.UI.model.models.MarketHistoryPurchasesModel;
import org.vaadin.UI.presenter.Interfaces.IPresenter;
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
        //model.getListOfPurchases();
        //TODO: change it
        view.setGrid(makeDemoListOfPurchases());
    }
    private List<PurchaseDTO> makeDemoListOfPurchases() {
        List<PurchaseDTO> purchases = new ArrayList<>();
        purchases.add(new PurchaseDTO(1L, "Product A", 2, 20.0, "John Doe"));
        purchases.add(new PurchaseDTO(2L, "Product B", 1, 100.0, "Jane Doe"));
        purchases.add(new PurchaseDTO(3L, "Product C", 5, 50.0, "Alice Smith"));
        purchases.add(new PurchaseDTO(4L, "Product D", 3, 30.0, "Bob Johnson"));
        purchases.add(new PurchaseDTO(5L, "Product E", 10, 10.0, "Carol White"));
        purchases.add(new PurchaseDTO(6L, "Product F", 7, 70.0, "David Brown"));
        purchases.add(new PurchaseDTO(7L, "Product G", 6, 60.0, "Eva Green"));
        purchases.add(new PurchaseDTO(8L, "Product H", 8, 80.0, "Frank Black"));
        purchases.add(new PurchaseDTO(9L, "Product I", 9, 90.0, "Grace White"));
        purchases.add(new PurchaseDTO(10L, "Product J", 4, 40.0, "Henry Blue"));
        purchases.add(new PurchaseDTO(11L, "Product K", 11, 110.0, "Isabella Silver"));
        purchases.add(new PurchaseDTO(12L, "Product L", 12, 120.0, "Jack Gold"));
        purchases.add(new PurchaseDTO(13L, "Product M", 13, 130.0, "Karen Brown"));
        purchases.add(new PurchaseDTO(14L, "Product N", 14, 140.0, "Leo Red"));
        purchases.add(new PurchaseDTO(15L, "Product O", 15, 150.0, "Maria Yellow"));
        // Add more purchases as needed
        return purchases;
    }
}
