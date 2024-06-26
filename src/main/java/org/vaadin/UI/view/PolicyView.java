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
import org.vaadin.UI.model.DTOs.PolicyDTO;
import org.vaadin.UI.presenter.PolicyPresenter;

import java.util.List;

public class PolicyView extends MainSettingView {
    private ComboBox<String> chooseStoreComboBox;
    private Grid<PolicyDTO> policiesGrid;
    private Button addNewPolicyButton;
    private PolicyPresenter presenter;
    private VerticalLayout drawer;

    public PolicyView(){
        presenter = new PolicyPresenter(this);

        VerticalLayout rightContent = getRightContent();
        rightContent.removeAll();

        HorizontalLayout topLayout = new HorizontalLayout();
        chooseStoreComboBox = new ComboBox<>("Select your store");
        chooseStoreComboBox.setPlaceholder("No store selected yet");
        presenter.onChoosingStore();
        addNewPolicyButton = new Button("Add New Item", new Icon(VaadinIcon.PLUS));
        topLayout.add(chooseStoreComboBox);
        topLayout.add(addNewPolicyButton);
        topLayout.setWidthFull();
        topLayout.setAlignItems(FlexComponent.Alignment.END);
        rightContent.add(topLayout);

        policiesGrid = createPoliciesGrid();
        policiesGrid.setWidthFull();
        rightContent.add(policiesGrid);

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

        policiesGrid.asSingleSelect().addValueChangeListener(event -> {
            //TODO: complete this
//            if (event.getValue() != null) {
//                showForm(true, event.getValue());
//            } else {
//                showForm(false, null);
//            }
        });

        addNewPolicyButton.addClickListener(event -> {
            presenter.onClickingAddNewItemButton();
        });
    }

    public void fillUpPolicies(List<PolicyDTO> storeItems) {
    }

    public void fillChooseStoreComboBox(List<String> stores) {
        chooseStoreComboBox.setItems(stores);
    }

    public void showNotification(String message) {
        Notification.show(message);
    }

    private Grid<PolicyDTO> createPoliciesGrid() {
        Grid<PolicyDTO> grid = new Grid<>(PolicyDTO.class);
//        grid.setColumns("itemId", "itemName", "quantity", "storeId", "totalPrice");
//        grid.getColumnByKey("itemId").setHeader("Item ID");
//        grid.getColumnByKey("itemName").setHeader("Item Name");
//        grid.getColumnByKey("quantity").setHeader("Quantity Available");
//        grid.getColumnByKey("storeId").setHeader("Store ID");
//        grid.getColumnByKey("totalPrice").setHeader("Price");
        return grid;
    }
}
