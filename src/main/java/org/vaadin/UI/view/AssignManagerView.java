package org.vaadin.UI.view;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.UI.Presenter.AssignManagerPresenter;
import org.vaadin.UI.Presenter.AssignOwnerPresenter;

import java.util.Arrays;
import java.util.List;

@Route("settings/assign manager")
public class AssignManagerView extends MainSettingView{
    private AssignManagerPresenter assignManagerPresenter;
    private final TextField userName;
    private final TextField storeNumber;
    private final MultiSelectComboBox<String> permissionsComboBox;


    public AssignManagerView() {
        assignManagerPresenter = new AssignManagerPresenter(this);
        userName = new TextField("User Name");
        storeNumber = new TextField("Store Number");

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

        Button assignManagerButton = new Button("Assign manager");
        assignManagerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        assignManagerButton.addClickListener(event ->
                assignManagerPresenter.onAssignManager(
                        userName.getValue(),
                        storeNumber.getValue(),
                        permissionsComboBox.getValue()
                )
        );

        VerticalLayout formLayout = new VerticalLayout(userName, storeNumber, permissionsComboBox, assignManagerButton);
        formLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        formLayout.setSpacing(true);

        add(formLayout);
    }

    public void showNotification(String message) {
        Notification.show(message);
    }
}
