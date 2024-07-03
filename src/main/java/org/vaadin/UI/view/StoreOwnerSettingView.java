package org.vaadin.UI.view;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.presenter.StoreOwnerSettingPresenter;

import java.util.*;

@Route("settings/store-setting")
public class StoreOwnerSettingView extends MainSettingView {
    private ComboBox<String> chooseStoreComboBox;
//    private VerticalLayout restOfPage;
    private StoreOwnerSettingPresenter presenter;
    private Button sendButton;  // Declare the button at the class level

    //    private String token = "userToken"; // Replace with actual token retrieval logic
    private ComboBox<String> managementInfoComboBox;
    private final MultiSelectComboBox<String> permissionsComboBox;
    private VerticalLayout formLayout;

//    private VerticalLayout permissionsLayout;
//    private Div managementInfoDiv;

    public StoreOwnerSettingView() {
        presenter = new StoreOwnerSettingPresenter(this);
//        restOfPage = new VerticalLayout();
//        restOfPage.add(createChooseStoreComboBox());
//        restOfPage.add(createItemGrid());

//        managementInfoDiv = new Div();
//        managementInfoDiv.setText("Management Info will be displayed here.");
//        VerticalLayout mainLayout = new VerticalLayout(restOfPage, managementInfoDiv);
//        permissionsLayout = new VerticalLayout();

//        permissionsLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
//        VerticalLayout centeredPermissionsContainer = new VerticalLayout();
//        centeredPermissionsContainer.setWidthFull();
//        centeredPermissionsContainer.setAlignItems(Alignment.CENTER);
//        centeredPermissionsContainer.add(permissionsLayout);
//
//        add(restOfPage);
//        add(centeredPermissionsContainer);

        // Add a listener to the combo box to fetch and display management info when a store is selected
        formLayout = new VerticalLayout(createChooseStoreComboBox());
        formLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        formLayout.setSpacing(true);

        chooseStoreComboBox.addValueChangeListener(event -> {
            String selectedStoreName = event.getValue();
            if (selectedStoreName != null) {
                displayManagementInfo(selectedStoreName);
            }
        });
        permissionsComboBox = new MultiSelectComboBox<>("Manager Permissions");
        add(formLayout);
    }

    private ComboBox<String> createChooseStoreComboBox() {
        // Fetch the real store IDs that the user owns
        List<String> storesList = presenter.getUserStoreOwnerships();
        chooseStoreComboBox = new ComboBox<>("Select your store", storesList);
        chooseStoreComboBox.setPlaceholder("No store selected yet");
        return chooseStoreComboBox;
    }


    private void displayManagementInfo(String storename) {
        // Remove the existing management info combo box if it exists
        if (managementInfoComboBox != null) {
            formLayout.remove(managementInfoComboBox);
        }
        Set<String> managementInfo = presenter.getStoreManagementInfo(storename);
        if (managementInfo != null) {
            managementInfoComboBox = new ComboBox<>("Select manager", managementInfo);
            managementInfoComboBox.setPlaceholder("No store selected yet");
            formLayout.add(managementInfoComboBox);
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
        formLayout.remove(permissionsComboBox);
        if (sendButton != null) {
            formLayout.remove(sendButton);
        }
        List<String> permissions = Arrays.asList(
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
        );
        permissionsComboBox.setItems(permissions);
        formLayout.add(permissionsComboBox);

        // Create and add the "Send" button
        sendButton = new Button("Send changes");
        sendButton.addClickListener(event -> {
            // Placeholder for what happens after the "Send" button is clicked
            showNotification("Permissions submitted.");
        });
        formLayout.add(sendButton);
    }

    public void showNotification(String message) {
        Notification.show(message);
    }
}