package org.vaadin.UI.view;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.PurchaseDTO;
import org.vaadin.UI.presenter.PurchasesPresenter;

import java.util.ArrayList;
import java.util.List;

@Route("settings/purchases")
public class PurchasesView extends MainSettingView {
    private ComboBox<String> chooseStoreComboBox;
    private Grid<PurchaseDTO> purchaseGrid;
    private PurchasesPresenter presenter;

    public PurchasesView() {
        presenter = new PurchasesPresenter(this);

        VerticalLayout rightContent = getRightContent();
        rightContent.removeAll();

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.add(new H1("Purchases - store"));
        chooseStoreComboBox = new ComboBox<>("Select your store");
        chooseStoreComboBox.setPlaceholder("No store selected yet");
        presenter.onChoosingStore();
        topLayout.add(chooseStoreComboBox);
        topLayout.setWidthFull();
        topLayout.setAlignItems(FlexComponent.Alignment.END);
        rightContent.add(topLayout);

        purchaseGrid = createPurchasesGrid();
        purchaseGrid.setWidthFull();
        rightContent.add(purchaseGrid);

        chooseStoreComboBox.addValueChangeListener(event -> {
            String selectedOptionStoreName = event.getValue();
            presenter.onSelectStore(selectedOptionStoreName);
        });
    }

    public void fillChooseStoreComboBox(List<String> storeList) {
        chooseStoreComboBox.setItems(storeList);
    }

    public void fillUpInventory(ArrayList<PurchaseDTO> purchaseDTOS) {
        purchaseGrid.setItems(purchaseDTOS);
    }

    private Grid<PurchaseDTO> createPurchasesGrid() {
        Grid<PurchaseDTO> grid = new Grid<>(PurchaseDTO.class, false);
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
}
