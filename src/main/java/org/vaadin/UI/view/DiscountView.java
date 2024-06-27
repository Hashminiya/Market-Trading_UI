package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.model.DTOs.DiscountDTO;

import org.vaadin.UI.presenter.DiscountPresenter;


import java.util.List;
@Route("settings/discounts")
public class DiscountView extends MainSettingView {
    private ComboBox<String> chooseStoreComboBox;
    private Grid<DiscountDTO> discountsGrid;
    private Button addNewDiscountButton;
    private DiscountPresenter presenter;
    private VerticalLayout drawer;

    public DiscountView(){
        presenter = new DiscountPresenter(this);

        VerticalLayout rightContent = getRightContent();
        rightContent.removeAll();

        HorizontalLayout topLayout = new HorizontalLayout();
        chooseStoreComboBox = new ComboBox<>("Select your store");
        chooseStoreComboBox.setPlaceholder("No store selected yet");
        presenter.onChoosingStore();
        addNewDiscountButton = new Button("Add New Discount", new Icon(VaadinIcon.PLUS));
        topLayout.add(chooseStoreComboBox);
        topLayout.add(addNewDiscountButton);
        topLayout.setWidthFull();
        topLayout.setAlignItems(FlexComponent.Alignment.END);
        rightContent.add(topLayout);

        discountsGrid = createDiscountsGrid();
        discountsGrid.setWidthFull();
        rightContent.add(discountsGrid);

        //TODO: complete this
//        form = new ItemForm(presenter);
//        form.setVisible(false);
//
//        drawer = createDrawer();
//        rightContent.add(drawer);

        chooseStoreComboBox.addValueChangeListener(event -> {
            String selectedOptionStoreName = event.getValue();
            presenter.onSelectStore(selectedOptionStoreName);
        });

        discountsGrid.asSingleSelect().addValueChangeListener(event -> {
            //TODO: complete this
//            if (event.getValue() != null) {
//                showForm(true, event.getValue());
//            } else {
//                showForm(false, null);
//            }
        });

        addNewDiscountButton.addClickListener(event -> {
            presenter.onClickingAddNewItemButton();
        });
    }

    public void fillUpDiscounts(List<DiscountDTO> storeItems) {
    }

    public void fillChooseStoreComboBox(List<String> stores) {
        chooseStoreComboBox.setItems(stores);
    }

    public void showNotification(String message) {
        Notification.show(message);
    }

    private Grid<DiscountDTO> createDiscountsGrid() {
        Grid<DiscountDTO> grid = new Grid<>(DiscountDTO.class);
//        grid.setColumns("itemId", "itemName", "quantity", "storeId", "totalPrice");
//        grid.getColumnByKey("itemId").setHeader("Item ID");
//        grid.getColumnByKey("itemName").setHeader("Item Name");
//        grid.getColumnByKey("quantity").setHeader("Quantity Available");
//        grid.getColumnByKey("storeId").setHeader("Store ID");
//        grid.getColumnByKey("totalPrice").setHeader("Price");
        return grid;
    }
}
