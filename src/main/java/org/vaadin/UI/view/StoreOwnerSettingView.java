package org.vaadin.UI.view;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.presenter.StoreOwnerSettingPresenter;
import com.vaadin.flow.component.combobox.ComboBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Route("store-setting")
public class StoreOwnerSettingView extends MainSettingView {
    private ComboBox<Long> chooseStoreComboBox;
    private VerticalLayout restOfPage;
    private StoreOwnerSettingPresenter presenter;
    private String token = "userToken"; // Replace with actual token retrieval logic
    private ComboBox<String> managementInfoComboBox;
    private VerticalLayout permissionsLayout;
//    private Div managementInfoDiv;

    public StoreOwnerSettingView() {
        presenter = new StoreOwnerSettingPresenter(this);
        restOfPage = new VerticalLayout();
        restOfPage.add(createChooseStoreComboBox());
//        restOfPage.add(createItemGrid());

//        managementInfoDiv = new Div();
//        managementInfoDiv.setText("Management Info will be displayed here.");
//        VerticalLayout mainLayout = new VerticalLayout(restOfPage, managementInfoDiv);
        permissionsLayout = new VerticalLayout();

        permissionsLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        VerticalLayout centeredPermissionsContainer = new VerticalLayout();
        centeredPermissionsContainer.setWidthFull();
        centeredPermissionsContainer.setAlignItems(Alignment.CENTER);
        centeredPermissionsContainer.add(permissionsLayout);

        add(restOfPage);
        add(centeredPermissionsContainer);

        // Add a listener to the combo box to fetch and display management info when a store is selected
        chooseStoreComboBox.addValueChangeListener(event -> {
            Long selectedStoreId = event.getValue();
            if (selectedStoreId != null) {
                displayManagementInfo(selectedStoreId);
            }
        });
    }

    private ComboBox<Long> createChooseStoreComboBox() {
        // Fetch the real store IDs that the user owns
        List<Long> storesList = presenter.getUserStoreOwnerships(token);

        chooseStoreComboBox = new ComboBox<>("Select your store", storesList);
        chooseStoreComboBox.setPlaceholder("No store selected yet");
        return chooseStoreComboBox;
    }

//    private ItemGrid createItemGrid() {
//        itemGrid = new ItemGrid();
//        return itemGrid;
//    }

    private void displayManagementInfo(long storeId) {
        // Remove the existing management info combo box if it exists
        if (managementInfoComboBox != null) {
            restOfPage.remove(managementInfoComboBox);
        }
        Set<String> managementInfo = presenter.getStoreManagementInfo(token, storeId);
        if (managementInfo != null) {
            managementInfoComboBox = new ComboBox<>("Select manager", managementInfo);
            managementInfoComboBox.setPlaceholder("No store selected yet");
            restOfPage.add(managementInfoComboBox);
            // Add a listener to the management info combo box to display permissions table when a manager is selected
            managementInfoComboBox.addValueChangeListener(event -> {
                String selectedManager = event.getValue();
                if (selectedManager != null) {
                    displayPermissionsTable();
                }
            });
        } else {
            showNotification("Failed to fetch management info.");
        }
    }

    private void displayPermissionsTable() {
        // Clear any existing permissions table
        permissionsLayout.removeAll();

        // List of permissions
        String[] permissions = {
                "VIEW_INVENTORY",
                "VIEW_STORE_MANAGEMENT_INFO",
                "VIEW_PURCHASE_HISTORY",
                "ASSIGN_OWNER",
                "ASSIGN_MANAGER",
                "ADD_ITEM",
                "UPDATE_ITEM",
                "DELETE_ITEM",
                "CHANGE_POLICY",
                "CHANGE_DISCOUNT_TYPE",
                "REMOVE_STORE"
        };

        // Create a table of checkboxes for each permission
        for (String permission : permissions) {
            Checkbox checkbox = new Checkbox(permission);
            permissionsLayout.add(checkbox);
        }

        // Create and add the "Send" button
        Button sendButton = new Button("Send");
        sendButton.addClickListener(event -> {
            // Placeholder for what happens after the "Send" button is clicked
            showNotification("Permissions submitted.");
        });
        permissionsLayout.add(sendButton);
    }

    public void showNotification(String message) {
        Notification.show(message);
    }
}
