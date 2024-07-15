package org.vaadin.UI.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.presenter.AssignManagerPresenter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Route("settings/assign manager")
public class AssignManagerView extends MainSettingView {
    private final TextField userName;
    private final TextField storeName;
    private final MultiSelectComboBox<String> permissionsComboBox;
    private AssignManagerPresenter assignManagerPresenter;


    public AssignManagerView() {
        assignManagerPresenter = new AssignManagerPresenter(this);
        userName = new TextField("User Name");
        storeName = new TextField("Store Name");

        // Initialize the Multi-Select Combo Box for permissions
        permissionsComboBox = new MultiSelectComboBox<>("Manager Permissions");
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
        permissionsComboBox.setValue(new HashSet<>(permissions));
        Button assignManagerButton = new Button("Assign manager");
        assignManagerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        assignManagerButton.addClickListener(event ->
                assignManagerPresenter.onAssignManager(
                        userName.getValue(),
                        storeName.getValue(),
                        permissionsComboBox.getValue()
                )
        );

        VerticalLayout formLayout = new VerticalLayout(userName, storeName, permissionsComboBox, assignManagerButton);
        formLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        formLayout.setSpacing(true);
        VerticalLayout rightContent = getRightContent();
        rightContent.removeAll();
        rightContent.add(formLayout);
//        add(formLayout);
    }

    public void showNotification(String message) {
        Notification.show(message);
    }
}
