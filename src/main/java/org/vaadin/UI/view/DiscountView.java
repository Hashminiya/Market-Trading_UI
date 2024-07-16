package org.vaadin.UI.view;

import com.vaadin.flow.component.PollEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import org.vaadin.UI.Util.Credentials;
import org.vaadin.UI.model.DTOs.Discounts.DiscountDTO;
import org.vaadin.UI.model.DTOs.PolicyViewDTO;
import org.vaadin.UI.model.models.InventoryModel;
import org.vaadin.UI.presenter.DiscountPresenter;
import org.vaadin.UI.presenter.PolicyPresenter;
import org.vaadin.UI.view.AddDiscount.AddDiscountDialog;
import org.vaadin.UI.view.AddDiscount.AddHiddenDiscountDialog;


import java.util.List;
@Route("settings/discounts")
public class DiscountView extends MainSettingView {
    private ComboBox<String> chooseStoreComboBox;
    private Grid<DiscountDTO> discountsGrid;
    private Button addNewDiscountButton;
    private Button addNewHiddenDiscountButton;
    private DiscountPresenter presenter;
    private InventoryModel inventoryModel;
    private long storeId;

    public DiscountView(){
        this.inventoryModel = new InventoryModel();
        presenter = new DiscountPresenter(this);
        PolicyPresenter policyPresenter = new PolicyPresenter(null);

        VerticalLayout rightContent = getRightContent();
        rightContent.removeAll();

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.add(new H1("Discounts"));
        chooseStoreComboBox = new ComboBox<>("Select your store");
        chooseStoreComboBox.setPlaceholder("No store selected yet");
        presenter.onChoosingStore();
        addNewDiscountButton = new Button("Add New Composite Discount", new Icon(VaadinIcon.PLUS));
        addNewHiddenDiscountButton = new Button("Add New Hidden Discount", new Icon(VaadinIcon.PLUS));
        topLayout.add(chooseStoreComboBox);
        topLayout.add(addNewDiscountButton);
        topLayout.add(addNewHiddenDiscountButton);
        topLayout.setWidthFull();
        topLayout.setAlignItems(FlexComponent.Alignment.END);
        rightContent.add(topLayout);

        discountsGrid = createDiscountsGrid();
        discountsGrid.setWidthFull();
        rightContent.add(discountsGrid);

        chooseStoreComboBox.addValueChangeListener(event -> {
            String selectedOptionStoreName = event.getValue();
            this.storeId = this.inventoryModel.getStoreIdByName(selectedOptionStoreName, Credentials.getToken());
            policyPresenter.onSelectStore(selectedOptionStoreName);
        });

        addNewDiscountButton.addClickListener(event -> {
            AddDiscountDialog dialog = new AddDiscountDialog(presenter, null);
            dialog.open();
        });

        addNewHiddenDiscountButton.addClickListener(event -> {
            AddHiddenDiscountDialog dialog = new AddHiddenDiscountDialog(presenter,policyPresenter, storeId);
            dialog.open();
        });
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public void fillUpDiscounts(List<DiscountDTO> discounts) {discountsGrid.setItems(discounts);}

    public void fillChooseStoreComboBox(List<String> stores) {
        chooseStoreComboBox.setItems(stores);
    }

    public void showNotification(String message) {
        Notification.show(message);
    }

    private Grid<DiscountDTO> createDiscountsGrid() {
        Grid<DiscountDTO> grid = new Grid<>(DiscountDTO.class);
        return grid;
    }
}
